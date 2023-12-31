package org.tcf.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClient;
import org.compiere.model.MLocator;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MStorageOnHand;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class MPlanningProduct extends X_M_PlanningProduct{
	
	private static final long serialVersionUID = -9124526517025508440L;

	/**
	 * @param ctx
	 * @param M_ProductionPlan_ID
	 * @param trxName
	 */
	public MPlanningProduct(Properties ctx, int M_PlanningProduct_ID,
			String trxName) {
		super(ctx, M_PlanningProduct_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPlanningProduct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MPlanningLine[] getLines() {
		ArrayList<MPlanningLine> list = new ArrayList<MPlanningLine>();
		
		String sql = "SELECT pl.M_PlanningLine_ID "
			+ "FROM M_PlanningLine pl "
			+ "WHERE pl.M_PlanningProduct_ID = ? "
			+ "ORDER BY pl.Line, pl.M_PlanningLine_ID ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, get_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add( new MPlanningLine( getCtx(), rs.getInt(1), get_TrxName() ) );	
		}
		catch (SQLException ex)
		{
			throw new AdempiereException("Unable to load production lines", ex);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		MPlanningLine[] retValue = new MPlanningLine[list.size()];
		list.toArray(retValue);
		return retValue;
	}
	
	public void deleteLines(String trxName) {

		for (MPlanningLine line : getLines())
		{
			line.deleteEx(true);
		}

	}// deleteLines

	public int createLines(boolean mustBeStocked) {
		
		int lineno = 100;

		int count = 0;

		// product to be produced
		MProduct finishedProduct = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		

		MPlanningLine line = new MPlanningLine( this );
		line.setLine( lineno );
		line.setM_Product_ID( finishedProduct.get_ID() );
		line.setM_Locator_ID( getM_Locator_ID() );
		line.setMovementQty( getProductionPlanQty());
		line.setPlannedQty(getProductionPlanQty());
		
		line.saveEx();
		count++;
		
		count = count + createLines(mustBeStocked, finishedProduct, getProductionPlanQty(), lineno);
		
		return count;
	}

	private int createLines(boolean mustBeStocked, MProduct finishedProduct, BigDecimal requiredQty, int lineno) {
		
		int count = 0;
		int defaultLocator = 0;
		
		MLocator finishedLocator = MLocator.get(getCtx(), getM_Locator_ID());
		
		int M_Warehouse_ID = finishedLocator.getM_Warehouse_ID();
		
		// products used in production
		String sql = " SELECT bl.M_Product_ID, bl.QtyBOM" + " FROM PP_Product_BOMLine bl"
				+ " JOIN PP_Product_BOM b ON b.PP_Product_BOM_ID = bl.PP_Product_BOM_ID "
				+ " WHERE b.M_Product_ID=" + finishedProduct.getM_Product_ID() + " AND b.IsActive='Y' AND bl.IsActive='Y' "
				+ " AND b.BOMType='A' AND b.BOMUse='A' " 
				+ " ORDER BY bl.Line";
		
		try (PreparedStatement pstmt = DB.prepareStatement(sql, get_TrxName());) {			

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				lineno = lineno + 10;
				int BOMProduct_ID = rs.getInt(1);
				BigDecimal BOMQty = rs.getBigDecimal(2);
				BigDecimal BOMMovementQty = BOMQty.multiply(requiredQty);
				
				MProduct bomproduct = new MProduct(Env.getCtx(), BOMProduct_ID, get_TrxName());
				

				if ( bomproduct.isBOM() && bomproduct.isPhantom() )
				{
					count = count + createLines(mustBeStocked, bomproduct, BOMMovementQty, lineno);
				}
				else
				{

					defaultLocator = bomproduct.getM_Locator_ID();
					if ( defaultLocator == 0 )
						defaultLocator = getM_Locator_ID();

					if (!bomproduct.isStocked())
					{					
						MPlanningLine BOMLine = null;
						BOMLine = new MPlanningLine( this );
						BOMLine.setLine( lineno );
						BOMLine.setM_Product_ID( BOMProduct_ID );
						BOMLine.setM_Locator_ID( defaultLocator );  
						BOMLine.setQtyUsed(BOMMovementQty );
						BOMLine.setPlannedQty( BOMMovementQty );
						BOMLine.saveEx(get_TrxName());

						lineno = lineno + 10;
						count++;					
					}
					else if (BOMMovementQty.signum() == 0) 
					{
						MPlanningLine BOMLine = null;
						BOMLine = new MPlanningLine( this );
						BOMLine.setLine( lineno );
						BOMLine.setM_Product_ID( BOMProduct_ID );
						BOMLine.setM_Locator_ID( defaultLocator );  
						BOMLine.setQtyUsed( BOMMovementQty );
						BOMLine.setPlannedQty( BOMMovementQty );
						BOMLine.saveEx(get_TrxName());

						lineno = lineno + 10;
						count++;
					}
					else
					{

						// BOM stock info
						MStorageOnHand[] storages = null;
						MProduct usedProduct = MProduct.get(getCtx(), BOMProduct_ID);
						defaultLocator = usedProduct.getM_Locator_ID();
						if ( defaultLocator == 0 )
							defaultLocator = getM_Locator_ID();
						if (usedProduct == null || usedProduct.get_ID() == 0)
							return 0;

						MClient client = MClient.get(getCtx());
						MProductCategory pc = MProductCategory.get(getCtx(),
								usedProduct.getM_Product_Category_ID());
						String MMPolicy = pc.getMMPolicy();
						if (MMPolicy == null || MMPolicy.length() == 0) 
						{ 
							MMPolicy = client.getMMPolicy();
						}

						storages = MStorageOnHand.getWarehouse(getCtx(), M_Warehouse_ID, BOMProduct_ID, 0, null,
								MProductCategory.MMPOLICY_FiFo.equals(MMPolicy), true, 0, get_TrxName());

						MPlanningLine BOMLine = null;
						int prevLoc = -1;
						// Create lines from storage until qty is reached
						for (int sl = 0; sl < storages.length; sl++) {

							BigDecimal lineQty = storages[sl].getQtyOnHand();
							if (lineQty.signum() != 0) {
								if (lineQty.compareTo(BOMMovementQty) > 0)
									lineQty = BOMMovementQty;

								int loc = storages[sl].getM_Locator_ID();
								// same locator
								if (prevLoc == loc) {
									BOMLine.setQtyUsed(BOMLine.getQtyUsed()
											.add(lineQty));
									BOMLine.setPlannedQty(BOMLine.getQtyUsed());
									BOMLine.saveEx(get_TrxName());

								}
								// otherwise create new line
								else {
									BOMLine = new MPlanningLine( this );
									BOMLine.setLine( lineno );
									BOMLine.setM_Product_ID( BOMProduct_ID );
									BOMLine.setM_Locator_ID( loc );
									BOMLine.setQtyUsed( lineQty);
									BOMLine.setPlannedQty( lineQty);
									BOMLine.saveEx(get_TrxName());

									lineno = lineno + 10;
									count++;
								}
								prevLoc = loc;
								// enough ?
								BOMMovementQty = BOMMovementQty.subtract(lineQty);
								if (BOMMovementQty.signum() == 0)
									break;
							}
						} // for available storages

						// fallback
						if (BOMMovementQty.signum() != 0 ) {
							if (!mustBeStocked)
							{
								//same locator
								if (prevLoc == defaultLocator) {
									BOMLine.setQtyUsed(BOMLine.getQtyUsed()
											.add(BOMMovementQty));
									BOMLine.setPlannedQty(BOMLine.getQtyUsed());
									BOMLine.saveEx(get_TrxName());

								}
								// otherwise create new line
								else {
									BOMLine = new MPlanningLine( this );
									BOMLine.setLine( lineno );
									BOMLine.setM_Product_ID( BOMProduct_ID );
									BOMLine.setM_Locator_ID( defaultLocator );  
									BOMLine.setQtyUsed( BOMMovementQty);
									BOMLine.setPlannedQty( BOMMovementQty);
									BOMLine.saveEx(get_TrxName());

									lineno = lineno + 10;
									count++;
								}
							}
							else
							{
								throw new AdempiereUserError("Not enough stock of " + BOMProduct_ID);
							}
						}
					}
				}
			} // for all bom products
		} catch (Exception e) {
			throw new AdempiereException("Failed to create production lines", e);
		}

		return count;
	}
	
	@Override
	protected boolean beforeDelete() {
		deleteLines(get_TrxName());
		return true;
	}

	@Override
	protected boolean beforeSave(boolean newRecord) 
	{
		MPlanning parent = new MPlanning(getCtx(), getM_Planning_ID(), get_TrxName());
		if (newRecord && parent.isProcessed()) {
			log.saveError("ParentComplete", Msg.translate(getCtx(), "M_Planning_ID"));
			return false;
		}
		return true;
	}
}

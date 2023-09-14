package org.tcf.documents;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.compiere.acct.Doc;
import org.compiere.acct.DocLine;
import org.compiere.acct.Fact;
import org.compiere.model.MAcctSchema;
import org.compiere.util.DB;
import org.tcf.model.MPlanning;
import org.tcf.model.X_M_Planning;
import org.tcf.model.X_M_PlanningLine;

public class Doc_Planning extends Doc {
	
	public Doc_Planning(MAcctSchema as,ResultSet rs,String trxName) {
		super(as,MPlanning.class,rs,DOCTYPE_MatProduction,trxName);
	}//Doc_Planning
	
	@Override
	protected String loadDocumentDetails() {
		setC_Currency_ID (NO_CURRENCY);
		X_M_Planning planning = (X_M_Planning)getPO();
		setDateDoc (planning.getMovementDate());
		setDateAcct(planning.getMovementDate());
		//	Contained Objects
		p_lines = loadLines(planning);
		if (log.isLoggable(Level.FINE)) log.fine("Lines=" + p_lines.length);
		return null;
	}//  loadDocumentDetailss
	
	private Map<Integer, BigDecimal> mQtyPlanning;
	
	
	private BigDecimal manipulateQtyPlanning (Map<Integer, BigDecimal> mQtyPlanning, X_M_PlanningLine line, Boolean isUsePlan, BigDecimal addMoreQty){
		BigDecimal qtyProduced = null;
		Integer key = isUsePlan?line.getM_PlanningProduct_ID():line.getM_Planning_ID();
		
		if (mQtyPlanning.containsKey(key)){
			qtyProduced = mQtyPlanning.get(key);
		}else{
			qtyProduced = BigDecimal.ZERO;
			mQtyPlanning.put(key, qtyProduced);
		}
		
		if (addMoreQty != null){
			qtyProduced = qtyProduced.add(addMoreQty);
			mQtyPlanning.put(key, qtyProduced);
		}
			
		return qtyProduced;
	}
	
	private DocLine[] loadLines(X_M_Planning planning)
	{
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		mQtyPlanning = new HashMap<>(); 
		String sqlPL = null;
		if (planning.isUseProductionPlan()){
//			Production
			//	-- ProductionLine	- the real level
			sqlPL = "SELECT * FROM "
							+ " M_PlanningLine pro_line INNER JOIN M_PlanningProduct plan ON pro_line.M_PlanningProduct_id = plan.M_PlanningProduct_id "
							+ " INNER JOIN M_Planning pro ON pro.M_Planning_id = plan.M_Planning_id "
							+ " WHERE pro.M_Planning_ID=? "
							+ " ORDER BY plan.M_PlanningProduct_id, pro_line.Line";
		}else{
//			Production
			//	-- ProductionLine	- the real level
			sqlPL = "SELECT * FROM M_PlanningLine pl "
					+ "WHERE pl.M_Planning_ID=? "
					+ "ORDER BY pl.Line";
		}
		
		PreparedStatement pstmtPL = null;
		ResultSet rsPL = null;
		try
		{			
			pstmtPL = DB.prepareStatement(sqlPL, getTrxName());
			pstmtPL.setInt(1,get_ID());
			rsPL = pstmtPL.executeQuery();
			while (rsPL.next())
			{
				X_M_PlanningLine line = new X_M_PlanningLine(getCtx(), rsPL, getTrxName());
				if (line.getMovementQty().signum() == 0)
				{
					if (log.isLoggable(Level.INFO)) log.info("LineQty=0 - " + line);
					continue;
				}
				DocLine docLine = new DocLine (line, this);
				docLine.setQty (line.getMovementQty(), false);
				//	Identify finished BOM Product
				if (planning.isUseProductionPlan())
					docLine.setProductionBOM(line.getM_Product_ID() == line.getM_PlanningProduct().getM_Product_ID());
				else
					docLine.setProductionBOM(line.getM_Product_ID() == planning.getM_Product_ID());
				
				if (docLine.isProductionBOM()){
					manipulateQtyPlanning (mQtyPlanning, line, planning.isUseProductionPlan(), line.getMovementQty());
				}
				//
				if (log.isLoggable(Level.FINE)) log.fine(docLine.toString());
				list.add (docLine);
			}
		}
		catch (Exception ee)
		{
			log.log(Level.SEVERE, sqlPL, ee);
		}
		finally
		{
			DB.close(rsPL, pstmtPL);
			rsPL = null;
			pstmtPL = null;
		}
			
		DocLine[] dl = new DocLine[list.size()];
		list.toArray(dl);
		return dl;
	}	//	loadLines
	
	@Override
	public BigDecimal getBalance() {
		return BigDecimal.ZERO;
	}
	@Override
	public ArrayList<Fact> createFacts(MAcctSchema as){
		ArrayList<Fact> facts = new ArrayList<Fact>();
		return facts;
	}
}

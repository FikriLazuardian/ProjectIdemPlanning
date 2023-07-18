package org.tcf.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MStorageOnHand;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

public class MPlanningLineMA extends X_M_PlanningLineMA {
	
	private static final long serialVersionUID = -4039144824975653270L;

	public MPlanningLineMA(Properties ctx, int M_PlanningLineMA_ID,
			String trxName) {
		super(ctx, M_PlanningLineMA_ID, trxName);
	}

	public MPlanningLineMA(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Parent constructor
	 * @param parent
	 * @param asi
	 * @param qty
	 * @param dateMaterialPolicy
	 */
	public MPlanningLineMA( MPlanningLine parent, int asi, BigDecimal qty,Timestamp dateMaterialPolicy)	{		
		super(parent.getCtx(),0,parent.get_TrxName());
		setM_AttributeSetInstance_ID(asi);
		setM_PlanningLine_ID(parent.get_ID());
		setMovementQty(qty);
		setAD_Org_ID(parent.getAD_Org_ID());
		if (dateMaterialPolicy == null)
		{
			if (asi > 0)
			{
				dateMaterialPolicy = MStorageOnHand.getDateMaterialPolicy(parent.getM_Product_ID(), asi, parent.get_TrxName());
			}
			if (dateMaterialPolicy == null)
			{
				dateMaterialPolicy = parent.getM_Planning().getMovementDate();
			}
		}
		setDateMaterialPolicy(dateMaterialPolicy);
	}
	
	@Override
	public void setDateMaterialPolicy(Timestamp DateMaterialPolicy) {
		if (DateMaterialPolicy != null)
			DateMaterialPolicy = Util.removeTime(DateMaterialPolicy);
		super.setDateMaterialPolicy(DateMaterialPolicy);
	}
	
	public static MPlanningLineMA get( MPlanningLine parent, int asi, Timestamp dateMPolicy )  {
		String where = " M_PlanningLine_ID = ? AND M_AttributeSetInstance_ID = ? ";
		if(dateMPolicy==null){
			dateMPolicy = new Timestamp(new Date().getTime());
		}
		where = where + "AND DateMaterialPolicy = trunc(cast(? as date))";
		
		MPlanningLineMA lineMA = MTable.get(parent.getCtx(), MPlanningLineMA.Table_Name).createQuery(where, parent.get_TrxName())
		.setParameters(parent.getM_PlanningLine_ID(), asi,dateMPolicy).first();
		
		if (lineMA != null)
			return lineMA;
		else
			return new MPlanningLineMA( parent,
				asi,
				Env.ZERO,dateMPolicy);
	}

	/**
	 * 	Get Material Allocations for Line
	 *	@param ctx context
	 *	@param M_ProductionLine_ID line
	 *	@param trxName trx
	 *	@return allocations
	 */
	public static MPlanningLineMA[] get (Properties ctx, int M_PlanningLine_ID, String trxName)
	{
	
		Query query = MTable.get(ctx, MPlanningLineMA.Table_Name)
							.createQuery(I_M_PlanningLineMA.COLUMNNAME_M_PlanningLine_ID+"=?", trxName);
		query.setParameters(M_PlanningLine_ID);
		List<MPlanningLineMA> list = query.list();		
		MPlanningLineMA[] retValue = list.toArray (new MPlanningLineMA[0]);		
		return retValue;
	}	//	get
	
	@Override
	protected boolean beforeSave(boolean newRecord) 
	{
		MPlanningLine parentLine = new MPlanningLine(getCtx(), getM_PlanningLine_ID(), get_TrxName());
		MPlanning planningParent;
		if (parentLine.getM_Planning_ID() > 0) {
			planningParent = new MPlanning(getCtx(), parentLine.getM_Planning_ID(), get_TrxName());
		} else {
			MPlanningProduct plan = new MPlanningProduct(getCtx(), parentLine.getM_PlanningProduct_ID(), get_TrxName());
			planningParent = new MPlanning(getCtx(), plan.getM_Planning_ID(), get_TrxName());
		}
		if (newRecord && planningParent.isProcessed()) {
			log.saveError("ParentComplete", Msg.translate(getCtx(), "M_Planning_ID"));
			return false;
		}
		return true;
	}
}

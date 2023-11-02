package org.tcf.model;

import java.sql.ResultSet;
import java.util.Objects;
import java.util.Properties;

import org.compiere.model.MProduction;

public class MProdNew extends MProduction {

	private static final long serialVersionUID = 513514382481345664L;

	public MProdNew(Properties ctx, int M_Production_ID, String trxName, String[] virtualColumns) {
		super(ctx, M_Production_ID, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}
	public MProdNew(Properties ctx, int M_Production_ID, String trxName) {
		super(ctx, M_Production_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MProdNew(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	@Override
	public String completeIt() {
		// TODO Auto-generated method stub
		Integer M_Planning_ID = (Integer) get_Value("M_Planning_ID");
		if (Objects.nonNull(M_Planning_ID) && M_Planning_ID > 0) {
			MPlanning planning = new MPlanning(getCtx(), M_Planning_ID, get_TrxName());
			planning.setProductionQty(planning.getProductionQty().add(getProductionQty()));
			planning.save();
		}
		return super.completeIt();
	}
}

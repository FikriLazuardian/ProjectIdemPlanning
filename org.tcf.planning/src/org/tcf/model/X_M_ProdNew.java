package org.tcf.model;

import java.util.Properties;

import org.compiere.model.X_M_Production;

public class X_M_ProdNew extends X_M_Production {
	

	private static final long serialVersionUID = 4391143937432745427L;

	public X_M_ProdNew(Properties ctx, int M_Production_ID, String trxName) {
		super(ctx, M_Production_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public X_M_ProdNew(Properties ctx, int M_Production_ID, String trxName, String[] virtualColumns) {
		super(ctx, M_Production_ID, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}


}

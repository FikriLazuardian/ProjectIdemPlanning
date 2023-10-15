package org.tcf.model;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.CLogger;

public class CalloutPlanning extends CalloutEngine{
	CLogger log = CLogger.getCLogger(CalloutPlanning.class);

	public String planning (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) {
		Integer M_Planning_ID = (Integer)value;
		if(M_Planning_ID == null || M_Planning_ID.intValue()==0) {
			return "";
		}
		log.warning("ColumnName: "+ mField.getColumnName());
		log.warning("NewValue: "+ value.toString());
		//Get Details
		MPlanning planning = new MPlanning (ctx, M_Planning_ID.intValue(), null);
		if (planning.get_ID() != 0)
		{
			
			mTab.setValue("AD_Org_ID", Integer.valueOf(planning.getAD_Org_ID()));
			mTab.setValue("AD_OrgTrx_ID", Integer.valueOf(planning.getAD_OrgTrx_ID()));
			mTab.setValue("M_Locator_ID", Integer.valueOf(planning.getM_Locator_ID()));
			mTab.setValue("M_Product_ID", Integer.valueOf(planning.getM_Product_ID()));
			mTab.setValue("ProductionPlanQty", planning.getProductionPlanQty());
		}
		return "";
	}
	
	public String product (Properties ctx, int WindowNo,GridTab mTab, GridField mField,Object value) {
		Integer M_Planning_ID = (Integer)value;
		
		return "";
	}
}

package org.tcf.factories;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
//import org.adempiere.base.IProcessFactory;
import org.compiere.model.PO;
//import org.compiere.process.ProcessCall;
import org.compiere.util.Env;
import org.tcf.model.MPlanning;
import org.tcf.model.MPlanningLine;
import org.tcf.model.MPlanningLineMA;
import org.tcf.model.MProdNew;
//import org.tcf.process.PlanningCreate;

public class TcfPlanningModelFactory implements IModelFactory {

	@Override
	public Class<?> getClass(String tableName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MPlanning.Table_Name)) {
			return MPlanning.class;
		}
		if(tableName.equalsIgnoreCase(MPlanningLine.Table_Name)) {
			return MPlanningLine.class;
		}
		if(tableName.equalsIgnoreCase(MPlanningLineMA.Table_Name)) {
			return MPlanningLineMA.class;
		}
		if(tableName.equalsIgnoreCase(MProdNew.Table_Name)) {
			return MProdNew.class;
		}
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MPlanning.Table_Name)) {
			return new MPlanning(Env.getCtx(),Record_ID,trxName);
		}
		if(tableName.equalsIgnoreCase(MPlanningLine.Table_Name))
		{
			return new MPlanningLine(Env.getCtx(),Record_ID,trxName);
		}
		if(tableName.equalsIgnoreCase(MPlanningLineMA.Table_Name)) {
			return new MPlanningLineMA(Env.getCtx(),Record_ID,trxName);
 
		}
		if(tableName.equalsIgnoreCase(MProdNew.Table_Name)) {
			return new MProdNew(Env.getCtx(),Record_ID,trxName);
 
		}
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MPlanning.Table_Name)) {
			return new MPlanning(Env.getCtx(),rs,trxName);
		}
		if(tableName.equalsIgnoreCase(MPlanningLine.Table_Name)) {
			return new MPlanningLine(Env.getCtx(),rs,trxName);
		}
		if(tableName.equalsIgnoreCase(MPlanningLineMA.Table_Name)) {
			return new MPlanningLineMA(Env.getCtx(),rs,trxName);
		}
		if(tableName.equalsIgnoreCase(MProdNew.Table_Name)) {
			return new MProdNew(Env.getCtx(),rs,trxName);
		}
		return null;
	}

//	@Override
//	public ProcessCall newProcessInstance(String className) {
//		// TODO Auto-generated method stub
//		if(className.equals("org.tcf.process.PlanningCreate")) {
//			return new PlanningCreate();
//		}
//		return null;
//	}

}

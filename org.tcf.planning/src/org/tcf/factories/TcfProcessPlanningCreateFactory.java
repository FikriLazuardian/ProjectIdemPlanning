package org.tcf.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.tcf.process.PlanningCreate;

public class TcfProcessPlanningCreateFactory implements IProcessFactory{


	@Override
	public ProcessCall newProcessInstance(String className) {
		// TODO Auto-generated method stub
		if(className.equals("org.tcf.process.PlanningCreate"))
		return new PlanningCreate();
		return null;
	}

}

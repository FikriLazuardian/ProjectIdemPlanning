package org.tcf.process;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;


import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.tcf.model.I_M_PlanningProduct;
import org.tcf.model.MPlanning;
import org.tcf.model.MPlanningProduct;

@org.adempiere.base.annotation.Process
public class PlanningCreate extends SvrProcess {

	private int p_M_Planning_ID=0;
	private MPlanning m_planning = null;
	private boolean mustBeStocked = false;  //not used
	private boolean recreate = false;
	private BigDecimal newQty = null;
	private int p_PP_Product_BOM_ID=0;
	
	
	protected void prepare() {
		log.warning("Prepare Method");
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if ("Recreate".equals(name))
				recreate = "Y".equals(para[i].getParameter());
			else if ("ProductionPlanQty".equals(name))
				newQty  = (BigDecimal) para[i].getParameter();
			else if ("PP_Product_BOM_ID".equals(name))
				p_PP_Product_BOM_ID = para[i].getParameterAsInt();
			else
				log.log(Level.WARNING, "Unknown Parameter: " + name);		
		}
		
		p_M_Planning_ID = getRecord_ID();
		m_planning = new MPlanning(getCtx(), p_M_Planning_ID, get_TrxName());
		
		
	}	//prepare

	@Override
	protected String doIt() throws Exception {
		log.warning("doIt Method");

		if ( m_planning.get_ID() == 0 )
			throw new AdempiereUserError("Could not load production header");

		if ( m_planning.isProcessed() )
			return "Already processed";

		return createLines();

	}
	
	protected String createLines() throws Exception {
		
		int created = 0;
		if (!m_planning.isUseProductionPlan()) {
			String msg = validateEndProduct(m_planning.getM_Product_ID());
			if (!Util.isEmpty(msg))
				throw new AdempiereUserError(msg);
			
			if (!recreate && "Y".equalsIgnoreCase(m_planning.getIsCreated()))
				throw new AdempiereUserError("Planning already created.");
			
			if (newQty != null )
				m_planning.setProductionPlanQty(newQty);
			
			m_planning.deleteLines(get_TrxName());
			created = m_planning.createLines(mustBeStocked, p_PP_Product_BOM_ID);
		} else {
			Query planQuery = new Query(getCtx(), I_M_PlanningProduct.Table_Name, "M_PlanningProduct.M_Planning_ID=?", get_TrxName());
			List<MPlanningProduct> plans = planQuery.setParameters(m_planning.getM_Planning_ID()).list();
			for(MPlanningProduct plan : plans) {
				validateEndProduct(plan.getM_Product_ID());
				
				if (!recreate && "Y".equalsIgnoreCase(m_planning.getIsCreated()))
					throw new AdempiereUserError("Planning already created.");
				
				plan.deleteLines(get_TrxName());
				int n = plan.createLines(mustBeStocked);
				if ( n == 0 ) 
				{return "Failed to create planning lines"; }
				created = created + n;
			}
		}
		if ( created == 0 ) 
		{return "Failed to create planning lines"; }
		
		
		m_planning.setIsCreated("Y");
		m_planning.save(get_TrxName());
		StringBuilder msgreturn = new StringBuilder().append(created).append(" planning lines were created");
		return msgreturn.toString();
	}

	private String validateEndProduct(int M_Product_ID) throws Exception {
		MPlanning planning = new MPlanning(Env.getCtx(), 0, get_TrxName());
		return planning.validateEndProduct(M_Product_ID);
	}	
	
}

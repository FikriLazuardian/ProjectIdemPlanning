package org.tcf.model;

import java.util.List;
import java.util.Properties;

import org.compiere.model.I_M_ProductionPlan;
import org.compiere.model.MProduction;
import org.compiere.model.MProductionLine;
import org.compiere.model.MProductionPlan;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Env;

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
	
	@Override
	public String completeIt() {

		// Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			m_justPrepared = false;
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		StringBuilder errors = new StringBuilder();
		int processed = 0;
			
		if (!isUseProductionPlan()) {
			MProductionLine[] lines = getLines();
			//IDEMPIERE-3107 Check if End Product in Production Lines exist
			if(!isHaveEndProduct(lines)) {
				m_processMsg = "Production does not contain End Product";
				return DocAction.STATUS_Invalid;
			}
			errors.append(processLines(lines));
			if (errors.length() > 0) {
				m_processMsg = errors.toString();
				return DocAction.STATUS_Invalid;
			}
			processed = processed + lines.length;
		} else {
			Query planQuery = new Query(Env.getCtx(), I_M_ProductionPlan.Table_Name, "M_ProductionPlan.M_Production_ID=?", get_TrxName());
			List<MProductionPlan> plans = planQuery.setParameters(getM_Production_ID()).list();
			for(MProductionPlan plan : plans) {
				MProductionLine[] lines = plan.getLines();
				
				//IDEMPIERE-3107 Check if End Product in Production Lines exist
				if(!isHaveEndProduct(lines)) {
					m_processMsg = String.format("Production plan (line %1$d id %2$d) does not contain End Product", plan.getLine(), plan.get_ID());
					return DocAction.STATUS_Invalid;
				}
				
				if (lines.length > 0) {
					errors.append(processLines(lines));
					if (errors.length() > 0) {
						m_processMsg = errors.toString();
						return DocAction.STATUS_Invalid;
					}
					processed = processed + lines.length;
				}
				plan.setProcessed(true);
				plan.saveEx();
			}
		}

		//		User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}
	
	private boolean isHaveEndProduct(MProductionLine[] lines) {
		
		for(MProductionLine line : lines) {
			if(line.isEndProduct())
				return true;			
		}
		return false;
	}
}

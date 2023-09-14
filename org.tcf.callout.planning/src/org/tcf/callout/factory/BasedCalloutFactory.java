package org.tcf.callout.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.base.AnnotationBasedColumnCalloutFactory;
import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.osgi.service.component.annotations.Component;
import org.tcf.callout.model.CalloutPlanning;
import org.tcf.model.MPlanning;
/**
 * 
 * @author hengsin
 * @author ict.tcf
 *
 */
public class BasedCalloutFactory implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
		// TODO Auto-generated method stub
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();
		if(tableName.equalsIgnoreCase(MPlanning.Table_Name)&& columnName.equalsIgnoreCase(MPlanning.COLUMNNAME_Description))
			list.add(new CalloutPlanning());
		return list != null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}
	

}

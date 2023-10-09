package org.tcf.callout;

import org.adempiere.base.AnnotationBasedColumnCalloutFactory;
import org.adempiere.base.IColumnCalloutFactory;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = IColumnCalloutFactory.class)
public class CalloutFactory extends AnnotationBasedColumnCalloutFactory{

	@Override
	protected String[] getPackages() {
		// TODO Auto-generated method stub
		return new String[] {"org.tcf.model"};
	}


}

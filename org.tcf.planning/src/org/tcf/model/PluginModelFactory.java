package org.tcf.model;

import org.adempiere.base.AnnotationBasedModelFactory;
import org.osgi.service.component.annotations.Component;

@Component
public class PluginModelFactory extends AnnotationBasedModelFactory {

	@Override
	protected String[] getPackages() {
		// TODO Auto-generated method stub
		return new String[] {"org.tcf.model"};
	}


}

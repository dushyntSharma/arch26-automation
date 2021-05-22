package com.mindtree.test.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.mindtree.test.core.services.PracticeService;

@Model(adaptables = Resource.class, adapters = PraticeModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PractiseModelImpl implements PraticeModel {

	@OSGiService
	PracticeService practiceService;

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return practiceService.name();
	}

	@Override
	public boolean account() {
		// TODO Auto-generated method stub
		return practiceService.account();
	}

	@Override
	public String[] deposit() {
		// TODO Auto-generated method stub
		return practiceService.deposit();
	}

	@Override
	public String bank() {
		// TODO Auto-generated method stub
		return practiceService.bank();
	}

}

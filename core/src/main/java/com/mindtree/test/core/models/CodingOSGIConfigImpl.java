package com.mindtree.test.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.mindtree.test.core.services.CodingChallengeService;

@Model(adaptables = Resource.class, adapters = CodingOSGIConfig.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CodingOSGIConfigImpl implements CodingOSGIConfig {

	@OSGiService
	CodingChallengeService codingChallengeService;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return codingChallengeService.getName();
	}

	@Override
	public boolean getChallenge() {
		// TODO Auto-generated method stub
		return codingChallengeService.getChallenge();
	}

	@Override
	public String[] getSkills() {
		// TODO Auto-generated method stub
		return codingChallengeService.getSkills();
	}

	@Override
	public String getCompany() {
		// TODO Auto-generated method stub
		return codingChallengeService.getCompany();
	}

}

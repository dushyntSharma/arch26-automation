package com.mindtree.test.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import com.mindtree.test.core.components.CodingChallengeFactoryConfig;

@Component(service = CodingChallengeService.class, immediate = true)
@Designate(ocd = CodingChallengeFactoryConfig.class)
public class CodingChallengeServiceImpl implements CodingChallengeService {

	private String name;
	private boolean challenge;
	private String[] skills;
	private String company;

	@Activate
	@Modified
	protected void activate(CodingChallengeFactoryConfig codingChallengeFactoryConfig) {

		name = codingChallengeFactoryConfig.name();
		challenge = codingChallengeFactoryConfig.challenge();
		skills = codingChallengeFactoryConfig.skills();
		company = codingChallengeFactoryConfig.company();

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean getChallenge() {
		// TODO Auto-generated method stub
		return challenge;
	}

	@Override
	public String[] getSkills() {
		// TODO Auto-generated method stub
		return skills;
	}

	@Override
	public String getCompany() {
		// TODO Auto-generated method stub
		return company;
	}

}

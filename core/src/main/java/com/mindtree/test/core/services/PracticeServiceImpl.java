package com.mindtree.test.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import com.mindtree.test.core.components.PracticeFactoryConfig;

@Component(service = PracticeService.class, immediate = true)
@Designate(ocd = PracticeFactoryConfig.class)
public class PracticeServiceImpl implements PracticeService {

	private String name;
	private boolean account;
	private String[] deposit;
	private String bank;

	@Activate
	@Modified
	public void activate(PracticeFactoryConfig practiceFactoryConfig) {
		name = practiceFactoryConfig.name();
		account = practiceFactoryConfig.account();
		deposit = practiceFactoryConfig.deposit();
		bank = practiceFactoryConfig.bank();
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean account() {
		// TODO Auto-generated method stub
		return account;
	}

	@Override
	public String[] deposit() {
		// TODO Auto-generated method stub
		return deposit;
	}

	@Override
	public String bank() {
		// TODO Auto-generated method stub
		return bank;
	}

}

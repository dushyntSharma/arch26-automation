package com.mindtree.test.core.components;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;
import org.osgi.service.metatype.annotations.AttributeType;

@ObjectClassDefinition(name = "Practice Factory", description = "Practising the osgi config")
public @interface PracticeFactoryConfig {
	@AttributeDefinition(name = "name", description = "Enter the name", type = AttributeType.STRING)
	String name();

	@AttributeDefinition(name = "account type", description = "Choose the account type", type = AttributeType.BOOLEAN)
	boolean account();

	@AttributeDefinition(name = "deposit", description = "Enter the deposit amount", type = AttributeType.STRING)
	String[] deposit();

	@AttributeDefinition(name = "bank", description = "Choose the bank", options = {
			@Option(label = "HDFC", value = "HDFC"), @Option(label = "AXIS", value = "AXIS"),
			@Option(label = "SBI", value = "SBI") }, type = AttributeType.STRING)
	String bank();

}

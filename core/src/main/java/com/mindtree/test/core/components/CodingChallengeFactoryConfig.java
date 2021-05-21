package com.mindtree.test.core.components;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "Coding Challenge", description = "Coding Challenge Implementation")
public @interface CodingChallengeFactoryConfig {
	@AttributeDefinition(name = "Name", description = "Enter the name", type = AttributeType.STRING)
	String name();

	@AttributeDefinition(name = "Challenge", description = "Challenge completed?", type = AttributeType.BOOLEAN)
	boolean challenge();

	@AttributeDefinition(name = "Sklils", description = "Add your skills", type = AttributeType.STRING)
	String[] skills();

	@AttributeDefinition(name = "Company", description = "Select Company", options = {
			@Option(label = "Mindtree", value = "mindtree"), @Option(label = "Wipro", value = "wipro"),
			@Option(label = "TCS", value = "tcs") }, type = AttributeType.STRING)
	String company();

}

package com.mindtree.test.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.mindtree.test.core.services.PractiseCSVreader;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PractiseCSVModel {

	@OSGiService
	PractiseCSVreader practiseCSVreader;

	public String getCSVFile() {
		return practiseCSVreader.getCSVFile();
	}

	public List<String> getPage() {
		return practiseCSVreader.getPage();
	}

}

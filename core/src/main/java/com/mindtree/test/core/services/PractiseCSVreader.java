package com.mindtree.test.core.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;

@Component(service = PractiseCSVreader.class, immediate = true)
public class PractiseCSVreader {
	private static final Logger log = LoggerFactory.getLogger(PractiseCSVreader.class);

	@Reference
	ResourceResolverFactory resourceResolverFactory;

	ResourceResolver resourceResolver = null;

	private static final String USER = "newVariable";

	private static final String PATH = "/content/dam/TestAEM/CSV/reader.csv";

	String path = null;

	@Activate
	@Modified
	public void activateMethod() {
		log.info("============================================================");
		log.info("Resource is in the activate method");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ResourceResolverFactory.SUBSERVICE, USER);
		try {
			resourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
			log.info("Resource Resolver registerd");
			log.info("============================================================");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public String getCSVFile() {
		log.info("===================================================================");
		log.info("Reousrce is in the getCSVFile");
		try {
			Resource resource = resourceResolver.getResource(PATH);
			Asset asset = resource.adaptTo(Asset.class);
			Rendition rendition = asset.getOriginal();
			InputStream inputStream = rendition.adaptTo(InputStream.class);
			path = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			log.info(PATH);
			log.info("==============================================");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return path;

	}

	public List<String> getPage() {

		log.info("=====================================================================================");
		log.info("CSV File reading using the Buffered reader");
		List<String> lines = new ArrayList<String>();

		log.info("The control is coming inside the GetPage");

		try {

			Resource resource = resourceResolver.getResource(PATH);

			Asset asset = resource.adaptTo(Asset.class);
			Rendition rendition = asset.getOriginal();
			InputStream inputStream = rendition.adaptTo(InputStream.class);
			try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream))) {
				String line = "";
				while ((line = bufferReader.readLine()) != null) {
					log.info(line);
					lines.add(line);
				}

				log.info("=====================================================================================");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return lines;

	}

}

package com.mindtree.test.core.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

@Component(service = ReviewCSVPage.class, immediate = true)
public class ReviewCSVPageImpl implements ReviewCSVPage {

	@Reference
	ResourceResolverFactory resolverFactory;
	private final Logger logger = LoggerFactory.getLogger(ReviewCSVPageImpl.class);
	ResourceResolver resolver;
	Resource resource;
	private final String SYSTEM_USER = "newService";

	@Activate
	public void Activate() {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("CSVFileReaderImpl activated");
		map.put(ResourceResolverFactory.SUBSERVICE, (Object) SYSTEM_USER);
		try {
			resolver = resolverFactory.getServiceResourceResolver(map);

		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

	public int createPagesWithCSVFileDemo() {
		resource = resolver.getResource("/content/dam/TestAEM/CSV/csv_content1.csv");
		Asset asset = resource.adaptTo(Asset.class);
		Rendition rendition = asset.getOriginal();
		InputStream inputStream = rendition.adaptTo(InputStream.class);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		Session session = resolver.adaptTo(Session.class);

		String line = "";
		List<Page> createdPages = new ArrayList<Page>();
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		try {
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] pageData = line.split(",");

				String pageParent = pageData[4].trim();
				String pageDescription = pageData[3].trim();
				String pageTitle = pageData[2].trim();
				String pageTemplate = pageData[1].trim();
				String pageName = pageData[0].trim().replace(' ', '-');
				Page page = pageManager.create(pageParent, pageName, pageTemplate, pageTitle);

				Node node;
				try {
					node = (Node) session.getItem(page.getPath() + "/jcr:content");

					if (node != null) {
						logger.info(node.getName());
						node.setProperty("jcr:description", pageDescription);
						node.setProperty("pageTitle", pageTitle);
						node.setProperty("navTitle", "nav " + pageTitle);
						session.save();
					} else {
						logger.info("Node is null");
					}

				} catch (PathNotFoundException e) {
					e.printStackTrace();
				} catch (RepositoryException e) {
					e.printStackTrace();
				}

				createdPages.add(page);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WCMException e) {
			logger.info(e.getMessage());
			logger.info(e.toString());

		}
		return createdPages.size();
	}
}

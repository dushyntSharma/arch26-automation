package com.mindtree.test.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.mindtree.test.core.services.SiteMapGenerator;

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "SlingTest/components/structure/page", selectors = "sitemap", extensions = "xml")
public class SiteMapGeneratorServlet extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Reference
	SiteMapGenerator siteMapGenerator;

	@Override
	protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("application/xml");
		siteMapGenerator.generateSiteMap(req.getResource(), res.getWriter());
	}
}

package com.mindtree.test.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.mindtree.test.core.services.ReviewCSVPage;

@Component(service = Servlet.class)
@SlingServletPaths(value = { "/bin/reviewfilecsv" })
public class ReviewCSVServlet extends SlingSafeMethodsServlet {

	private final Logger logger = LoggerFactory.getLogger(ReviewCSVServlet.class);
	@Reference
	ReviewCSVPage reviewCSVPage;

	@Override
	public void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {
		int totalPagesCreated = reviewCSVPage.createPagesWithCSVFileDemo();
		logger.info("==================================================");
		logger.info(totalPagesCreated + " pages created successfully");
//		res.sendRedirect("/content/AemSite/en/firstpage.html");
		res.getWriter().write(totalPagesCreated + " pages created successfully");
	}
}

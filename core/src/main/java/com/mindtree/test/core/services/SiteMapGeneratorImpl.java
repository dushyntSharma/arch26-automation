package com.mindtree.test.core.services;

import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

public class SiteMapGeneratorImpl implements SiteMapGenerator {

	private static final Logger Log = LoggerFactory.getLogger(SiteMapGeneratorImpl.class);
	@Reference
	ResourceResolverFactory resourceResolverFactory;

	ResourceResolver resourceResolver = null;
	private static final String SITEMAP_LOCATION = "http://localhost:4505/";
	private static final String SITEMAP_NAMESPACE = "http://www.sitemaps.org/schemas/sitemap/0.9";
	private static final FastDateFormat DATE = FastDateFormat.getInstance("YYYY-MM-DD");
	public static final String SERVICE_NAME = "newService";

	@Activate
	@Modified
	public void activate() {

		try {
			Log.info("===================================================================");
			Log.info("Resource is inside the Activate Method");

			Map<String, Object> map = new HashMap();
			map.put(ResourceResolverFactory.SUBSERVICE, SERVICE_NAME);

			resourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
			Log.info("Inside getResourceResolver");
			Log.info("===================================================================");
		} catch (LoginException e) {
			Log.info("===================================================================");
			Log.info("Inside getResourceResolver failed" + e.getMessage());
			Log.info("===================================================================");
		}

	}

	@Override
	public void generateSiteMap(Resource siteResource, Writer writer) {
		try {
			PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
			Page mainPage = pageManager.getContainingPage(siteResource);

			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();

			XMLStreamWriter streamWriter = xmlOutputFactory.createXMLStreamWriter(writer);

			// Creating the URL (urlset)
			streamWriter.writeStartDocument("1.0");
			streamWriter.writeStartElement("", "urlset", SITEMAP_NAMESPACE);
			streamWriter.writeNamespace("", SITEMAP_NAMESPACE);

			innerPage(mainPage, streamWriter);
			// Ending of the URL (urlset)
			streamWriter.writeEndElement();
			streamWriter.writeEndDocument();
		} catch (XMLStreamException e) {

			e.printStackTrace();
		}

	}

	private void innerPage(Page mainPage, XMLStreamWriter streamWriter) throws XMLStreamException {
		xmlWriter(mainPage, streamWriter);
		for (Iterator<Page> children = mainPage.listChildren(); children.hasNext();) {
			Page childPage = children.next();
			if (childPage.listChildren().hasNext()) {
				innerPage(childPage, streamWriter);
			} else {
				xmlWriter(childPage, streamWriter);
			}

		}

	}

	private void xmlWriter(Page mainPage, XMLStreamWriter streamWriter) throws XMLStreamException {
		// TODO Auto-generated method stub

		streamWriter.writeStartElement(SITEMAP_NAMESPACE, "url");
		XMLElementWriter(streamWriter, "location", SITEMAP_LOCATION + mainPage.getName());

		XMLElementWriter(streamWriter, "last_modified", DATE.format(mainPage.getLastModified()));

		XMLElementWriter(streamWriter, "change_frequency", "weekly");
		streamWriter.writeEndElement();
	}

	private void XMLElementWriter(XMLStreamWriter streamWriter, String element, String value)
			throws XMLStreamException {
		// Creating the XML element or starting XML element
		streamWriter.writeStartElement(SITEMAP_NAMESPACE, element);
		// Writing the value inside the XML tag
		streamWriter.writeCharacters(value);
		// Creating the ending tag
		streamWriter.writeEndElement();
	}

}

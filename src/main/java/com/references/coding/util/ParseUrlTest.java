package com.references.coding.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseUrlTest {

	private static final Logger LOG = LoggerFactory.getLogger(ParseUrlTest.class);

	public static void main(String[] args) {

		String urlOrigin = "http://www.bbc.co.uk/news/";
		LOG.info("*****************Start get Base Url*********************** ");
		LOG.info("New BaseUrl : " + HtmlParserUtil.getBaseUrl(urlOrigin));
		LOG.info("*****************End get base Url********************** ");

		LOG.info("*****************Start get content Html *********************** ");
		String url = "http://www.bbc.co.uk/news/";
		String hmtlContent = " ";
		hmtlContent = HtmlParserUtil.getHtmlContent(url);
		System.out.println("**************************************** ");
		LOG.info("*****************End get content Html *********************** ");

	}

}

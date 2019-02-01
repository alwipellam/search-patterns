package com.references.coding.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlParserUtil {

	private static final Logger LOG = LoggerFactory.getLogger(HtmlParserUtil.class);
	private static final int CONNECTION_TIMEOUT = 10000;

	/**
	 * @param urlOrigin
	 * @return the code return the main url from origin url
	 */
	@SuppressWarnings("unused")
	public static String getBaseUrl(String urlOrigin) {

		URL url;
		String baseUrl = null;
		try {
			url = new URL(urlOrigin.trim());
			baseUrl = url.getProtocol() + "://" + url.getHost();
			// System.out.println("baseUrl : " + baseUrl);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return baseUrl;

	}

	public static String getBaseUrlPath(String urlOrigin) {

		URL url;
		String baseUrl = null;
		try {
			url = new URL(urlOrigin.trim());

			String path = url.getFile().substring(0, url.getFile().lastIndexOf('/'));
			baseUrl = url.getProtocol() + "://" + url.getHost() + path;
			// System.out.println("baseUrl : " + baseUrl);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return baseUrl;

	}

	/**
	 * @param url
	 * @return The code uses JSoup to download and print a tiny web page.
	 */
	public static String getHtmlContent(String url) {

		String html = "";
		try {
			Connection connection = Jsoup.connect(getBaseUrl(url.trim()));
			connection.timeout(CONNECTION_TIMEOUT);
			html = connection.get().html();
		} catch (IOException e) {
			LOG.info("Unable to fetch url - " + url + " - " +" - Error message - " + e.getMessage());
			// LOG.info("Error message - " + url + " - " + e.getMessage());
			// e.printStackTrace();
			return html = null;
		}
		return html;
	}

}

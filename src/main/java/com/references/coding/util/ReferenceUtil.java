package com.references.coding.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.references.coding.directory.FileReadUtil;
import com.references.coding.directory.FileWriterUtil;
import com.references.coding.domain.PatternMatcher;
import com.references.coding.domain.Reference;
import com.references.coding.enums.FileErrorCode;
import com.references.coding.enums.ReferencePattern;
import com.references.coding.exception.SystemException;

public final class ReferenceUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(FileWriterUtil.class);
	private static final String HTTP = "http://";

	private ReferenceUtil() {
	}

	/**
	 * 
	 * @param patterns
	 *            patterns List
	 * @param filePath
	 *            file path
	 * @return the list of matching patterns
	 */
	public static List<Reference> getInputReferences(String filePath, List<ReferencePattern> patterns) {

			    
		if (CollectionUtils.isEmpty(patterns)) {
			throw new SystemException(FileErrorCode.PATTERN_INVALID);
		}

		List<String> urls = getUrlsByFilePath(filePath);

		/***** Here We Are Creating A 'Parallel Stream' & Displaying The Result *****/	
		List<ReferencePattern> unmodifiablePatterns = Collections.unmodifiableList(patterns);
		return urls.parallelStream().map(url -> {
			return new Reference(url, getPatternsMatches(unmodifiablePatterns, url));
		}).collect(Collectors.toList());	
		

	}
	
	public static List<Reference> getInputReferencesByStream(String filePath, List<ReferencePattern> patterns) {

		/***** Here We Are Creating A 'Stream' & Displaying The Result *****/	    
		if (CollectionUtils.isEmpty(patterns)) {
			throw new SystemException(FileErrorCode.PATTERN_INVALID);
		}

		List<String> urls = getUrlsByFilePath(filePath);
			
		List<ReferencePattern> unmodifiablePatterns = Collections.unmodifiableList(patterns);
		return urls.stream().map(url -> {
			return new Reference(url, getPatternsMatches(unmodifiablePatterns, url));
		}).collect(Collectors.toList());	
		

	}

	private static List<String> getUrlsByFilePath(String filePath) {

		return FileReadUtil.readFileAndProcessing(filePath);
	}

	private static List<PatternMatcher> getPatternsMatches(List<ReferencePattern> patterns, String url) {

		if (url.contains(HTTP) && !CollectionUtils.isEmpty(patterns)) {
			String htmlContent = HtmlParserUtil.getHtmlContent(url);
			if (!StringUtils.isEmpty(htmlContent) || htmlContent != null) {
				return getMatchingPatternsByHtmlContent(patterns, htmlContent);
			}
		}
		return new ArrayList<>();

	}

	private static List<PatternMatcher> getMatchingPatternsByHtmlContent(List<ReferencePattern> patterns,
			String htmlContent) {

		return patterns.stream().map(p -> {
			return new PatternMatcher(p, getPatternMatches(htmlContent, p));
		}).collect(Collectors.toList());

	}

	private static List<String> getPatternMatches(String htmlContent, ReferencePattern referencePattern) {
		Pattern pattern = Pattern.compile(referencePattern.getRegex());
		Matcher matcher = pattern.matcher(htmlContent);
		List<String> result = new ArrayList<>();

		while (matcher.find()) {
			result.add(matcher.group(1));
		}
		return result;
	}

	/**
	 * 
	 * yahoo.com hashtags: - #something - #something else
	 * 
	 * twitter: - @myaccount - @myotheracount
	 *
	 * facebook.com hashtags: - #something - #something else
	 * 
	 * twitter: - @myaccount - @myotheracount
	 * 
	 */

}

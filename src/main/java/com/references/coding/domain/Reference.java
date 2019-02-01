package com.references.coding.domain;

import java.util.List;

public class Reference {

	private String url;
	private List<PatternMatcher> resultsByPattern;

	public Reference(String url, List<PatternMatcher> resultsByPattern) {
		super();
		this.url = url;
		this.resultsByPattern = resultsByPattern;
	}

	public String getUrl() {
		return url;
	}

	public List<PatternMatcher> getResultsByPattern() {
		return resultsByPattern;
	}

	@Override
	public String toString() {
		return "Reference [url=" + url + ", resultsByPattern=" + resultsByPattern + "]";
	}
	
	

}

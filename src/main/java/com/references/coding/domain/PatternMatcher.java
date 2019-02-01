package com.references.coding.domain;

import java.util.List;

import com.references.coding.enums.ReferencePattern;

public class PatternMatcher {

	private ReferencePattern pattern;
	private List<String> matches;

	public PatternMatcher(ReferencePattern pattern, List<String> matches) {
		super();
		this.pattern = pattern;
		this.matches = matches;
	}

	public ReferencePattern getPattern() {
		return pattern;
	}

	public List<String> getMatches() {
		return matches;
	}

	@Override
	public String toString() {
		return "PatternMatcher [pattern=" + pattern + ", matches=" + matches + "]";
	}

	
}

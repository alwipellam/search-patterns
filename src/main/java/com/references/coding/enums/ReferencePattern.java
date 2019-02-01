package com.references.coding.enums;

public enum ReferencePattern {

	HASH_TAG("hashTag", "#(\\S+)"), 
	TWITTER_ACCOUNT("twitter", "@(\\S+)"), 
	PROPER_NAME("properName", "^[\\p{L} .'-]+$");

	private String idReference;
	private String regex;

	private ReferencePattern(String idReference, String regex) {
		this.idReference = idReference;
		this.regex = regex;
	}

	public String getIdReference() {
		return idReference;
	}
	
	public String getRegex() {
		return regex;
	}

	public static ReferencePattern getReferenceById(String id) {
		for (ReferencePattern reference : ReferencePattern.values()) {
			if (reference.getIdReference().equals(id)) {
				return reference;
			}
		}
		throw new IllegalArgumentException("The given reference pattern \"" + id + "\" doesn't match any value.");
	}

}

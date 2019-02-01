package com.references.coding.enums;

import com.references.coding.exception.ErrorCode;

public enum FileErrorCode implements ErrorCode {

	GENERIC_BUSINESS_ERROR("GENERIC BUSINESS ERROR", " Any Service is not working"), 
	FILE_PATH_INVALID("0000","The path is not valid"),
	PATTERN_INVALID("0001","The pattern is not valid");

	private String code;
	private String description;

	private FileErrorCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

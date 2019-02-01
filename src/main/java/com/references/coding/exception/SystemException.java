package com.references.coding.exception;

import com.references.coding.enums.FileErrorCode;

public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1306694556408712398L;

    private final ErrorCode errorCode;

    // The exception message will be the errorCode.descriptionMessage
    public SystemException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    // The exception message will be customized using the @errorMessage argument
    public SystemException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    // The exception message will be the same of the @cause
    public SystemException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public SystemException(ErrorCode errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
    }

	public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return String.format("SystemException [errorCode=%s, errorMessage=%s]", errorCode, this.getLocalizedMessage());
    }

}

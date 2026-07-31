package com.tgd.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class ErrorDetail {
	@Schema(description = "A clear description detailing why the request failed", example = "Requested owner's name (200) characters exceeds the limit (100)")
	private final String message;
	@Schema(description = "The specific Java exception class type that triggered this response", example = "IllegalArgumentException")
	private final String errorType;
	@Schema(description = "The standard HTTP network response status code number value", example = "400")
	private final int errorCode;

	public ErrorDetail(String message, String errorType, int errorCode) {
		super();
		this.message = message;
		this.errorType = errorType;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public String getErrorType() {
		return errorType;
	}

	public int getErrorCode() {
		return errorCode;
	}
}

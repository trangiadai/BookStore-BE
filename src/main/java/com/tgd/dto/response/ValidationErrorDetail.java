package com.tgd.dto.response;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

public class ValidationErrorDetail {
	@Schema(description = "A map matching failed DTO properties to their specific validation messages", example = "{\"name\": \"Owner's name can not be empty or just a space/tab\", \"phone\": \"Owner's phone can not be empty or just a space/tab\"}")
	private final Map<String, String> messages;
	@Schema(description = "The specific Java exception class type that triggered this response", example = "MethodArgumentNotValidException")
	private final String errorType;
	@Schema(description = "The standard HTTP network response status code number value", example = "400")
	private final int errorCode;

	public ValidationErrorDetail(Map<String, String> messages, String errorType, int errorCode) {
		super();
		this.messages = messages;
		this.errorType = errorType;
		this.errorCode = errorCode;
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public String getErrorType() {
		return errorType;
	}

	public int getErrorCode() {
		return errorCode;
	}
}

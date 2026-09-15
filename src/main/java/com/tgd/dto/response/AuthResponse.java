package com.tgd.dto.response;

public class AuthResponse {
	private String accessToken;
	private String tokenType = "Bearer";

	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
}

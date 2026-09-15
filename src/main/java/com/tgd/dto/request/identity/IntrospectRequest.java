package com.tgd.dto.request.identity;

import jakarta.validation.constraints.NotBlank;

public class IntrospectRequest {
	@NotBlank(message = "Token must not be blank")
	private String token;

	public IntrospectRequest(String token) {
		super();
		this.token = token;
	}

	public IntrospectRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

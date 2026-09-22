package com.tgd.dto.response.identity;

public class IntrospectResponse {
	private boolean valid;

	public IntrospectResponse(boolean valid) {
		super();
		this.valid = valid;
	}

	public IntrospectResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}

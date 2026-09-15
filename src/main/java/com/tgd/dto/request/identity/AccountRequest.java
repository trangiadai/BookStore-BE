package com.tgd.dto.request.identity;

public class AccountRequest {
	private String email;
	private String oldPassWord;
	private String newPassWord;

	public AccountRequest(String email, String oldPassWord, String newPassWord) {
		super();
		this.email = email;
		this.oldPassWord = oldPassWord;
		this.newPassWord = newPassWord;
	}

	public AccountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPassWord() {
		return oldPassWord;
	}

	public void setOldPassWord(String oldPassWord) {
		this.oldPassWord = oldPassWord;
	}

	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}
}

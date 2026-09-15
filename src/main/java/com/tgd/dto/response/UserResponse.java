package com.tgd.dto.response;

public class UserResponse {
	private String fullName;
	private String phoneNumber;
	private String address;

	public UserResponse(String fullName, String phoneNumber, String address) {
		super();
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}

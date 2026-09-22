package com.tgd.dto.request.identity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequest {
	@NotBlank(message = "Full name cannot be blank")
	@Size(max = 100, message = "The maximum length of full name is 100")
	private String fullName;

	@NotBlank(message = "Phone number cannot be blank")
	@Size(max = 20, message = "The maximum length of phone number is 20")
	@Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only numbers")
	private String phoneNumber;

	@NotBlank(message = "Address cannot be blank")
	@Size(min = 3, max = 1000, message = "Address must be between 3 and 1000 characters")
	private String address;

	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRequest(String fullName, String phoneNumber, String address) {
		super();
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.address = address;
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

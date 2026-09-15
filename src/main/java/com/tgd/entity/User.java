package com.tgd.entity;

public class User {
	private Long id;
	private Long accountId;
	private String fullName;
	private String phoneNumber;
	private String address;

	public User(Long id, Long accountId, String fullName, String phoneNumber, String address) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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

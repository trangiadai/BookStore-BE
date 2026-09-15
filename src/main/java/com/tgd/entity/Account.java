package com.tgd.entity;

import java.time.LocalDateTime;

public class Account {
	private Long id;
	private String email;
	private String passwordHash;
	private String role;
	private boolean enabled;
	private LocalDateTime createdAt;

	public Account(Long id, String email, String passwordHash, String role, boolean enabled, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.email = email;
		this.passwordHash = passwordHash;
		this.role = role;
		this.enabled = enabled;
		this.createdAt = createdAt;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}

package com.tgd.entity;

import java.time.LocalDateTime;

import com.tgd.enums.OrphanedFileStatus;

public class OrphanedFile {

	private Long id;
	private String publicId;
	private String status;
	private Integer retryCount;
	private String lastError;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// Default Constructor
	public OrphanedFile() {
		this.status = OrphanedFileStatus.PENDING.name();
		this.retryCount = 0;
	}

	public OrphanedFile(Long id, String publicId, String status, Integer retryCount, String lastError,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.publicId = publicId;
		this.status = status;
		this.retryCount = retryCount;
		this.lastError = lastError;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	// Convenient Constructor for creating new outbox entries
	public OrphanedFile(String publicId) {
		this.publicId = publicId;
		this.status = OrphanedFileStatus.PENDING.name();
		this.retryCount = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public String getLastError() {
		return lastError;
	}

	public void setLastError(String lastError) {
		this.lastError = lastError;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "OrphanedFile{" + "id=" + id + ", publicId='" + publicId + '\'' + ", status='" + status + '\''
				+ ", retryCount=" + retryCount + ", lastError='" + lastError + '\'' + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + '}';
	}
}

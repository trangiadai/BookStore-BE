package com.tgd.entity;

public class ProductImage {
	private Long id;
	private String url;
	private String publicId;
	private Long productId;
	private Boolean isPrimary;
	private Integer displayOrder;

	public ProductImage(Long id, String url, String publicId, Long productId, Boolean isPrimary, Integer displayOrder) {
		super();
		this.id = id;
		this.url = url;
		this.publicId = publicId;
		this.productId = productId;
		this.isPrimary = isPrimary;
		this.displayOrder = displayOrder;
	}

	public ProductImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
}

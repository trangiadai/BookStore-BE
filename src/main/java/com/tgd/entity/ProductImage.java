package com.tgd.entity;

public class ProductImage {
	private Long id;
	private String url;
	private String publicId;
	private long productId;

	public ProductImage(Long id, String url, String publicId, long productId) {
		super();
		this.id = id;
		this.url = url;
		this.publicId = publicId;
		this.productId = productId;
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

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

}

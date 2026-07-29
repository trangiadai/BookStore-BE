package com.tgd.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Product {
	private Long id;
	private String name;
	private String category;
	private BigDecimal importPrice;
	private BigDecimal sellingPrice;
	private Integer quantity;
	private String description;
	private Set<ProductImage> imageUrls = new HashSet<>();
	private LocalDateTime createdAt;

	public Product(Long id, String name, String category, BigDecimal importPrice, BigDecimal sellingPrice,
			Integer quantity, String description, Set<ProductImage> imageUrls, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.importPrice = importPrice;
		this.sellingPrice = sellingPrice;
		this.quantity = quantity;
		this.description = description;
		this.imageUrls = imageUrls;
		this.createdAt = createdAt;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(BigDecimal importPrice) {
		this.importPrice = importPrice;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<ProductImage> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(Set<ProductImage> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}

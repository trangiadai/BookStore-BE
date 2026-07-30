package com.tgd.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import com.tgd.entity.ProductImage;
import com.tgd.enums.ProductCategory;

public class ProductResponseDTO {
	private Long id;
	private String name;
	private ProductCategory productCategory;
	private BigDecimal importPrice;
	private BigDecimal sellingPrice;
	private Integer quantity;
	private String description;
	private Set<ProductImage> productImages;
	private LocalDateTime createdAt;

	public ProductResponseDTO(Long id, String name, ProductCategory productCategory, BigDecimal importPrice,
			BigDecimal sellingPrice, Integer quantity, String description, Set<ProductImage> productImages,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.productCategory = productCategory;
		this.importPrice = importPrice;
		this.sellingPrice = sellingPrice;
		this.quantity = quantity;
		this.description = description;
		this.productImages = productImages;
		this.createdAt = createdAt;
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

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
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

	public Set<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public ProductResponseDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

}

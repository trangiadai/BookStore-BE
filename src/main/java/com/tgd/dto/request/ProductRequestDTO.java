package com.tgd.dto.request;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public class ProductRequestDTO {
	private String name;
	private BigDecimal importPrice;
	private BigDecimal sellingPrice;
	private Integer quantity;
	private String description;
	private String productCategory;
	private Set<MultipartFile> productImages;

	public ProductRequestDTO(String name, BigDecimal importPrice, BigDecimal sellingPrice, Integer quantity,
			String description, String productCategory, Set<MultipartFile> productImages) {
		super();
		this.name = name;
		this.importPrice = importPrice;
		this.sellingPrice = sellingPrice;
		this.quantity = quantity;
		this.description = description;
		this.productCategory = productCategory;
		this.productImages = productImages;
	}

	public ProductRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Set<MultipartFile> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<MultipartFile> productImages) {
		this.productImages = productImages;
	}

}

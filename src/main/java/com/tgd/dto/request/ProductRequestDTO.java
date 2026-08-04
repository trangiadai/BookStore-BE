package com.tgd.dto.request;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ProductRequestDTO {

	@NotBlank(message = "The product name can't be empty or spaces/tabs")
	@Size(max = 100, min = 2, message = "The maximun length of product name is 100 characters, and the minimun is 2 characters")
	@Schema(description = "Product Name", example = "Wireless Mouse") // Fixes Size validation in Swagger
	private String name;

	@NotNull(message = "The importPrice can't be empty")
	@DecimalMin(value = "0.01", message = "The minimun value for importPrice is 0.01")
	@DecimalMax(value = "999999999999.99", message = "The maximun value for importPrice is 999,999,999,999.99")
	@Schema(description = "Import Price", example = "15.50")
	private BigDecimal importPrice;

	@NotNull(message = "The sellingPrice can't be empty")
	@DecimalMin(value = "0.01", message = "The minimun value for sellingPrice is 0.01")
	@DecimalMax(value = "999999999999.99", message = "The maximun value for sellingPrice is 999,999,999,999.99")
	@Schema(description = "Selling Price", example = "29.99")
	private BigDecimal sellingPrice;

	@NotNull(message = "The quality of the product can't be empty or invalid value")
	@PositiveOrZero(message = "The quality of the product must be possitive")
	@Schema(description = "Quantity in stock", example = "100")
	private Integer quantity;

	@Schema(description = "Description", example = "Ergonomic wireless mouse with high precision sensor.")
	private String description;

	@NotNull(message = "The category of product can't be empty or invalid value")
	@Schema(description = "The category of the product", example = "TECHNOLOGY")
	private String productCategory;

//	@ArraySchema(schema = @Schema(type = "string", format = "binary"), arraySchema = @Schema(description = "Optional product images", nullable = true, requiredMode = Schema.RequiredMode.NOT_REQUIRED))
//	private Set<MultipartFile> productImages;

	public ProductRequestDTO(String name, BigDecimal importPrice, BigDecimal sellingPrice, Integer quantity,
			String description, String productCategory) {
		super();
		this.name = name;
		this.importPrice = importPrice;
		this.sellingPrice = sellingPrice;
		this.quantity = quantity;
		this.description = description;
		this.productCategory = productCategory;
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

}

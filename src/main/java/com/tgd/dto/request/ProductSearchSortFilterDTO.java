package com.tgd.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tgd.enums.SortDirection;
import com.tgd.enums.SortField;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductSearchSortFilterDTO {
	@Size(max = 100, message = "Search term cannot exceed 100 characters")
	private String name;

	@Positive(message = "The categoryId must be positive number")
	@Schema(example = "1")
	private Long categoryId;

	@DecimalMin(value = "0.0", message = "Min price cannot be negative")
	@DecimalMax(value = "9999999999.99", message = "Min price cannot be over 9999999999.99")
	@Schema(example = "1.00")
	private BigDecimal minPrice;

	@DecimalMin(value = "0.0", message = "Max price cannot be negative")
	@DecimalMax(value = "9999999999.99", message = "Max price cannot be over 9999999999.99")
	@Schema(example = "1000000000.00")
	private BigDecimal maxPrice;
	
	@Schema(description = "The start date of filter", example = "2026-07-30 21:35:18")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdFrom;
	
	@Schema(description = "The end date of filter", example = "2026-12-08 21:35:18")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdTo;
	
	private SortField sortBy = SortField.CREATED_AT;
	private SortDirection sortDirection = SortDirection.DESC;
	
	@JsonIgnore
    @AssertTrue(message = "minPrice must be less than or equal to maxPrice")
    public boolean isPriceRangeValid() {
        if (minPrice == null || maxPrice == null) {
            return true; 
        }
        return minPrice.compareTo(maxPrice) <= 0;
    }

    @JsonIgnore
    @AssertTrue(message = "createdFrom date must be before or equal to createdTo date")
    public boolean isDateRangeValid() {
        if (createdFrom == null || createdTo == null) {
            return true;
        }
        return !createdFrom.isAfter(createdTo);
    }

	public ProductSearchSortFilterDTO(String name, Long categoryId, BigDecimal minPrice, BigDecimal maxPrice,
			LocalDateTime createdFrom, LocalDateTime createdTo, SortField sortBy, SortDirection sortDirection) {
		super();
		this.name = name;
		this.categoryId = categoryId;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.createdFrom = createdFrom;
		this.createdTo = createdTo;
		this.sortBy = sortBy;
		this.sortDirection = sortDirection;
	}

	public ProductSearchSortFilterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public LocalDateTime getCreatedFrom() {
		return createdFrom;
	}

	public void setCreatedFrom(LocalDateTime createdFrom) {
		this.createdFrom = createdFrom;
	}

	public LocalDateTime getCreatedTo() {
		return createdTo;
	}

	public void setCreatedTo(LocalDateTime createdTo) {
		this.createdTo = createdTo;
	}

	public SortField getSortBy() {
		return sortBy;
	}

	public void setSortBy(SortField sortBy) {
		this.sortBy = sortBy;
	}

	public SortDirection getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}

}

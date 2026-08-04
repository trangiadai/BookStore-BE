package com.tgd.dto.mappers;

import com.tgd.dto.request.ProductRequestDTO;
import com.tgd.dto.response.ProductResponseDTO;
import com.tgd.entity.Product;
import com.tgd.enums.ProductCategory;

public class ProductMapperDTO {

	public static ProductResponseDTO toProductResponse(Product product) {
		if (product == null) {
			return null;
		}

		ProductResponseDTO productReponse = new ProductResponseDTO();
		productReponse.setId(product.getId());
		productReponse.setName(product.getName());
		if (product.getProductCategory() != null) {
			productReponse.setProductCategory(ProductCategory.fromString(product.getProductCategory()));
		}
		productReponse.setImportPrice(product.getImportPrice());
		productReponse.setSellingPrice(product.getSellingPrice());
		productReponse.setQuantity(product.getQuantity());
		productReponse.setDescription(product.getDescription());
		productReponse.setProductImages(product.getProductImages());
		productReponse.setCreatedAt(product.getCreatedAt());

		return productReponse;
	}

	public static Product toProduct(ProductRequestDTO productRequest) {
		if (productRequest == null) {
			return null;
		}

		Product product = new Product();
		product.setName(productRequest.getName());
		if (productRequest.getProductCategory() != null) {
			product.setProductCategory(ProductCategory.fromString(productRequest.getProductCategory()).name());
		}
		product.setImportPrice(productRequest.getImportPrice());
		product.setSellingPrice(productRequest.getSellingPrice());
		product.setQuantity(productRequest.getQuantity());
		product.setDescription(productRequest.getDescription());

		return product;
	}
}

package com.tgd.dto.mappers;

import com.tgd.dto.request.ProductRequestDTO;
import com.tgd.dto.response.ProductResponseDTO;
import com.tgd.entity.Product;

public class ProductMapperDTO {

	public static ProductResponseDTO toProductResponse(Product product) {
		if (product == null) {
			return null;
		}

		ProductResponseDTO productReponse = new ProductResponseDTO();
		productReponse.setId(product.getId());
		productReponse.setName(product.getName());
		productReponse.setImportPrice(product.getImportPrice());
		productReponse.setSellingPrice(product.getSellingPrice());
		productReponse.setQuantity(product.getQuantity());
		productReponse.setDescription(product.getDescription());
		productReponse.setCategoryId(product.getCategoryId());
		productReponse.setCategoryName(product.getCategoryName());
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
		product.setImportPrice(productRequest.getImportPrice());
		product.setSellingPrice(productRequest.getSellingPrice());
		product.setQuantity(productRequest.getQuantity());
		product.setDescription(productRequest.getDescription());
		product.setCategoryId(productRequest.getCategoryId());

		return product;
	}
}

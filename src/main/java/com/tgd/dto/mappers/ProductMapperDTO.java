package com.tgd.dto.mappers;

import com.tgd.dto.request.ProductRequest;
import com.tgd.dto.response.ProductResponse;
import com.tgd.entity.Product;

public class ProductMapperDTO {

	public static ProductResponse toProductResponse(Product product) {
		if (product == null) {
			return null;
		}

		ProductResponse productReponse = new ProductResponse();
		productReponse.setId(product.getId());
		productReponse.setName(product.getName());
		productReponse.setImportPrice(product.getImportPrice());
		productReponse.setSellingPrice(product.getSellingPrice());
		productReponse.setQuantity(product.getQuantity());
		productReponse.setAverageRating(product.getAverageRating());
		productReponse.setDescription(product.getDescription());
		productReponse.setCategoryId(product.getCategoryId());
		productReponse.setCategoryName(product.getCategoryName());
		productReponse.setProductImages(product.getProductImages());
		productReponse.setCreatedAt(product.getCreatedAt());

		return productReponse;
	}

	public static Product toProduct(ProductRequest productRequest) {
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

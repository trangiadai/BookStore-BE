package com.tgd.dto.mappers;

import com.tgd.dto.response.ProductResponseDTO;
import com.tgd.entity.Product;
import com.tgd.enums.ProductCategory;

public class ProductMapperDTO {

	public static ProductResponseDTO toProductResponse(Product product) {
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
}

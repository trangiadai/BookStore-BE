package com.tgd.dto.mappers;

import java.util.Map;

import com.tgd.entity.ProductImage;

public class ProductImageMapperDTO {

	public static ProductImage toProductImage(Map uploadResult) {
		ProductImage productImage = new ProductImage();
		productImage.setUrl(uploadResult.get("secure_url").toString());
		productImage.setPublicId(uploadResult.get("public_id").toString());
		
		return productImage;
	}
	
}

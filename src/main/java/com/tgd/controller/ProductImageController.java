package com.tgd.controller;

import java.beans.PropertyEditorSupport;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tgd.entity.ProductImage;
import com.tgd.service.ProductImageService;

@RestController
@RequestMapping("/product-images")
public class ProductImageController {
	private final ProductImageService productImageService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Convert empty MultipartFile items to null
		binder.registerCustomEditor(MultipartFile.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				// If Swagger or client submits empty string or "string", set to null
				if (text == null || text.trim().isEmpty() || "string".equalsIgnoreCase(text.trim())) {
					setValue(null);
				}
			}
		});
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Set<ProductImage> createProductImage(@RequestParam("productImages") Set<MultipartFile> productImages,
			@RequestParam("productId") Long productId) {
		return productImageService.createProductImage(productImages, productId);
	}

	@DeleteMapping("/{productImageId}")
	public int softDeleteProductImage(@PathVariable("productImageId") Long productImageId) {
		return productImageService.softDeleteProductImage(productImageId);
	}

	@DeleteMapping("/{productImageId}/hard")
	public int hardDeleteProductImage(@PathVariable("productImageId") Long productImageId) {
		return productImageService.hardDeleteProductImage(productImageId);
	}

	public ProductImageController(ProductImageService productImageService) {
		super();
		this.productImageService = productImageService;
	}

}

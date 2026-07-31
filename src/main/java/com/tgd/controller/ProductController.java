package com.tgd.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tgd.dto.request.ProductRequestDTO;
import com.tgd.dto.response.ProductResponseDTO;
import com.tgd.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;

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

	@GetMapping
	public List<ProductResponseDTO> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponseDTO createProduct(@Valid @ModelAttribute ProductRequestDTO productRequest) {
		return productService.createProduct(productRequest);
	}

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

}

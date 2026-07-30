package com.tgd.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.dto.request.ProductRequestDTO;
import com.tgd.dto.response.ProductResponseDTO;
import com.tgd.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;
	
	@GetMapping
	public List<ProductResponseDTO> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@PostMapping
	public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO productRequest) {
		return productService.createProduct(productRequest);
	}

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
}

package com.tgd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tgd.dto.mappers.ProductMapperDTO;
import com.tgd.dto.request.ProductRequestDTO;
import com.tgd.dto.response.ProductResponseDTO;
import com.tgd.entity.Product;
import com.tgd.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productResitory;

	public ProductResponseDTO getProductById(Long id) {
		return ProductMapperDTO.toProductResponse(productResitory.getProductById(id));
	}

	public List<ProductResponseDTO> getAllProducts() {
		List<Product> products = productResitory.getAllProducts();

		return products.stream().map(ProductMapperDTO::toProductResponse).collect(Collectors.toList());
	}

	public ProductResponseDTO createProduct(ProductRequestDTO productRequest) {
		Product product = ProductMapperDTO.toProduct(productRequest);
		Long productId = productResitory.createProduct(product).longValue();

		return getProductById(productId);
	}

	public ProductService(ProductRepository productResitory) {
		super();
		this.productResitory = productResitory;
	}

}

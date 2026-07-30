package com.tgd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tgd.dto.mappers.ProductMapperDTO;
import com.tgd.dto.response.ProductResponseDTO;
import com.tgd.entity.Product;
import com.tgd.repository.ProductRepository;

@Service
public class ProductService {
	private ProductRepository productResitory;

	public List<ProductResponseDTO> getAllProducts() {
		List<Product> products = productResitory.getAllProducts();

		return products.stream().map(ProductMapperDTO::toProductResponse).collect(Collectors.toList());
	}

	public ProductService(ProductRepository productResitory) {
		super();
		this.productResitory = productResitory;
	}

}

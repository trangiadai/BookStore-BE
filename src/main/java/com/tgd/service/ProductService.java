package com.tgd.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tgd.dto.mappers.ProductImageMapperDTO;
import com.tgd.dto.mappers.ProductMapperDTO;
import com.tgd.dto.request.ProductRequestDTO;
import com.tgd.dto.response.ProductResponseDTO;
import com.tgd.entity.Product;
import com.tgd.entity.ProductImage;
import com.tgd.repository.ProductRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@Service
public class ProductService {
	private final ProductRepository productResitory;
	private final ProductImageService productImageService;

	public List<ProductResponseDTO> getAllProducts() {
		List<Product> products = productResitory.getAllProducts();

		return products.stream().map(ProductMapperDTO::toProductResponse).collect(Collectors.toList());
	}

	@Transactional
	public ProductResponseDTO createProduct(ProductRequestDTO productRequest) {
		Product product = new Product();
		ProductMapperDTO.toProduct(productRequest);

		//TODO: we must create product before create product image because the productId
		//TODO: write logic to create the product image (xml)
		product.setId(productResitory.createProduct(product));
		
		
		Set<ProductImage> productImages = new HashSet<>();
		Set<MultipartFile> rawProductImages = productRequest.getProductImages();
		if (rawProductImages != null && !rawProductImages.isEmpty()) {
			for (MultipartFile rawProductImage : rawProductImages) {
				if (rawProductImage != null && !rawProductImage.isEmpty()) {
					productImages.add(productImageService.createProductImage(rawProductImage, product.getId()));
				}
			}
		}
		product.setProductImages(productImages);	

		return ProductMapperDTO.toProductResponse(product);
	}

	public ProductService(ProductRepository productResitory, ProductImageService productImageService) {
		super();
		this.productResitory = productResitory;
		this.productImageService = productImageService;
	}

}

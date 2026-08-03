package com.tgd.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tgd.dto.mappers.ProductMapperDTO;
import com.tgd.dto.request.ProductRequestDTO;
import com.tgd.dto.response.ProductResponseDTO;
import com.tgd.entity.Product;
import com.tgd.entity.ProductImage;
import com.tgd.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productResitory;
	private final ProductImageService productImageService;

	public List<ProductResponseDTO> getAllProducts() {
		List<Product> products = productResitory.getAllProducts();

		return products.stream().map(ProductMapperDTO::toProductResponse).collect(Collectors.toList());
	}

	public ProductResponseDTO createProduct(ProductRequestDTO productRequest) {
		Set<ProductImage> uploadedImages = new HashSet<>();
		Set<MultipartFile> rawProductImages = productRequest.getProductImages();
		if (rawProductImages != null && !rawProductImages.isEmpty()) {
			for (MultipartFile file : rawProductImages) {
				if (file != null && !file.isEmpty()) {
					try {
						ProductImage image = productImageService.uploadToCloudinary(file);
						uploadedImages.add(image);
					} catch (IOException e) {
						// Cleanup files uploaded so far if one upload fails
						rollbackCloudinaryUploads(uploadedImages);
						throw new RuntimeException("Image upload failed", e);
					}
				}
			}
		}
		try {
			return saveProductAndImages(productRequest, uploadedImages);
		} catch (Exception e) {
			// If DB insert fails, delete files from Cloudinary
			rollbackCloudinaryUploads(uploadedImages);
			throw e;
		}
	}

	// Helper method to handle DB insertion in a tight transaction
	@Transactional
	protected ProductResponseDTO saveProductAndImages(ProductRequestDTO productRequest,
			Set<ProductImage> uploadedImages) {

		Product product = ProductMapperDTO.toProduct(productRequest);
		product.setId(productResitory.createProduct(product).longValue());

		Set<ProductImage> savedImages = new HashSet<>();
		for (ProductImage img : uploadedImages) {
			savedImages.add(productImageService.saveProductImageToDb(img, product.getId()));
		}
		product.setProductImages(savedImages);

		return ProductMapperDTO.toProductResponse(product);
	}

	private void rollbackCloudinaryUploads(Set<ProductImage> images) {
		for (ProductImage img : images) {
			if (img.getPublicId() != null) {
				productImageService.deleteFromCloudinary(img.getPublicId());
			}
		}
	}

	public ProductService(ProductRepository productResitory, ProductImageService productImageService) {
		super();
		this.productResitory = productResitory;
		this.productImageService = productImageService;
	}

}

package com.tgd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgd.dao.mappers.OrphanedFileMapper;
import com.tgd.dto.mappers.ProductMapperDTO;
import com.tgd.dto.request.ProductRequestDTO;
import com.tgd.dto.response.ProductResponseDTO;
import com.tgd.entity.OrphanedFile;
import com.tgd.entity.Product;
import com.tgd.entity.ProductImage;
import com.tgd.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductImageService productImageService;
	private final OrphanedFileMapper orphanedFileMapper;

	@Transactional
	public int softDeleteProduct(Long productId) {
		getProductById(productId);

		return productImageService.softDeleteImagesByProductId(productId)
				+ productRepository.softDeleteProduct(productId);
	}

	@Transactional
	public int hardDeleteProduct(Long productId) {
		List<ProductImage> images = productImageService.getAllImagesByProductId(productId);

		for (ProductImage img : images) {
			if (img.getPublicId() != null) {
				OrphanedFile file = new OrphanedFile();
				file.setPublicId(img.getPublicId());
				file.setStatus("PENDING");
				file.setRetryCount(0);
				orphanedFileMapper.insert(file);
			}
		}

		return productImageService.hardDeleteImagesByProductId(productId)
				+ productRepository.hardDeleteProduct(productId);
	}

	public ProductResponseDTO getProductById(Long id) {
		Product product = productRepository.getProductById(id)
				.orElseThrow(() -> new IllegalArgumentException("Not found active product with id: " + id));

		return ProductMapperDTO.toProductResponse(product);
	}

	public List<ProductResponseDTO> getAllProducts() {
		List<Product> products = productRepository.getAllProducts();

		return products.stream().map(ProductMapperDTO::toProductResponse).collect(Collectors.toList());
	}

	public ProductResponseDTO createProduct(ProductRequestDTO productRequest) {
		Product product = ProductMapperDTO.toProduct(productRequest);
		Long productId = productRepository.createProduct(product).longValue();

		return getProductById(productId);
	}

	public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequest) {
		Product product = ProductMapperDTO.toProduct(productRequest);
		product.setId(id);
		productRepository.updateProduct(product);

		return ProductMapperDTO.toProductResponse(product);
	}

	public ProductService(ProductRepository productRepository, ProductImageService productImageService,
			OrphanedFileMapper orphanedFileMapper) {
		super();
		this.productRepository = productRepository;
		this.productImageService = productImageService;
		this.orphanedFileMapper = orphanedFileMapper;
	}

}

package com.tgd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgd.dto.mappers.ProductMapperDTO;
import com.tgd.dto.request.ProductRequestDTO;
import com.tgd.dto.request.ProductSearchSortFilterDTO;
import com.tgd.dto.response.CategoryResponseDTO;
import com.tgd.dto.response.ProductResponseDTO;
import com.tgd.entity.Product;
import com.tgd.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductImageService productImageService;
	private final CategoryService categoryService;

	@Transactional
	public int softDeleteProduct(Long productId) {
		getProductById(productId);

		return productImageService.softDeleteImagesByProductId(productId)
				+ productRepository.softDeleteProduct(productId);
	}

	@Transactional
	public int hardDeleteProduct(Long productId) {
		getSoftDeletedProductById(productId);

		int deletedImagesCount = productImageService.hardDeleteImagesByProductId(productId);
		int deletedProductCount = productRepository.hardDeleteProduct(productId);

		return deletedImagesCount + deletedProductCount;
	}

	private Product getSoftDeletedProductById(Long id) {
		Product product = productRepository.getSoftDeletedProductById(id).orElseThrow(
				() -> new IllegalArgumentException("Not found in garbage collection the product with id: " + id));

		return product;
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

	@Transactional
	public ProductResponseDTO createProduct(ProductRequestDTO productRequest) {
		categoryService.getCategoryById(productRequest.getCategoryId());

		Product product = ProductMapperDTO.toProduct(productRequest);
		Long productId = productRepository.createProduct(product).longValue();

		return getProductById(productId);
	}

	@Transactional
	public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequest) {
		CategoryResponseDTO categoryResponse = categoryService.getCategoryById(productRequest.getCategoryId());

		Product product = ProductMapperDTO.toProduct(productRequest);
		product.setId(id);
		product.setCategoryName(categoryResponse.getName());
		productRepository.updateProduct(product);

		return ProductMapperDTO.toProductResponse(product);
	}

	@Transactional
	public int recoverProduct(Long productId) {
		getSoftDeletedProductById(productId);

		int recoveredProductCount = productRepository.recoverProduct(productId);
		int recoveredImagesCount = productImageService.recoverImagesByProductId(productId);

		return recoveredProductCount + recoveredImagesCount;
	}

	public List<ProductResponseDTO> searchSortFilterProducts(ProductSearchSortFilterDTO criteria) {
		List<Product> products = productRepository.searchSortFilterProducts(criteria);

		return products.stream().map(ProductMapperDTO::toProductResponse).collect(Collectors.toList());
	}

	public ProductService(ProductRepository productRepository, ProductImageService productImageService,
			CategoryService categoryService) {
		super();
		this.productRepository = productRepository;
		this.productImageService = productImageService;
		this.categoryService = categoryService;
	}

}

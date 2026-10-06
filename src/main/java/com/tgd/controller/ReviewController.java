package com.tgd.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.dto.request.ReviewCreateRequest;
import com.tgd.dto.request.ReviewUpdateRequest;
import com.tgd.dto.response.ReviewResponse;
import com.tgd.dto.response.UserReviewResponse;
import com.tgd.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	private final ReviewService reviewService;

	@PostMapping
	public ResponseEntity<String> createReview(@AuthenticationPrincipal Jwt jwt,
			@Valid @RequestBody ReviewCreateRequest request) {
		reviewService.createReview(jwt, request);

		return ResponseEntity.status(HttpStatus.CREATED).body("Review submitted successfully.");
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<Page<ReviewResponse>> getReviewsByProduct(@PathVariable Long productId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(reviewService.getReviewsByProductId(productId, page, size));
	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<Void> updateReview(@AuthenticationPrincipal Jwt jwt, @PathVariable Long reviewId,
			@Valid @RequestBody ReviewUpdateRequest request) {
		reviewService.updateReview(jwt, reviewId, request);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/me")
	public ResponseEntity<Page<UserReviewResponse>> getMyReviews(@AuthenticationPrincipal Jwt jwt,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(reviewService.getMyReviews(jwt, page, size));
	}

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}

}
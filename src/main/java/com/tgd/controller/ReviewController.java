package com.tgd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.dto.request.ReviewCreateRequest;
import com.tgd.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	private final ReviewService reviewService;

	@PostMapping
	public ResponseEntity<String> createReview(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody ReviewCreateRequest request) {
		reviewService.createReview(jwt, request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Review submitted successfully.");
	}

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}

}
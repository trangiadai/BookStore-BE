package com.tgd.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgd.dto.request.ReviewCreateRequest;
import com.tgd.dto.request.ReviewEvent;
import com.tgd.dto.request.ReviewUpdateEvent;
import com.tgd.dto.request.ReviewUpdateRequest;
import com.tgd.dto.response.ReviewResponse;
import com.tgd.dto.response.UserReviewResponse;
import com.tgd.entity.Review;
import com.tgd.repository.ReviewRepository;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final KafkaTemplate<String, Object> kafkaTemplate;
	private static final String REVIEW_TOPIC = "product-reviews";
	private static final String REVIEW_UPDATE_TOPIC = "product-review-updates";

	@Transactional
	public Long createReview(Jwt jwt, ReviewCreateRequest request) {
		Long accountId = Long.parseLong(jwt.getSubject());
		boolean isEligible = reviewRepository.isOrderItemEligibleForReview(request.getOrderItemId(),
				request.getProductId(), accountId);
		if (!isEligible) {
			throw new IllegalArgumentException("You are not eligible to review this item.");
		}

		if (reviewRepository.existsByOrderItemId(request.getOrderItemId())) {
			throw new IllegalStateException("A review for this order item already exists.");
		}

		Review review = new Review();
		review.setOrderItemId(request.getOrderItemId());
		review.setProductId(request.getProductId());
		review.setAccountId(accountId);
		review.setRating(request.getRating());
		review.setComment(request.getComment());

		Number generatedId = reviewRepository.createReview(review);

		ReviewEvent event = new ReviewEvent(request.getProductId(), accountId, request.getOrderItemId(),
				request.getRating(), request.getComment());
		kafkaTemplate.send(REVIEW_TOPIC, String.valueOf(request.getProductId()), event);

		return generatedId != null ? generatedId.longValue() : null;
	}

	public Page<ReviewResponse> getReviewsByProductId(Long productId, int page, int size) {
		int offset = page * size;
		List<ReviewResponse> content = reviewRepository.getReviewsByProductId(productId, offset, size);
		long totalElements = reviewRepository.countByProductId(productId);

		return new PageImpl<>(content, PageRequest.of(page, size), totalElements);
	}

	@Transactional
	public void updateReview(Jwt jwt, Long reviewId, ReviewUpdateRequest request) {
		Long accountId = Long.parseLong(jwt.getSubject());
		
		Review review = reviewRepository.getReviewById(reviewId)
				.orElseThrow(() -> new IllegalArgumentException("Review not found with id: " + reviewId));

		if (!review.getAccountId().equals(accountId)) {
			throw new IllegalStateException("You are not authorized to edit this review.");
		}

		if (review.getEditCount() >= 1) {
			throw new IllegalStateException("You have reached the maximum allowed edits (1) for this review.");
		}

		LocalDateTime windowDeadline = review.getCreatedAt().plusDays(30);
		if (LocalDateTime.now().isAfter(windowDeadline)) {
			throw new IllegalStateException("Reviews can only be edited within 30 days of posting.");
		}

		int oldRating = review.getRating();
		int newRating = request.getRating();

		review.setRating(newRating);
		review.setComment(request.getComment());
		reviewRepository.updateReview(review);

		if (oldRating != newRating) {
			ReviewUpdateEvent updateEvent = new ReviewUpdateEvent(review.getProductId(), oldRating, newRating);
			kafkaTemplate.send(REVIEW_UPDATE_TOPIC, String.valueOf(review.getProductId()), updateEvent);
		}
	}
	
	public Page<UserReviewResponse> getMyReviews(Jwt jwt, int page, int size) {
		Long accountId = Long.parseLong(jwt.getSubject());
		int offset = page * size;

		List<UserReviewResponse> reviews = reviewRepository.getReviewsByAccountId(accountId, offset, size);
		long totalElements = reviewRepository.countByAccountId(accountId);

		LocalDateTime now = LocalDateTime.now();

		// Populate business rule flag: max 1 edit & within 30 days
		reviews.forEach(review -> {
			boolean underEditLimit = review.getEditCount() < 1;
			boolean withinTimeWindow = review.getCreatedAt().plusDays(30).isAfter(now);
			review.setIsEditable(underEditLimit && withinTimeWindow);
		});

		return new PageImpl<>(reviews, PageRequest.of(page, size), totalElements);
	}

	public ReviewService(ReviewRepository reviewRepository, KafkaTemplate<String, Object> kafkaTemplate) {
		super();
		this.reviewRepository = reviewRepository;
		this.kafkaTemplate = kafkaTemplate;
	}
}
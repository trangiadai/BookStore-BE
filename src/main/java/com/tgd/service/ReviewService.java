package com.tgd.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgd.dto.request.ReviewCreateRequest;
import com.tgd.dto.request.ReviewEvent;
import com.tgd.entity.Review;
import com.tgd.repository.ReviewRepository;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final KafkaTemplate<String, ReviewEvent> kafkaTemplate;
	private static final String REVIEW_TOPIC = "product-reviews";

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

	public ReviewService(ReviewRepository reviewRepository, KafkaTemplate<String, ReviewEvent> kafkaTemplate) {
		super();
		this.reviewRepository = reviewRepository;
		this.kafkaTemplate = kafkaTemplate;
	}
}
package com.tgd.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tgd.dao.mappers.ReviewMapper;
import com.tgd.entity.Review;

@Repository
public class ReviewRepository {
	private final ReviewMapper reviewMapper;

	public Number createReview(Review review) {
		Map<String, Object> param = new HashMap<>();
		param.put("orderItemId", review.getOrderItemId());
		param.put("productId", review.getProductId());
		param.put("accountId", review.getAccountId());
		param.put("rating", review.getRating());
		param.put("comment", review.getComment());

		reviewMapper.createReview(param);

		return (Number) param.get("id");
	}

	public boolean existsByOrderItemId(Long orderItemId) {
		Map<String, Object> param = new HashMap<>();
		param.put("orderItemId", orderItemId);

		return reviewMapper.existsByOrderItemId(param);
	}

	public boolean isOrderItemEligibleForReview(Long orderItemId, Long productId, Long accountId) {
		Map<String, Object> param = new HashMap<>();
		param.put("orderItemId", orderItemId);
		param.put("productId", productId);
		param.put("accountId", accountId);

		return reviewMapper.isOrderItemEligibleForReview(param);
	}

	public ReviewRepository(ReviewMapper reviewMapper) {
		super();
		this.reviewMapper = reviewMapper;
	}
}

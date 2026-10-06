package com.tgd.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tgd.dao.mappers.ReviewMapper;
import com.tgd.dto.response.ReviewResponse;
import com.tgd.dto.response.UserReviewResponse;
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
	
	public List<ReviewResponse> getReviewsByProductId(Long productId, int offset, int size) {
		Map<String, Object> param = new HashMap<>();
		param.put("productId", productId);
		param.put("offset", offset);
		param.put("size", size);

		return reviewMapper.getReviewsByProductId(param);
	}

	public Long countByProductId(Long productId) {
		Map<String, Object> param = new HashMap<>();
		param.put("productId", productId);

		return reviewMapper.countByProductId(param);
	}
	
	public Optional<Review> getReviewById(Long reviewId) {
        Map<String, Object> param = new HashMap<>();
        param.put("reviewId", reviewId);

        return Optional.ofNullable(reviewMapper.getReviewById(param));
    }

    public void updateReview(Review review) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", review.getId());
        param.put("rating", review.getRating());
        param.put("comment", review.getComment());

        reviewMapper.updateReview(param);
    }

	public ReviewRepository(ReviewMapper reviewMapper) {
		super();
		this.reviewMapper = reviewMapper;
	}
	
	public List<UserReviewResponse> getReviewsByAccountId(Long accountId, int offset, int size) {
	    Map<String, Object> param = new HashMap<>();
	    param.put("accountId", accountId);
	    param.put("offset", offset);
	    param.put("size", size);

	    return reviewMapper.getReviewsByAccountId(param);
	}

	public Long countByAccountId(Long accountId) {
	    Map<String, Object> param = new HashMap<>();
	    param.put("accountId", accountId);

	    return reviewMapper.countByAccountId(param);
	}
}

package com.tgd.dao.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tgd.dto.response.ReviewResponse;
import com.tgd.dto.response.UserReviewResponse;
import com.tgd.entity.Review;

@Mapper
public interface ReviewMapper {
	void createReview(Map<String, Object> param);

	boolean existsByOrderItemId(Map<String, Object> param);

	boolean isOrderItemEligibleForReview(Map<String, Object> param);
	
	List<ReviewResponse> getReviewsByProductId(Map<String, Object> param);
	
	Long countByProductId(Map<String, Object> param);
	
	Review getReviewById(Map<String, Object> param);

    void updateReview(Map<String, Object> param);
    
    List<UserReviewResponse> getReviewsByAccountId(Map<String, Object> param);

    Long countByAccountId(Map<String, Object> param);
}

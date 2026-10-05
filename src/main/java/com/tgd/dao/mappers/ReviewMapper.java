package com.tgd.dao.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
	void createReview(Map<String, Object> param);

	boolean existsByOrderItemId(Map<String, Object> param);

	boolean isOrderItemEligibleForReview(Map<String, Object> param);
}

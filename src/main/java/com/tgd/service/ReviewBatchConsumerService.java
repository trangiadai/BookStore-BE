package com.tgd.service;

import com.tgd.dto.request.ProductStatUpdate;
import com.tgd.dto.request.ReviewEvent;
import com.tgd.dto.request.ReviewUpdateEvent;
import com.tgd.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewBatchConsumerService {
	private final ProductRepository productRepository;

	@KafkaListener(topics = "product-reviews", groupId = "review-processing-group")
	@Transactional
	public void consumeBatch(List<ReviewEvent> events) {
		if (events == null || events.isEmpty()) {
			return;
		}

		Map<Long, ProductStatUpdate> aggregatedStats = new HashMap<>();

		for (ReviewEvent event : events) {
			aggregatedStats.compute(event.getProductId(), (productId, existingStat) -> {
				if (existingStat == null) {
					return new ProductStatUpdate(productId, 1, event.getRating());
				} else {
					existingStat.setCountIncrement(existingStat.getCountIncrement() + 1);
					existingStat.setSumIncrement(existingStat.getSumIncrement() + event.getRating());
					return existingStat;
				}
			});
		}

		productRepository.updateBatchStats(new ArrayList<>(aggregatedStats.values()));
	}

	@KafkaListener(topics = "product-review-updates", groupId = "review-processing-group")
	@Transactional
	public void consumeUpdateBatch(List<ReviewUpdateEvent> events) {
		if (events == null || events.isEmpty()) {
			return;
		}

		Map<Long, ProductStatUpdate> aggregatedStats = new HashMap<>();

		for (ReviewUpdateEvent event : events) {
			int delta = event.getNewRating() - event.getOldRating();

			aggregatedStats.compute(event.getProductId(), (productId, existingStat) -> {
				if (existingStat == null) {
					// countIncrement is 0 because no new review was added
					return new ProductStatUpdate(productId, 0, delta);
				} else {
					existingStat.setSumIncrement(existingStat.getSumIncrement() + delta);
					return existingStat;
				}
			});
		}

		productRepository.updateBatchStats(new ArrayList<>(aggregatedStats.values()));
	}

	public ReviewBatchConsumerService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

}
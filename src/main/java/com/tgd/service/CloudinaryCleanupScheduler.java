package com.tgd.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tgd.dao.mappers.OrphanedFileMapper;
import com.tgd.entity.OrphanedFile;

@Service
public class CloudinaryCleanupScheduler {
	private final OrphanedFileMapper orphanedFileMapper;
	private final CloudinaryService cloudinaryService;
	private static final Logger log = LoggerFactory.getLogger(CloudinaryCleanupScheduler.class);

	// Runs every 5 minutes: Retries pending Cloudinary deletions
	@Scheduled(fixedDelay = 300000)
	public void processPendingCloudinaryDeletions() {
		List<OrphanedFile> pendingFiles = orphanedFileMapper.findPendingFiles(5);

		if (pendingFiles.isEmpty()) {
			return;
		}

		for (OrphanedFile file : pendingFiles) {
			try {
				cloudinaryService.deleteFile(file.getPublicId());
				orphanedFileMapper.deleteById(file.getId());
				log.info("Successfully cleaned up Cloudinary file: {}", file.getPublicId());

			} catch (Exception e) {
				int newRetryCount = file.getRetryCount() + 1;
				file.setRetryCount(newRetryCount);
				file.setLastError(e.getMessage());

				if (newRetryCount >= 5) {
					// Pushed to FAILED_PERMANENTLY for Option A (Admin Intervention)
					file.setStatus("FAILED_PERMANENTLY");
					log.error("CRITICAL: Cloudinary deletion for '{}' permanently failed after 5 attempts.",
							file.getPublicId());
				} else {
					log.warn("Retry {}/5 failed for Cloudinary file: {}", newRetryCount, file.getPublicId());
				}

				orphanedFileMapper.updateStatusAndRetry(file);
			}
		}
	}

	// Option C: Runs 1st day of every month at 2:00 AM
	// Purges FAILED_PERMANENTLY or MANUALLY_RESOLVED outbox records older than 30
	// days
	@Scheduled(cron = "0 0 2 1 * ?")
	public void purgeOldOutboxLogs() {
		LocalDateTime cutoffDate = LocalDateTime.now().minusDays(30);
		int deletedRows = orphanedFileMapper.deleteOldFailedRecords(cutoffDate);
		log.info("Purged {} outbox logs older than 30 days.", deletedRows);
	}

	public CloudinaryCleanupScheduler(OrphanedFileMapper orphanedFileMapper, CloudinaryService cloudinaryService) {
		super();
		this.orphanedFileMapper = orphanedFileMapper;
		this.cloudinaryService = cloudinaryService;
	}
}

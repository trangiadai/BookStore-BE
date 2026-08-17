package com.tgd.dao.mappers;

import com.tgd.entity.OrphanedFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrphanedFileMapper {

    void insert(OrphanedFile orphanedFile);

    OrphanedFile findById(@Param("id") Long id);

    List<OrphanedFile> findPendingFiles(@Param("maxRetries") int maxRetries);

    List<OrphanedFile> findFailedPermanentlyFiles();

    void updateStatusAndRetry(OrphanedFile orphanedFile);

    void deleteById(@Param("id") Long id);

    int deleteOldFailedRecords(@Param("cutoffDate") LocalDateTime cutoffDate);
}
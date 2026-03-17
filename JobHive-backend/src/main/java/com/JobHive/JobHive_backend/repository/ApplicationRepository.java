package com.JobHive.JobHive_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobHive.JobHive_backend.model.ApplicationEntity;
import com.JobHive.JobHive_backend.model.enums.ApplicationStatus;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
    List<ApplicationEntity> findByApplicant_UserId(Long userId);        // All apps by a job seeker
    List<ApplicationEntity> findByJob_JobId(Long jobId);                // All apps for a job
    List<ApplicationEntity> findByStatus(ApplicationStatus status);     // Filter by status
    boolean existsByApplicant_UserIdAndJob_JobId(Long userId, Long jobId); // Prevent duplicate apply
}
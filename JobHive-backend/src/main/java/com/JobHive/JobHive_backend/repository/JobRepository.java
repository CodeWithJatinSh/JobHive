package com.JobHive.JobHive_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobHive.JobHive_backend.model.JobEntity;
import com.JobHive.JobHive_backend.model.enums.JobStatus;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {
    List<JobEntity> findByPostedBy_UserId(Long userId);         // All jobs by a recruiter
    List<JobEntity> findByStatus(JobStatus status);             // All OPEN jobs
    List<JobEntity> findByLocationContainingIgnoreCase(String location); // Location filter
}
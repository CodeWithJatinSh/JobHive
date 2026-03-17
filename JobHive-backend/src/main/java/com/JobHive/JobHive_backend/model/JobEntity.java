package com.JobHive.JobHive_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

import com.JobHive.JobHive_backend.model.enums.ExperienceLevel;
import com.JobHive.JobHive_backend.model.enums.JobStatus;
import com.JobHive.JobHive_backend.model.enums.JobType;



@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_ID")
    private Long jobId;

    @Column(name = "TITLE", nullable = false)
    private String title;                        // "Backend Developer - Java"

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "LOCATION", nullable = false)
    private String location;                     // "Mohali, Punjab" or "Remote"

    @Column(name = "IS_REMOTE")
    private boolean remote = false;

    @Column(name = "JOB_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobType jobType;                     // FULL_TIME, PART_TIME, CONTRACT, INTERNSHIP

    @Column(name = "EXPERIENCE_LEVEL")
    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;     // FRESHER, MID, SENIOR

    @Column(name = "SALARY_MIN")
    private Double salaryMin;

    @Column(name = "SALARY_MAX")
    private Double salaryMax;

    @Column(name = "SKILLS_REQUIRED", columnDefinition = "TEXT")
    private String skillsRequired;               // "Java, Spring Boot, MySQL"

    @Column(name = "DEADLINE")
    private LocalDateTime deadline;              // Application closing date

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private JobStatus status = JobStatus.OPEN;   // OPEN, CLOSED, DRAFT

    // --- Timestamps ---
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    // --- Relationships ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTED_BY", nullable = false)
    private UserEntity postedBy;                 // Recruiter

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ApplicationEntity> applications;
}
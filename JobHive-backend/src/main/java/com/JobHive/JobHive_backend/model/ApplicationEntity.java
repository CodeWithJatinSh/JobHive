package com.JobHive.JobHive_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.JobHive.JobHive_backend.model.enums.ApplicationStatus;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPLICATION_ID")
    private Long applicationId;

    @Column(name = "COVER_LETTER", columnDefinition = "TEXT")
    private String coverLetter;

    @Column(name = "RESUME_URL")
    private String resumeUrl;                    // Can override profile resume

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private ApplicationStatus status = ApplicationStatus.APPLIED;
    // APPLIED, UNDER_REVIEW, SHORTLISTED, REJECTED, HIRED

    @Column(name = "RECRUITER_NOTE", columnDefinition = "TEXT")
    private String recruiterNote;                // Internal note by recruiter

    //*======================================= Timestamps ========================================================*/
    @Column(name = "APPLIED_AT", updatable = false)
    private LocalDateTime appliedAt = LocalDateTime.now();

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    /*======================================Relationships======================================*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICANT_ID", nullable = false)
    private UserEntity applicant;                // Job Seeker

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_ID", nullable = false)
    private JobEntity job;                       // Which job they applied to
}
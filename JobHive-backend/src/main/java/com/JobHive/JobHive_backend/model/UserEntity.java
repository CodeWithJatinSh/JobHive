package com.JobHive.JobHive_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

import com.JobHive.JobHive_backend.model.enums.Role;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "PROFILE_PICTURE_URL")
    private String profilePictureUrl;          // Cloudinary

    @Column(name = "HEADLINE")
    private String headline;                   // "Java Backend Dev | Spring Boot"

    @Column(name = "BIO", columnDefinition = "TEXT")
    private String bio;

    // --- Job Seeker specific ---
    @Column(name = "RESUME_URL")
    private String resumeUrl;                  // Cloudinary PDF

    // --- Recruiter specific ---
    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "COMPANY_WEBSITE")
    private String companyWebsite;

    // --- Role & Status ---
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;                         // ADMIN, RECRUITER, JOB_SEEKER

    @Column(name = "IS_ENABLED")
    private boolean enabled = true;

    @Column(name = "IS_EMAIL_VERIFIED")
    private boolean emailVerified = false;

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
    @OneToMany(mappedBy = "postedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JobEntity> postedJobs;        // Recruiter posts jobs

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ApplicationEntity> applications; // Job Seeker applies to jobs
}
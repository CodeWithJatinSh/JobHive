package com.JobHive.JobHive_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobHive.JobHive_backend.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
     Optional<UserEntity> findByEmail(String email);
     boolean existsByEmail(String email);
}
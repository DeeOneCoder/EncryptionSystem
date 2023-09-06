package com.Davidson.EncryptionSystem.repository;

import com.Davidson.EncryptionSystem.model.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    Optional<UserActivity> findByEncryptionTitle(String title);
    Optional<List<UserActivity>> findByDate(LocalDate date);
    Optional<List<UserActivity>> findByActivity(String activity);
}

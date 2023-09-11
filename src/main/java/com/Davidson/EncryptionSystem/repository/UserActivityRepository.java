package com.Davidson.EncryptionSystem.repository;

import com.Davidson.EncryptionSystem.model.UserActivity;
import com.Davidson.EncryptionSystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    Optional<List<UserActivity>> findByUser(Users users);

}

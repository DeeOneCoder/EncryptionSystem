package com.Davidson.EncryptionSystem.repository;

import com.Davidson.EncryptionSystem.model.UserActivity;
import com.Davidson.EncryptionSystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

<<<<<<< Updated upstream
    Optional<List<UserActivity>> findByUser(Users users);

=======
<<<<<<< HEAD
    Optional<UserActivity> findByEncryptionTitle(String title);
    Optional<List<UserActivity>> findByDate(LocalDate date);
    Optional<List<UserActivity>> findByActivity(String activity);
=======
    Optional<List<UserActivity>> findByUser(Long id);

>>>>>>> parent of 0128780 (All endpoint for creation of User)
>>>>>>> Stashed changes
}

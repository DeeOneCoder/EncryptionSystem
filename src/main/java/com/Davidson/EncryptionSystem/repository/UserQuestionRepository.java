package com.Davidson.EncryptionSystem.repository;

import com.Davidson.EncryptionSystem.model.UserQuestion;
import com.Davidson.EncryptionSystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQuestionRepository extends JpaRepository<UserQuestion, Integer> {
<<<<<<< Updated upstream
    Optional<UserQuestion> findByUser(Users user);
=======
<<<<<<< HEAD
    Optional<UserQuestion> findByUsers(long user);
=======
    Optional<UserQuestion> findByUser(long id);
>>>>>>> parent of 0128780 (All endpoint for creation of User)
>>>>>>> Stashed changes
}

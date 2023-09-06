package com.Davidson.EncryptionSystem.repository;

import com.Davidson.EncryptionSystem.model.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQuestionRepository extends JpaRepository<UserQuestion, Integer> {
    Optional<UserQuestion> findByUsers(long user);
}

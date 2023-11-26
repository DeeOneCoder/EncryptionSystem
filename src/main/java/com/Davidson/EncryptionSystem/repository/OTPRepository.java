package com.Davidson.EncryptionSystem.repository;

import com.Davidson.EncryptionSystem.model.OneTimePassword;
import com.Davidson.EncryptionSystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<OneTimePassword, Long> {
    Optional<OneTimePassword> findByUser(Users user);
}

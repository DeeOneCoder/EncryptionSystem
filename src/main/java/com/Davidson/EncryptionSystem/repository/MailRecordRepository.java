package com.Davidson.EncryptionSystem.repository;

import com.Davidson.EncryptionSystem.model.MailRecord;
import com.Davidson.EncryptionSystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MailRecordRepository extends JpaRepository<MailRecord, Long> {

    Optional<List<MailRecord>> findByUser(Users user);

}

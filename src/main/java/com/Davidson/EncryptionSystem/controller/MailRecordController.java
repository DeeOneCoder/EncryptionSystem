package com.Davidson.EncryptionSystem.controller;

import com.Davidson.EncryptionSystem.model.MailRecord;
import com.Davidson.EncryptionSystem.model.Users;
import com.Davidson.EncryptionSystem.repository.MailRecordRepository;
import com.Davidson.EncryptionSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mail")
public class MailRecordController {

    private final UserRepository userRepository;
    private final MailRecordRepository mailRecordRepository;



    @PostMapping("/{id}")
    @ResponseBody
    public ResponseEntity<MailRecord> postMailRecord(@PathVariable Long id,@Valid @RequestBody MailRecord mailRecord){
        Users user = userRepository.findById(id).orElseThrow();
        MailRecord record = MailRecord.builder().emailAddresses(mailRecord.getEmailAddresses())
                .user(user).dateAndTime(LocalDateTime.now()).build();
        mailRecordRepository.save(record);
        return new ResponseEntity<>(record, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<List<MailRecord>> getMailRecord(@PathVariable Long id){
        List<MailRecord> record = mailRecordRepository.findByUser(userRepository.findById(id).orElseThrow()).orElseThrow();
        return new ResponseEntity<>(record, HttpStatus.OK);
    }



}

package com.Davidson.EncryptionSystem.controller;

import com.Davidson.EncryptionSystem.model.UserQuestion;
import com.Davidson.EncryptionSystem.model.Users;
import com.Davidson.EncryptionSystem.repository.UserQuestionRepository;
import com.Davidson.EncryptionSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class UserQuestionController {

    private final UserQuestionRepository userQuestionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/{id}")
    @ResponseBody
    @PreAuthorize("USER")
    public ResponseEntity<UserQuestion> postUserQuestion(@RequestBody @Valid UserQuestion userQuestion,
                                                         @PathVariable Long id){
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserQuestion questionToSave = UserQuestion.builder()
                .question(userQuestion.getQuestion())
                .answer(userQuestion.getAnswer())
                .secretKey(userQuestion.getSecretKey())
                .user(user).build();
        return new ResponseEntity<>(userQuestionRepository.save(questionToSave), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @PreAuthorize("USER")
    public ResponseEntity<UserQuestion> getUserQuestion( @PathVariable Long id){
        Users user = userRepository.findById(id).orElseThrow();
        return new ResponseEntity<>(userQuestionRepository.findByUser(user).orElseThrow(), HttpStatus.OK);
    }
}

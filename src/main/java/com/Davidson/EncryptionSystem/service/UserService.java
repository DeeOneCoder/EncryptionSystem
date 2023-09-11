package com.Davidson.EncryptionSystem.service;

import com.Davidson.EncryptionSystem.model.Users;
import com.Davidson.EncryptionSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "user", key = "#id")
    public Users getUser(Long id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
    }
}

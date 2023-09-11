package com.Davidson.EncryptionSystem.controller;

import com.Davidson.EncryptionSystem.model.Users;
import com.Davidson.EncryptionSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    @ResponseBody
    public Users getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
}

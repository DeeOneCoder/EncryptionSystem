package com.Davidson.EncryptionSystem.controller;

import com.Davidson.EncryptionSystem.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private JWTService tokenService;

    @PostMapping("")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String tokenHeader) {

        // Invalidate the token by adding it to the blacklist
        tokenService.addToBlacklist(tokenHeader);

        return ResponseEntity.ok("Logout successful");
    }
}



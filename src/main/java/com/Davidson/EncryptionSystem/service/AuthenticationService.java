package com.Davidson.EncryptionSystem.service;

import com.Davidson.EncryptionSystem.model.Users;
import com.Davidson.EncryptionSystem.repository.UserRepository;
import com.Davidson.EncryptionSystem.requests.AuthenticationRequest;
import com.Davidson.EncryptionSystem.requests.AuthenticationResponse;
import com.Davidson.EncryptionSystem.requests.UserRegistrationRequest;
import com.Davidson.EncryptionSystem.requests.UserRegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private UserRepository userRepository;

    private JWTService jwtService;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;


    public UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) {

    Users user = Users.builder().fullName(userRegistrationRequest.getFullName())
            .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
            .username(userRegistrationRequest.getUsername())
            .role(userRegistrationRequest.getRole())
            .build();
    userRepository.save(user);

        return UserRegistrationResponse.builder()
                .username(userRegistrationRequest.getUsername())
                .fullName(userRegistrationRequest.getFullName())
                .password(userRegistrationRequest.getPassword())
                .build();

    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()
        ));
        Users user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found")
        );

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();

    }
}


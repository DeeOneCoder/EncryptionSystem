package com.Davidson.EncryptionSystem.controller;

<<<<<<< Updated upstream
import com.Davidson.EncryptionSystem.model.Role;
import com.Davidson.EncryptionSystem.requests.AuthenticationRequest;
import com.Davidson.EncryptionSystem.requests.AuthenticationResponse;
import com.Davidson.EncryptionSystem.requests.UserRegistrationRequest;
import com.Davidson.EncryptionSystem.requests.UserRegistrationResponse;
=======
import com.Davidson.EncryptionSystem.requests.AuthenticationRequest;
import com.Davidson.EncryptionSystem.requests.AuthenticationResponse;
import com.Davidson.EncryptionSystem.requests.UserRegistrationRequest;
>>>>>>> Stashed changes
import com.Davidson.EncryptionSystem.service.AuthenticationService;
import com.Davidson.EncryptionSystem.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginAndAuthenticationController {

    private final AuthenticationService authenticationService;

    private final MessageService messageService;

    @PostMapping("/signup")
<<<<<<< Updated upstream
    public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest) throws Exception {
        userRegistrationRequest.setRole(Role.USER);
        UserRegistrationResponse response = authenticationService.register(userRegistrationRequest);
=======
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest) throws Exception {
        AuthenticationResponse response = authenticationService.register(userRegistrationRequest);
>>>>>>> Stashed changes
        messageService.sendMail(userRegistrationRequest.getUsername(),
                "Registration Successful for " + userRegistrationRequest.getFullName(),
                "Congratulations!\n\nYou have successfully registered " +
                        "with the email address: " + userRegistrationRequest.getUsername() +
                ". \n\nYou can login with below details:\n" +
                "Username: " + userRegistrationRequest.getUsername() +
                "\nPassword: " + userRegistrationRequest.getPassword());
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

<<<<<<< Updated upstream
    @PostMapping
=======
    @PostMapping("/")
>>>>>>> Stashed changes
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return new ResponseEntity<>(authenticationService.authenticateUser(authenticationRequest), HttpStatus.ACCEPTED);
    }


}

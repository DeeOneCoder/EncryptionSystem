package com.Davidson.EncryptionSystem.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
public class AuthenticationRequest {

    private String username;
    private String password;
}

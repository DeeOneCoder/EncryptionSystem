package com.Davidson.EncryptionSystem.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationResponse {

    private String fullName;
    private String username; //email
    private String password;

}

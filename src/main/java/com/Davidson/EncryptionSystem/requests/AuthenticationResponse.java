package com.Davidson.EncryptionSystem.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

    private String token;

    @Override
    public String toString(){
        return this.token;
    }

}

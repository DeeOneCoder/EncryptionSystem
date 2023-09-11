package com.Davidson.EncryptionSystem;

import com.Davidson.EncryptionSystem.model.Role;
import com.Davidson.EncryptionSystem.requests.AuthenticationResponse;
import com.Davidson.EncryptionSystem.requests.UserRegistrationRequest;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


public class TestClass {
    public static void main(String[] args) {

        System.out.println(LocalDate.now());

//        RestTemplate restTemplate = new RestTemplate();
//        AuthenticationResponse token = restTemplate
//                .postForEntity("http://localhost:8080/login/signup", new UserRegistrationRequest("Akinade Adepoju",
//                        "adepoju.love@mail.com", "password", Role.USER), AuthenticationResponse.class).getBody();
//
//        System.out.println(token.toString());



    }

}

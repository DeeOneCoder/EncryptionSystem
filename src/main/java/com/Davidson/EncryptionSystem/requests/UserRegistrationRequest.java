package com.Davidson.EncryptionSystem.requests;

import com.Davidson.EncryptionSystem.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
<<<<<<< Updated upstream
=======
import org.springframework.beans.factory.annotation.Value;
>>>>>>> Stashed changes

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
<<<<<<< Updated upstream
=======
import javax.validation.constraints.NotNull;
>>>>>>> Stashed changes

@Data
@AllArgsConstructor
public class UserRegistrationRequest {

    @NotBlank
    @Length(min = 6, max = 25, message = "Enter a valid name")
    private String fullName;

    @Email(message = "Enter a valid email address")
    private String username; //email

    @NotBlank(message = "Password can not be blank")
    @NotEmpty(message = "Password can not be empty")
    @Length(min = 6, max = 50, message = "Password should be more than 5 letters")
    private String password;

<<<<<<< Updated upstream
=======
    @NotNull
    @Value("USER")
>>>>>>> Stashed changes
    private Role role;
}

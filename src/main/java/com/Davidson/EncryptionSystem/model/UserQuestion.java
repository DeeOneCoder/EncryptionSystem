package com.Davidson.EncryptionSystem.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "questions", schema = "active_users" )
public class UserQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use GenerationType.IDENTITY for MySQL
    private int id;

    @NotBlank(message = "Recovery Question can not be blank")
    private String question;

    @NotBlank(message = "Please provide a Recovery Answer")
    @NotNull(message = "Can not be null")
    private String answer;

    @NotBlank
    @NotNull
    @NotEmpty
    @Length(min = 6, max = 256)
    private String secretKey;

    @OneToOne(optional = true)
    @JoinColumn(name = "user_id", unique = true)
    private Users user;

}

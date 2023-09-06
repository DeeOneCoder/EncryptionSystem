package com.Davidson.EncryptionSystem.model;

import lombok.*;

import javax.persistence.*;

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


    private String question;
    private String answer;
    private String secretKey;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users users;

}

package com.Davidson.EncryptionSystem.model;

import javax.persistence.*;

@Entity
public class OneTimePassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private Users user;

    @Column(name = "otp")
    private String oneTimePassword;
}

package com.Davidson.EncryptionSystem.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_activity", schema = "active_users")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @NotBlank(message = "Encryption Title can not be blank")
    @NotNull(message = "Encryption Title can not be null")
    @NotEmpty(message = "Encryption Title can not be empty")
    @Column(name = "title")
    private String encryptionTitle;

    @NotBlank
    @NotNull(message = "Encryption Title can not be null")
    @NotEmpty(message = "Encryption Title can not be empty")
    private String passkey;


    private LocalDate date;


    @Enumerated(value = EnumType.STRING)
    private Activity activity;


}

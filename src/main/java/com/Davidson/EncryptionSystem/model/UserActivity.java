package com.Davidson.EncryptionSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_activity", schema = "active_users")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @NotBlank(message = "Encryption Title can not be blank")
    @NotNull(message = "Encryption Title can not be null")
    @NotEmpty(message = "Encryption Title can not be empty")
    @Column(name = "title")
    private String encryptionTitle;

    @NotBlank
    private String passkey;

    @Value("${LocalDate.now()}")
    private LocalDate date;

    @NotBlank
    @Enumerated(value = EnumType.STRING)
    private Activity activity;


}

package com.Davidson.EncryptionSystem.model;

<<<<<<< Updated upstream
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Value;
>>>>>>> parent of 0128780 (All endpoint for creation of User)
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    @NotBlank
    @NotNull(message = "Encryption Title can not be null")
    @NotEmpty(message = "Encryption Title can not be empty")
=======
<<<<<<< HEAD
    @NonNull
>>>>>>> Stashed changes
    private String passkey;


<<<<<<< Updated upstream
    private LocalDate date;


=======
    @NonNull
=======
    @NotBlank
    private String passkey;

    @Value("${LocalDate.now()}")
    private LocalDate date;

    @NotBlank
>>>>>>> parent of 0128780 (All endpoint for creation of User)
>>>>>>> Stashed changes
    @Enumerated(value = EnumType.STRING)
    private Activity activity;


}

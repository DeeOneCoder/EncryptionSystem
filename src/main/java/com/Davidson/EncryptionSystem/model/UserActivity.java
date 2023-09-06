package com.Davidson.EncryptionSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
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

    @NonNull
    @Column(name = "title")
    private String encryptionTitle;

    @NonNull
    private String passkey;

    @NonNull
    private LocalDate date = LocalDate.now();

    @NonNull
    @Enumerated(value = EnumType.STRING)
    private Activity activity;


}

package com.Davidson.EncryptionSystem.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mail_record", schema = "active_users")
public class MailRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Field can not be empty")
    private String emailAddresses;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id")
    private Users user;

    @Column(name = "date_and_time")
    private LocalDateTime dateAndTime;


}

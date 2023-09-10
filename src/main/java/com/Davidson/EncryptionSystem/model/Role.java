package com.Davidson.EncryptionSystem.model;

import javax.validation.constraints.NotBlank;

public enum Role {

    @NotBlank
    USER,
    ADMIN
}

package com.orion.mdd.dtos.authentification;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String username;
    private String email;
    private String password;
}

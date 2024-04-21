package com.orion.mdd.dtos.authentification;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}

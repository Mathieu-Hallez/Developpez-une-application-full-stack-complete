package com.orion.mdd.payloads.authentification;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}

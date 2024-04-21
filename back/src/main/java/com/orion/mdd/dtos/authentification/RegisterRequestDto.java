package com.orion.mdd.dtos.authentification;

import com.orion.mdd.security.ValidPassword;
import lombok.Data;

@Data
public class RegisterRequestDto {
    private String username;
    private String email;

    @ValidPassword
    private String password;
}

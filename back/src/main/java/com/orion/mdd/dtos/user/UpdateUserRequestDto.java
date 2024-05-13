package com.orion.mdd.dtos.user;

import com.orion.mdd.security.ValidPasswordUpdate;
import lombok.Data;

@Data
public class UpdateUserRequestDto {
    private String username;
    private String email;

    @ValidPasswordUpdate
    private String password;
}

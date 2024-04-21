package com.orion.mdd.dtos.user;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class UpdateUserDto {
    private String email;
    private String username;
}

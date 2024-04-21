package com.orion.mdd.dtos.authentification;

import lombok.Data;

@Data
public class TokenDto {
    private String token;
    public TokenDto(String token) {
        this.token = token;
    }
}

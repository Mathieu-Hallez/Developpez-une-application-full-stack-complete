package com.orion.mdd.payloads.authentification;

import lombok.Data;

@Data
public class TokenDto {
    private String token;
    public TokenDto(String token) {
        this.token = token;
    }
}

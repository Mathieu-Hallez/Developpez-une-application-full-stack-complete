package com.orion.mdd.dtos.api;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;

    public ApiResponse(String msg) {
        this.message = msg;
    }
}

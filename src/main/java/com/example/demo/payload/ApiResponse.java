package com.example.demo.payload;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;
    private boolean success;
    private Object object;

    public ApiResponse(String message, boolean success, Object object) {
        this.message = message;
        this.success = success;
        this.object = object;
    }

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ApiResponse(boolean success, Object object) {
        this.success = success;
        this.object = object;
    }

    public ApiResponse(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public ApiResponse(Object object) {
        this.object = object;
    }

    public ApiResponse(boolean success) {
        this.success = success;
    }
}

package com.goodmit.framework.web.auth.authentication.dto;

public record LoginRequest(String username, String password) {

    public void validate() {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
    }
}

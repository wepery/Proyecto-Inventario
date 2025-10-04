package com.example.backend.dto;

public class TokenResponseDTO {

    public String token;

    public TokenResponseDTO(String token) {
        this.token = token;
    }

    public TokenResponseDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

package com.example.backend.security.dto;

public class LoginRequestDTO {

    private String login;
    private String password;

    public LoginRequestDTO(String password, String login) {
        this.password = password;
        this.login = login;
    }

    public LoginRequestDTO() {
    }

    @Override
    public String toString() {
        return "LoginRequestDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

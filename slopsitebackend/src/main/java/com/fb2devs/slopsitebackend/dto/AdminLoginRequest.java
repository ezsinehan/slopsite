package com.fb2devs.slopsitebackend.dto;

public class AdminLoginRequest {
    private String username;
    private String password;

    // Default constructor
    public AdminLoginRequest() {
    }

    // Constructor with all fields
    public AdminLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

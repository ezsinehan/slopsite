package com.fb2devs.slopsitebackend.dto;

public class LoginResponse {
    
    private String role; // "student" or "teacher"
    private Long id;
    private String name;
    
    public LoginResponse(String role, Long id, String name) {
        this.role = role;
        this.id = id;
        this.name = name;
    }
    public LoginResponse() {}

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}

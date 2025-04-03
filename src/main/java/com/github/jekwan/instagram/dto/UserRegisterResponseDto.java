package com.github.jekwan.instagram.dto;

public class UserRegisterResponseDto {
    private String name;
    private String email;

    public UserRegisterResponseDto() { }
    public UserRegisterResponseDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

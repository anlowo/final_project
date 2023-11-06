package com.example.final_project.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String login;

    private String password;

    private String password2;

    private boolean isActive;

    private String activationCode;
}

package com.example.final_project.service;

import com.example.final_project.entity.User;

import java.util.List;

public interface UserService {
    User createUser(String login, String password, String email);

    User readUser(Long id);

    List<User> readAllUsers();

    User updateUser(Long id, String login, String password, String email);

    User deleteUser(Long id);
}

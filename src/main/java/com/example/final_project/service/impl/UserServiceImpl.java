package com.example.final_project.service.impl;

import com.example.final_project.dto.UserDto;
import com.example.final_project.entity.User;
import com.example.final_project.repository.UserRepository;
import com.example.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(String login, String password, String email) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        userRepository.save(user);
        return user;
    }

    public User createProduct(UserDto userDto, Long categoryId) {
        User user = new User();
        user.setName(productDto.getName());
        product.setStatus(productDto.getStatus());
        product.setExpirationDate(productDto.getExpirationDate());
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public User readUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь с id " + id + " не найден"));
        System.out.println(user.toString());
        return user;
    }

    @Override
    public List<User> readAllUsers() {
        List<User> users = userRepository.findAll();
        users.sort(Comparator.comparingLong(User::getId));
        return users;
    }

    @Override
    public User updateUser(Long id, String login, String password, String email) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь с id " + id + " не найден"));
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        userRepository.save(user);
        return user;
    }

    @Override
    public User deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь с id " + id + " не найден"));
        userRepository.delete(user);
        return user;
    }
}

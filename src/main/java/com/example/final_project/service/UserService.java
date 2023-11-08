package com.example.final_project.service;

import com.example.final_project.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByLogin(String login) throws UsernameNotFoundException;

    boolean addUser(User user);

    boolean activateUser(String code);

    List<User> findAll();

    void saveUser(User user, String login, Map<String, String> form);

    void updateProfile(User user, String password, String email);

    User findById(Long userId);
}

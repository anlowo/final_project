package com.example.final_project.service.impl;

import com.example.final_project.entity.User;
import com.example.final_project.entity.enums.Roles;
import com.example.final_project.repository.UserRepository;
import com.example.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository repository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);

    @Override
    public UserDetails loadUserByLogin(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public boolean addUser(User user) {
        User userFromDb = repository.findByLogin(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User with login "
                        + user.getUsername() + " not found"));

        user.setActive(true);
        user.setRoles(Collections.singleton(Roles.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repository.save(user);

        return true;
    }

//    private void sendMessage(User user) {
//        if (!StringUtils.isEmpty(user.getEmail())) {
//            String message = String.format(
//                    "Hello, %s! \n" +
//                            "Welcome to Sweater. Please, visit next link: http://localhost:8080/activate/%s",
//                    user.getUsername(),
//                    user.getActivationCode()
//            );
//        }
//    }

    @Override
    public boolean activateUser(String code) {
        User user = repository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        repository.save(user);

        return true;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void saveUser(User user, String login, Map<String, String> form) {
        user.setLogin(login);

        Set<String> roles = Arrays.stream(Roles.values())
                .map(Roles::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Roles.valueOf(key));
            }
        }

        repository.save(user);
    }

    @Override
    public void updateProfile(User user, String password, String login) {
        String userLogin = user.getLogin();

        boolean isLoginChanged = (login != null && !login.equals(userLogin)) ||
                (userLogin != null && !userLogin.equals(login));

        if (isLoginChanged) {
            user.setLogin(login);

            if (!StringUtils.isEmpty(login)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        repository.save(user);
    }

    @Override
    public User findById(Long userId) {
         return repository.findById(userId).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}

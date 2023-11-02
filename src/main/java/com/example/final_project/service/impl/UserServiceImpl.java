//package com.example.final_project.service.impl;
//
//import com.example.final_project.entity.User;
//import com.example.final_project.entity.enums.Roles;
//import com.example.final_project.repository.UserRepository;
//import com.example.final_project.service.MailSenderService;
//import com.example.final_project.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserService, UserDetailsService {
//    @Autowired
//    private UserRepository repository;
//
//    @Autowired
//    private MailSenderService mailSenderService;
//
//    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return repository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//
//    public boolean addUser(User user) {
//        User userFromDb = repository.findByUsername(user.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Roles.USER));
//        user.setActivationCode(UUID.randomUUID().toString());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        repository.save(user);
//
//        sendMessage(user);
//
//        return true;
//    }
//
//    private void sendMessage(User user) {
//        if (!StringUtils.isEmpty(user.getEmail())) {
//            String message = String.format(
//                    "Hello, %s! \n" +
//                            "Welcome to Sweater. Please, visit next link: http://localhost:8080/activate/%s",
//                    user.getUsername(),
//                    user.getActivationCode()
//            );
//
//            mailSenderService.send(user.getEmail(), "Activation code", message);
//        }
//    }
//
//    public boolean activateUser(String code) {
//        User user = repository.findByActivationCode(code);
//
//        if (user == null) {
//            return false;
//        }
//
//        user.setActivationCode(null);
//
//        repository.save(user);
//
//        return true;
//    }
//
//    public List<User> findAll() {
//        return repository.findAll();
//    }
//
//    public void saveUser(User user, String login, Map<String, String> form) {
//        user.setLogin(login);
//
//        Set<String> roles = Arrays.stream(Roles.values())
//                .map(Roles::name)
//                .collect(Collectors.toSet());
//
//        user.getRoles().clear();
//
//        for (String key : form.keySet()) {
//            if (roles.contains(key)) {
//                user.getRoles().add(Roles.valueOf(key));
//            }
//        }
//
//        repository.save(user);
//    }
//
//    public void updateProfile(User user, String password, String email) {
//        String userEmail = user.getEmail();
//
//        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
//                (userEmail != null && !userEmail.equals(email));
//
//        if (isEmailChanged) {
//            user.setEmail(email);
//
//            if (!StringUtils.isEmpty(email)) {
//                user.setActivationCode(UUID.randomUUID().toString());
//            }
//        }
//
//        if (!StringUtils.isEmpty(password)) {
//            user.setPassword(password);
//        }
//
//        repository.save(user);
//
//        if (isEmailChanged) {
//            sendMessage(user);
//        }
//    }
//}

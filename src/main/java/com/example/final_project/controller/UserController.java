package com.example.final_project.controller;

import com.example.final_project.entity.User;
import com.example.final_project.entity.enums.Roles;
import com.example.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userList")
    public ResponseEntity<List<User>> getUsersList() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userEdit/{userId}")
    public ResponseEntity<Map<String, Object>> userEditForm(@PathVariable Long userId) {
        User user = userService.findById(userId);

        if (user == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("roles", Roles.values());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/userSave")
    public ResponseEntity<Map<String, String>> userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, username, form);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User saved successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("profile")
    public ResponseEntity<Map<String, String>> getProfile(@AuthenticationPrincipal User user) {
        Map<String, String> profileData = new HashMap<>();
        profileData.put("login", user.getUsername());

        return new ResponseEntity<>(profileData, HttpStatus.OK);
    }

    @PostMapping("profile")
    public ResponseEntity<Map<String, String>> updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ) {
        userService.updateProfile(user, password, email);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Profile updated successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

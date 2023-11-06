package com.example.final_project.controller;

import com.example.final_project.dto.UserDto;
import com.example.final_project.entity.User;
import com.example.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public Map<String, String> addUser(@Valid @RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            response.put("passwordError", "Passwords are different!");
        }

        if (!userService.addUser(user)) {
            response.put("loginError", "User exists!");
        }

        return response;
    }

    @GetMapping("/activate/{code}")
    public Map<String, String> activate(@PathVariable String code) {
        Map<String, String> response = new HashMap<>();
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            response.put("message", "User successfully activated");
        } else {
            response.put("message", "Activation code is not found!");
        }

        return response;
    }
}

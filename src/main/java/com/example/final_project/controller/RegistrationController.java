package com.example.final_project.controller;

import com.example.final_project.dto.UserDto;
import com.example.final_project.entity.User;
import com.example.final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, String>> registration() {
        Map<String, String> response = new HashMap<>();
        response.put("registration", "Registration page");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<Map<String, String>> addUser(@Valid User user, BindingResult bindingResult) {
        Map<String, String> response = new HashMap<>();

        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            response.put("passwordError", "Passwords are different!");
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            response.putAll(errors);
        } else {
            if (!userService.addUser(user)) {
                response.put("usernameError", "User exists!");
            }
        }

        if (response.isEmpty()) {
            response.put("message", "User successfully registered");
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<Map<String, String>> activate(@PathVariable String code) {
        Map<String, String> response = new HashMap<>();
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            response.put("message", "User successfully activated");
        } else {
            response.put("message", "Activation code is not found!");
        }

        HttpStatus httpStatus = isActivated ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(response, httpStatus);
    }
}

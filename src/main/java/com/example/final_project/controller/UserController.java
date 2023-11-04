//package com.example.final_project.controller;
//
//import com.example.final_project.entity.User;
//import com.example.final_project.entity.enums.Roles;
//import com.example.final_project.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping
//    public String userList(Model model) {
//        model.addAttribute("users", userService.findAll());
//
//        return "userList";
//    }
//
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("{user}")
//    public String userEditForm(@PathVariable User user, Model model) {
//        model.addAttribute("user", user);
//        model.addAttribute("roles", Roles.values());
//
//        return "userEdit";
//    }
//
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping
//    public String userSave(
//            @RequestParam String username,
//            @RequestParam Map<String, String> form,
//            @RequestParam("userId") User user
//    ) {
//        userService.saveUser(user, username, form);
//
//        return "redirect:/user";
//    }
//
//    @GetMapping("profile")
//    public String getProfile(Model model, @AuthenticationPrincipal User user) {
//        model.addAttribute("username", user.getUsername());
//        model.addAttribute("email", user.getEmail());
//
//        return "profile";
//    }
//
//    @PostMapping("profile")
//    public String updateProfile(
//            @AuthenticationPrincipal User user,
//            @RequestParam String password,
//            @RequestParam String email
//    ) {
//        userService.updateProfile(user, password, email);
//
//        return "redirect:/user/profile";
//    }
//}

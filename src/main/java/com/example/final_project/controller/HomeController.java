package com.example.final_project.controller;

import com.example.final_project.entity.Booking;
import com.example.final_project.entity.User;
import com.example.final_project.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
public class HomeController {
    @Autowired
    private BookingRepository bookingRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> greeting(Map<String, Object> model) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/main")
    public ResponseEntity<Map<String, Object>> main(@RequestParam(required = false, defaultValue = "") Date checkInDate) {
        Map<String, Object> response = new HashMap<>();

        Iterable<Booking> bookings;

        if (checkInDate != null) {
            bookings = bookingRepository.findByCheckInDate(checkInDate);
        } else {
            bookings = bookingRepository.findAll();
        }

        response.put("booking", bookings);
        response.put("checkInDate", checkInDate);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/main")
    public ResponseEntity<Map<String, Object>> add(
            @AuthenticationPrincipal User user,
            @Valid Booking booking,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> response = new HashMap<>();

        booking.setUser(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            response.put("errors", errorsMap);
        } else {
            if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                booking.setFilename(resultFilename);

                bookingRepository.save(booking);

                response.put("booking", "Booking successfully saved.");
            }
        }

        Iterable<Booking> messages = bookingRepository.findAll();
        response.put("booking", booking);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

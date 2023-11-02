package com.example.final_project.controller;

import com.example.final_project.dto.HotelDto;
import com.example.final_project.entity.Hotel;
import com.example.final_project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("/add")
    public ResponseEntity<Hotel> addHotel(@RequestBody HotelDto hotelDto, @PathVariable Long roomId) {
        Hotel hotel = hotelService.createHotel(hotelDto, roomId);
        return ResponseEntity.ok(hotel);
    }
}

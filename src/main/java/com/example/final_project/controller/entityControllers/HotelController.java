package com.example.final_project.controller.entityControllers;

import com.example.final_project.dto.HotelDto;
import com.example.final_project.entity.Hotel;
import com.example.final_project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("/add")
    public ResponseEntity<Hotel> addHotel(@RequestBody HotelDto hotelDto) {
        Hotel hotel = hotelService.createHotel(hotelDto);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotel(id);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/get_all")
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody HotelDto hotelDto, @PathVariable Long id) {
        Hotel hotel = hotelService.updateHotel(id, hotelDto);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Hotel> deleteHotel(@PathVariable Long id) {
        Hotel hotel = hotelService.deleteHotel(id);
        return ResponseEntity.ok(hotel);
    }
}

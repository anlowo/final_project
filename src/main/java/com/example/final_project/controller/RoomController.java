package com.example.final_project.controller;

import com.example.final_project.dto.RoomDto;
import com.example.final_project.entity.Room;
import com.example.final_project.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/add")
    public ResponseEntity<Room> addRoom(@RequestBody RoomDto roomDto, @RequestParam Long hotelId) {
        Room room = roomService.createRooms(roomDto, hotelId);
        return ResponseEntity.ok(room);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable Long id) {
        Room room = roomService.getRoom(id);
        return ResponseEntity.ok(room);
    }

    @GetMapping("/get_all")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@RequestBody RoomDto roomDto, @PathVariable Long id) {
        Room room = roomService.updateRoom(id, roomDto);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Room> deleteRoom(@PathVariable Long id) {
        Room room = roomService.deleteRoom(id);
        return ResponseEntity.ok(room);
    }
}

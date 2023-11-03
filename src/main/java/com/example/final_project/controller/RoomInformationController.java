package com.example.final_project.controller;

import com.example.final_project.dto.RoomDto;
import com.example.final_project.dto.RoomInformationDto;
import com.example.final_project.entity.Room;
import com.example.final_project.entity.RoomInformation;
import com.example.final_project.service.RoomInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room_information")
public class RoomInformationController {
    private final RoomInformationService roomInformationService;

    @Autowired
    public RoomInformationController(RoomInformationService roomInformationService) {
        this.roomInformationService = roomInformationService;
    }

    @PostMapping("/add")
    public ResponseEntity<RoomInformation> addRoomInformation(@RequestBody RoomInformationDto roomInformationDto, @RequestParam Long roomId) {
        RoomInformation roomInformation = roomInformationService.createRoomInformation(roomInformationDto, roomId);
        return ResponseEntity.ok(roomInformation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomInformation> getRoomInformation(@PathVariable Long id) {
        RoomInformation roomInformation = roomInformationService.getroomInformation(id);
        return ResponseEntity.ok(roomInformation);
    }

    @GetMapping("/get_all")
    public List<RoomInformation> getAllRoomInformation() {
        return roomInformationService.getAllRoomInformation();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomInformation> updateRoomInformation(@RequestBody RoomInformationDto roomInformationDto, @PathVariable Long id) {
        RoomInformation roomInformation = roomInformationService.updateRoomInformation(id, roomInformationDto);
        return ResponseEntity.ok(roomInformation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoomInformation> deleteRoomInformation(@PathVariable Long id) {
        RoomInformation roomInformation = roomInformationService.deleteRoomInformation(id);
        return ResponseEntity.ok(roomInformation);
    }
}

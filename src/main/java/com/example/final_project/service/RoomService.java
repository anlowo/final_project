package com.example.final_project.service;

import com.example.final_project.dto.RoomDto;
import com.example.final_project.entity.Room;

import java.util.List;

public interface RoomService {
    Room createRooms(RoomDto roomDto, Long hotelId, Long roomInformationId);

    Room getRoom(Long id);

    List<Room> getAllRooms();

    Room updateRoom(Long id, RoomDto roomDto);

    Room deleteRoom(Long id);
}

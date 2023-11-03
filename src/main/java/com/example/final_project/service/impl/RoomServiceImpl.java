package com.example.final_project.service.impl;

import com.example.final_project.dto.RoomDto;
import com.example.final_project.entity.Hotel;
import com.example.final_project.entity.Room;
import com.example.final_project.entity.RoomInformation;
import com.example.final_project.repository.HotelRepository;
import com.example.final_project.repository.RoomInformationRepository;
import com.example.final_project.repository.RoomRepository;
import com.example.final_project.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomInformationRepository roomInformationRepository;

    @Override
    public Room createRooms(RoomDto roomDto, Long hotelId, Long roomInformationId) {
        Room room = new Room();
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setRoomLevel(roomDto.getRoomLevel());

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel with " + hotelId + " id not found"));

        RoomInformation roomInformation = roomInformationRepository.findById(roomInformationId)
                .orElseThrow(() -> new RuntimeException("Rooms information with " + roomInformationId + " id not found"));

        room.setHotel(hotel);
        room.setRoomInformation(roomInformation);

        return roomRepository.save(room);
    }

    @Override
    public Room getRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rooms with " + id + " id not found"));
        System.out.println(room.toString());
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        rooms.sort(Comparator.comparingLong(Room::getId));
        return rooms;
    }

    @Override
    public Room updateRoom(Long id, RoomDto roomDto) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ответ с id " + id + " не найден"));
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setRoomLevel(roomDto.getRoomLevel());
        return roomRepository.save(room);
    }

    @Override
    public Room deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ответ с id " + id + " не найден"));
        roomRepository.delete(room);
        return room;
    }
}

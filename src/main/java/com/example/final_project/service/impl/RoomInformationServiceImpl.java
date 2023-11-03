package com.example.final_project.service.impl;

import com.example.final_project.dto.RoomInformationDto;
import com.example.final_project.entity.Room;
import com.example.final_project.entity.RoomInformation;
import com.example.final_project.repository.RoomRepository;
import com.example.final_project.repository.RoomInformationRepository;
import com.example.final_project.service.RoomInformationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RoomInformationServiceImpl implements RoomInformationService {
    @Autowired
    private RoomInformationRepository roomInformationRepository;

    @Autowired
    private RoomRepository roomRepository;


//    @Override
//    public RoomInformation createRoomInformation(RoomInformationDto roomInformationDto, Long roomId) {
//        Optional<Room> roomOptional = roomRepository.findById(roomId);
//
//        if (roomOptional.isPresent()) {
//            Room room = roomOptional.get();
//
//            RoomInformation roomInformation = new RoomInformation();
//
//            roomInformation.setRoomType(roomInformationDto.getRoomType());
//            roomInformation.setRoomCapacity(roomInformationDto.getRoomCapacity());
//            roomInformation.setBedCount(roomInformationDto.getBedCount());
//            roomInformation.setBedType(roomInformationDto.getBedType());
//
//            roomInformation.getRoom().add(room);
//
//            roomInformationRepository.save(roomInformation);
//
//            return roomInformation;
//        } else {
//            throw new EntityNotFoundException("Room with ID " + roomId + " not found");
//        }
//    }

    @Override
    public RoomInformation createRoomInformation(RoomInformationDto roomInformationDto, Long roomId) {
        List<Room> roomList = roomRepository.findAll();

        if (roomList.isEmpty()) {
            throw new EntityNotFoundException("Rooms null");
        } else {
            roomList = roomRepository.findByIdInList(roomId)
                    .orElseThrow(() -> new EntityNotFoundException
                            ("Rooms with " + roomId + " id not found"));

            RoomInformation roomInformation = new RoomInformation();

            roomInformation.setRoomType(roomInformationDto.getRoomType());
            roomInformation.setRoomCapacity(roomInformationDto.getRoomCapacity());
            roomInformation.setBedCount(roomInformationDto.getBedCount());
            roomInformation.setBedType(roomInformationDto.getBedType());

            roomInformation.getRoom().add((Room) roomList);

            roomInformationRepository.save(roomInformation);

            return roomInformation;
        }
    }

    @Override
    public RoomInformation getroomInformation(Long id) {
        RoomInformation roomInformation = roomInformationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rooms with " + id + " id not found"));
        System.out.println(roomInformation.toString());
        return roomInformation;
    }

    @Override
    public List<RoomInformation> getAllRoomInformation() {
        List<RoomInformation> roomInformation = roomInformationRepository.findAll();
        roomInformation.sort(Comparator.comparingLong(RoomInformation::getId));
        return roomInformation;
    }

    @Override
    public RoomInformation updateRoomInformation(Long id, RoomInformationDto roomInformationDto) {
        RoomInformation roomInformation = roomInformationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Информация о номере с id " + id + " не найден"));
        roomInformation.setRoomType(roomInformationDto.getRoomType());
        roomInformation.setRoomCapacity(roomInformationDto.getRoomCapacity());
        roomInformation.setBedCount(roomInformationDto.getBedCount());
        roomInformation.setBedType(roomInformationDto.getBedType());
        return roomInformationRepository.save(roomInformation);
    }

    @Override
    public RoomInformation deleteRoomInformation(Long id) {
        RoomInformation roomInformation = roomInformationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Информация о номере с id " + id + " не найден"));
        roomInformationRepository.delete(roomInformation);
        return roomInformation;
    }
}

package com.example.final_project.service;

import com.example.final_project.dto.RoomInformationDto;
import com.example.final_project.entity.RoomInformation;

import java.util.List;

public interface RoomInformationService {
//    RoomInformation createRoomInformation(RoomInformationDto roomInformationDto, Long roomId);

    RoomInformation createInformation(RoomInformationDto roomInformationDto);

    RoomInformation getroomInformation(Long id);

    List<RoomInformation> getAllRoomInformation();

    RoomInformation updateRoomInformation(Long id, RoomInformationDto roomInformationDto);

    RoomInformation deleteRoomInformation(Long id);
}

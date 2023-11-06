package com.example.final_project.dto;

import com.example.final_project.entity.roomEnums.BedType;
import com.example.final_project.entity.roomEnums.RoomType;
import lombok.Data;

@Data
public class RoomInformationDto {
    private Long id;

    private RoomType roomType;

    private Integer bedCount;

    private BedType bedType;

    private Integer roomCapacity;
}

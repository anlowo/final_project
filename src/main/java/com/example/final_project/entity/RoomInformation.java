package com.example.final_project.entity;

import com.example.final_project.entity.roomEnums.BedType;
import com.example.final_project.entity.roomEnums.RoomType;
import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "room_information")
@Data
public class RoomInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    @Column(name = "room_type")
    private RoomType roomType;

    @Column(name = "bed_count")
    private Integer bedCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "bed_type")
    private BedType bedType;

    @Column(name = "room_capacity")
    private Integer roomCapacity;
}

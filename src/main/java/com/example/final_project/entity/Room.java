package com.example.final_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number")
    private Short roomNumber;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}

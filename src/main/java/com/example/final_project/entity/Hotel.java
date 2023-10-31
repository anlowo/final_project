package com.example.final_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "hotel")
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    @JoinColumn(name = "room_numbers")
    private List<Room> room;
}

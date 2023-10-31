package com.example.final_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "type")
@Data
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;
}

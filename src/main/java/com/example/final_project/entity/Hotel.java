package com.example.final_project.entity;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
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

    @Column(name = "number of floors")
    private Integer numberOfFloors;

    @CreationTimestamp
    private LocalDateTime cts;

    @UpdateTimestamp
    private LocalDateTime uts;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
//    private List<Room> room;
}

package com.example.final_project.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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

    @CreationTimestamp
    private LocalDateTime cts;

    @UpdateTimestamp
    private LocalDateTime uts;

}

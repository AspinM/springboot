package com.example.demo.SchoolStudent.SchoolEntity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "maptable")
@Data
public class Mapent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "addmap")
    private Long addressId;
    private String city;
    private String addressType;

}

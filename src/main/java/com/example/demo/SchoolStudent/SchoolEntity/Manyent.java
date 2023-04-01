package com.example.demo.SchoolStudent.SchoolEntity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "onetoo")
public class Manyent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addone")
    private Integer addressId;
    private String city;
    private String addresstype;

}

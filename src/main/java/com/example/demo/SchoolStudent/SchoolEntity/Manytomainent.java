package com.example.demo.SchoolStudent.SchoolEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table (name = "manytooo")
public class Manytomainent {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer mainid;
    private String mainName;


    @ManyToMany
    @JoinTable(name = "extratable",
    joinColumns = @JoinColumn(name = "mainid"),
    inverseJoinColumns = @JoinColumn(name = "secid")
    )
    private Set<Manytomanyent> manytomanyents= new HashSet<>();


}

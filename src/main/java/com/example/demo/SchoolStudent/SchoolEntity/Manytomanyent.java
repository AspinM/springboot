package com.example.demo.SchoolStudent.SchoolEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table (name = "manytomany")
public class Manytomanyent {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer secid;
    private String simpleName;
    @ManyToMany(mappedBy = "manytomanyents")
    private Set<Manytomainent> manytomainentss=new HashSet<>();
}

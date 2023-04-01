package com.example.demo.SchoolStudent.SchoolEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Onetomany")
@Data
public class Onetoent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empid")
    private Integer id;
    private String name;
    private Integer age;


    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_addone" , referencedColumnName = "empid")
    private List <Manyent> manyent;
}

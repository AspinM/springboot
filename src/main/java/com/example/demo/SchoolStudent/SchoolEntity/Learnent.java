package com.example.demo.SchoolStudent.SchoolEntity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.poi.ss.formula.functions.Address;
import org.springframework.stereotype.Service;
@Entity
@Table(name = "learning")
@Data
public class Learnent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empid")
    private Long empId;
    private String empname;
    private Integer empAge;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "fk_addmap")
   private Mapent mapent;
}

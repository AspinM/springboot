package com.example.demo.SchoolStudent.SchoolRepo;

import com.example.demo.SchoolStudent.SchoolEntity.Manyent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Manyrep extends JpaRepository <Manyent,Long> {
}

package com.example.demo.SchoolStudent.SchoolRepo;

import com.example.demo.SchoolStudent.SchoolEntity.Manytomanyent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Manytomanyrep extends JpaRepository<Manytomanyent,Integer> {
}

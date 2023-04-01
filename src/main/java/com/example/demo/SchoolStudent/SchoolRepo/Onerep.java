package com.example.demo.SchoolStudent.SchoolRepo;

import com.example.demo.SchoolStudent.SchoolEntity.Onetoent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Onerep extends JpaRepository<Onetoent,Integer> {
}

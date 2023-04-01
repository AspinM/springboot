package com.example.demo.SchoolStudent.SchoolRepo;

import com.example.demo.SchoolStudent.SchoolEntity.Schoolent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface Schoolrep extends JpaRepository<Schoolent,Integer>
{
 Schoolent findByUsernameAndPassword(String username,String password);
// Schoolent findByusername(String username);
// Schoolent findBypassword(String password);
}


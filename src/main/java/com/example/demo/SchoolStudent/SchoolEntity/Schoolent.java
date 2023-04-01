package com.example.demo.SchoolStudent.SchoolEntity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Service
@Data
@Table(name = "students")


public class Schoolent
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

}


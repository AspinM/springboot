package com.example.demo.SchoolStudent.SchoolEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="register")
@Data

public class Registerent
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String firstname;
        private String lastname;
        @JsonFormat(pattern = "yyyy-MM-dd")
        @Temporal(value = TemporalType.DATE)
        private LocalDate dob;
        private String mail;
        private String gender;
        private String address;
        private String city;
        private Long phone;
        private String[] hobbies;
        private String pincode;
        private String state;
        private String country;
        private String[] qualification10;
        private String[] qualification12;
        private String   courses;


}
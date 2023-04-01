package com.example.demo.SchoolStudent.SchoolRepo;

import com.example.demo.SchoolStudent.SchoolEntity.Registerent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public interface Registerrepo extends JpaRepository<Registerent,Integer>
{
    @Query(nativeQuery = true, value = "select count(courses) from register")
    Integer totalStudents();

    @Query(nativeQuery = true, value ="select courses, COUNT(courses) from register where courses = courses group by courses")
    ArrayList<ArrayList> courseDetail();

    @Query(nativeQuery = true, value = "select id from register")
    List <List<Object>> idList();


    @Query("select a from Registerent a where a.dob BETWEEN ?1 AND ?2")
    List<Registerent> findByDateBetween(LocalDate start, LocalDate end);

}

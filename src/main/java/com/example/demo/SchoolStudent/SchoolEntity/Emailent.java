package com.example.demo.SchoolStudent.SchoolEntity;

import lombok.Data;

@Data
public class Emailent {
    private String to;
    private String body;
    private String subject;
    private String attachment;
}

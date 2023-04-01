package com.example.demo.SchoolStudent.Util;

import com.example.demo.SchoolStudent.SchoolEntity.Registerent;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.*;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CsvConversion {
    @Autowired
    private Environment environment;
    private Writer writer;
    private List<Registerent> students;
    public CsvConversion( List<Registerent> students) throws IOException
    {
        this.students=students;
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String currentDateTime = dateFormatter.format(new Date());
//        String path = "E:\\";
//        File newDirectory = new File(path,"csv");
//        newDirectory.mkdir();
        String pathCsv = "E:"+"studentList_"+currentDateTime+".csv";
        writer = new FileWriter(pathCsv);
    }
    public void exportToCSV(HttpServletResponse response)throws IOException
    {
        try(CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT))
        {

            printer.printRecord("ID", "Firstname", "Lastname","Dob","Mail","Phone","Address","City",
                    "Pincode","State","Country","Hobbies","Gender","Qualification10","getQualification12",
                    "Courses");
            for (Registerent student : students)
            {
                printer.printRecord(student.getId(), student.getFirstname(),student.getLastname(),
                        student.getDob(),student.getMail(),student.getPhone(),student.getAddress(),
                        student.getCity(),student.getPincode(),student.getState(),student.getCountry(),
                        Arrays.toString(student.getHobbies()),student.getGender(), Arrays.toString(student.getQualification10()),
                        Arrays.toString(student.getQualification12()),student.getCourses());
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

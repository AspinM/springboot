package com.example.demo.SchoolStudent.SchoolController;
import com.example.demo.SchoolStudent.SchoolEntity.*;
import com.example.demo.SchoolStudent.SchoolEntity.Learnent;
import com.example.demo.SchoolStudent.SchoolRepo.Learnrepo;
import com.example.demo.SchoolStudent.SchoolRepo.Manytomainrep;
import com.example.demo.SchoolStudent.SchoolRepo.Onerep;
import com.example.demo.SchoolStudent.SchoolRepo.Schoolrep;
import com.example.demo.SchoolStudent.SchoolService.Schoolserv;
import com.example.demo.SchoolStudent.Util.CsvConversion;
import com.example.demo.SchoolStudent.Util.ExcelConversion;
import com.example.demo.SchoolStudent.Util.PdfConversion;
import com.example.demo.SchoolStudent.Util.XlConversion;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping("/SCHOOL")
@CrossOrigin(origins = "http://localhost:4200")
public class Schoolcont
{
    @Autowired
    private Schoolserv schoolserv;
    @Autowired
    private Schoolrep schoolrep;



    @PostMapping("/post")
    public ResponseEntity<?> createStudents(@RequestBody Schoolent schoolent)
    {
        return schoolserv.addStudent(schoolent);
    }
//    @PostMapping("/learn")
//    public ResponseEntity<?> learn (@RequestBody Learnent learnent)
//    {
//        return schoolserv.learn(learnent);
//    }
//    @GetMapping ("learntoget")
//    public ResponseEntity<?> learnget (@RequestParam Integer id )
//    {
//        return schoolserv.learnget(id);
//    }
    @GetMapping("/validating")
    public ResponseEntity<?> validating(@RequestParam String username, String password)
    {
        return schoolserv.checkUser(username,password);
    }
    @PostMapping("/registerpost")
    public ResponseEntity<?> Studentregistion(@RequestBody Registerent registerent)
    {
        return schoolserv.studentRegi(registerent);
    }
    @GetMapping("/registerget")
    public ResponseEntity<?> get(@RequestParam Integer id)
    {
        return schoolserv.get(id);

    }

     @GetMapping("/viewall")
    public List<?> getAllStudents()
    {
        return schoolserv.viewAllStudents();
    }
    @GetMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Integer id)
    {
        return schoolserv.delete(id);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateCurdEntity(@RequestParam Integer id, @RequestBody Registerent studentDetails)

    {
        return schoolserv.updateCurdEntity(id, studentDetails);
    }
    @GetMapping("/totalStudents")
    public ResponseEntity<?>getTotalStudent()
    {
        return schoolserv.retrieveCourse();
    }
    @GetMapping("/courseDetail")
    public ResponseEntity<?> getCourseCount()
    {
//System.out.println();
        return schoolserv.retrieveCourseCount();
    }
    @GetMapping("/allId")
    public ResponseEntity<?> getAllId()
    {
        return schoolserv.findAllId();
    }
    @GetMapping("/convert")
    public ResponseEntity<?> download(@RequestParam("file") String file, HttpServletResponse response) throws IOException {
        if (file.equalsIgnoreCase("pdf")) {
            List<Registerent> listUsers = schoolserv.getAllStudents();
            PdfConversion conversion = new PdfConversion(listUsers);
            conversion.export(response);
            JSONObject obj1 = new JSONObject();
            obj1.put("Status", "Successfully downloaded the PDF file");
            return new ResponseEntity(obj1, HttpStatus.OK);
        }
        else if (file.equalsIgnoreCase("csv")) {
            List<Registerent> listUsers = schoolserv.getAllStudents();
            CsvConversion conversion = new CsvConversion(listUsers);
            conversion.exportToCSV(response);
            JSONObject obj = new JSONObject();
            obj.put("Status", "Successfully downloaded the CSV file");
            return new ResponseEntity(obj, HttpStatus.OK);

        }
        else if (file.equalsIgnoreCase("Xls"))
        {
            List<Registerent> listOfStudents = schoolserv.getAllStudents();
            XlConversion generator = new XlConversion(listOfStudents);
            generator.generateXls(response);
            JSONObject obj = new JSONObject();
            obj.put("Status", "Successfully downloaded the XLS file");
            return new ResponseEntity(obj, HttpStatus.OK);
        }
        else if (file.equalsIgnoreCase("Xlsx"))
        {
            List<Registerent> listOfStudents = schoolserv.getAllStudents();
            ExcelConversion generator = new ExcelConversion(listOfStudents);
            generator.generateExcelFile(response);
            JSONObject obj = new JSONObject();
            obj.put("Status", "Successfully downloaded the XLSX file");
            return new ResponseEntity(obj, HttpStatus.OK);

        }
        else
        {
            JSONObject obj = new JSONObject();
            obj.put("Status", "File type doesn't match");
            return new ResponseEntity(obj, HttpStatus.OK);
        }
//
//        return new ResponseEntity("outpur", HttpStatus.OK);
    }
    @GetMapping("/Paging")
    public ResponseEntity<?> getAll (@RequestParam String field, @RequestParam Integer page, @RequestParam Integer size,@RequestParam String type)
    {
        return schoolserv.getAll5(field, page, size, type);
    }
    @GetMapping("/dob")
    public ResponseEntity<?> retrieveByDob(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end )
    {
        return schoolserv.getbydob(start, end);
    }

    @Autowired
    private Learnrepo learnrepo;
    @PostMapping("/mappingmethod")
    public ResponseEntity<String> savedata(@RequestBody List<Learnent> empData) {
        learnrepo.saveAll(empData);
        return ResponseEntity.ok("data saved");
    }


    @Autowired
    private Onerep onerep;
    @PostMapping("/onetomany")
    public ResponseEntity<String> onetoonedata (@RequestBody List<Onetoent> empData){
        onerep.saveAll(empData);
        return ResponseEntity.ok("onetomanydata");
    }

    @GetMapping("/getonebymany")
    public ResponseEntity<?> getbyid(@RequestParam Integer id)
    {
        return schoolserv.getbyid(id);

    }
  @PutMapping("/manytomany")
    public Manytomainent manytomany(
            @RequestParam Integer mainid,
            @RequestParam Integer simpleid
  ){
        return schoolserv.manytomany(mainid,simpleid);
  }

  @PostMapping("/savemany")
    public ResponseEntity<Manytomainent> savemany(@RequestBody Manytomainent empobj){
        schoolserv.saveManytomainent(empobj);
        return new ResponseEntity<>(HttpStatus.CREATED);
  }


    @PostMapping("/savemanytoo")
    public ResponseEntity<Manytomanyent> savemany(@RequestBody Manytomanyent empobj){
        schoolserv.saveManytomanyent(empobj);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
  @GetMapping("/getmanytomany")
    public List<Manytomainent> getmanytomany(@PathVariable(required = false) Integer mainid){
        return schoolserv.getdetials(mainid);
  }


  @PostMapping("/emailsending")
    public ResponseEntity<?> mailsending(@RequestBody Emailent mail) throws MessagingException {
        schoolserv.sendemail(mail);
        return new ResponseEntity<>(HttpStatus.OK);
  }
}
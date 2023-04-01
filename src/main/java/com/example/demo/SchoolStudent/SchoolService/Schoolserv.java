package com.example.demo.SchoolStudent.SchoolService;

import com.example.demo.SchoolStudent.SchoolEntity.*;
import com.example.demo.SchoolStudent.SchoolRepo.*;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.poi.ss.formula.functions.T;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

@Service
public class Schoolserv {
    @Autowired
    private Schoolrep schoolrep;
    @Autowired Learnrepo learnrepo;
    @Autowired
    private Registerrepo registerrepo;
    @Autowired
    private Onerep onerep;

    @Autowired
    private Manytomainrep manytomainrep;

    @Autowired
    private Manytomanyrep manytomanyrep;

    @Autowired
    private JavaMailSender javaMailSender;




    public ResponseEntity<?> addStudent(@RequestBody Schoolent schoolent) {
        schoolrep.save(schoolent);
        JSONObject obj = new JSONObject();
        obj.put("Status", "Successfully updated the entry");
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    public ResponseEntity <?> learn (@RequestBody Learnent learnent){
        learnrepo.save(learnent);
        JSONObject obj=new JSONObject();
        obj.put("Status","Run successfully");
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    public ResponseEntity<?> checkUser(@RequestParam String username, String password) {
        try {
            Object oj = schoolrep.findByUsernameAndPassword(username, password);
            if (oj != null) {
                JSONObject obj = new JSONObject();
                obj.put("Status", "Username and password is valid");
                return new ResponseEntity<>(obj, HttpStatus.OK);
            } else {
                throw new RuntimeException();
            }
        } catch (Exception ex) {
            JSONObject obj = new JSONObject();
            obj.put("Status", "Username and password is not valid");
            return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> studentRegi(@RequestBody Registerent registerent) {
        registerrepo.save(registerent);
        JSONObject obj = new JSONObject();
        obj.put("Status", "Successfully updated the entry");
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    public ResponseEntity<?> get(Integer id) {
        try {
//            Optional<Registerent> obj = registerrepo.findById(id);
            Registerent s = registerrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:" + id));
            return new ResponseEntity<>(registerrepo.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            JSONObject obj = new JSONObject();
            obj.put("Status", "Student id does not exist");
            return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
        }
    }

//    public ResponseEntity<?> learnget(Integer id) {
//        try {
////           Optional<Learnent> obj = learnrepo.findById(id);
//            Learnent s = learnrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:" + id));
//            return new ResponseEntity<>(learnrepo.findById(id), HttpStatus.OK);
//        } catch (Exception ex) {
//            JSONObject obj = new JSONObject();
//            obj.put("Status", "Student id does not exist");
//            return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
//        }
//    }

    public List<?> viewAllStudents()
    {
        return registerrepo.findAll();
    }

    public ResponseEntity<?> delete(Integer id)
    {
        registerrepo.deleteById(id);
        JSONObject obj = new JSONObject();
        obj.put("Status", "Successfully deleted the entry");
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    public ResponseEntity<?> updateCurdEntity(Integer id, @RequestBody Registerent studentDetails)
    {
        Registerent student = registerrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:" + id));
        Registerent update = registerrepo.findById(id).get();
      // Registerent update = updateEmp.get();
        update.setFirstname(studentDetails.getFirstname());
        update.setLastname(studentDetails.getLastname());
        update.setDob(studentDetails.getDob());
        update.setMail(studentDetails.getMail());
        update.setGender(studentDetails.getGender());
        update.setAddress(studentDetails.getAddress());
        update.setCity(studentDetails.getCity());
        update.setPhone(studentDetails.getPhone());
        update.setHobbies(studentDetails.getHobbies());
        update.setPincode(studentDetails.getPincode());
        update.setState(studentDetails.getState());
        update.setCountry(studentDetails.getCountry());
        update.setQualification10(studentDetails.getQualification10());
        update.setQualification12(studentDetails.getQualification12());
        update.setCourses(studentDetails.getCourses());
        registerrepo.save(update);
        JSONObject obj = new JSONObject();
        obj.put("Status", "Successfully updated the entry");
        return new ResponseEntity(obj, HttpStatus.OK);
    }

    public ResponseEntity<?> retrieveCourse()
    {
        return ResponseEntity.ok(registerrepo.totalStudents());
    }


    public ResponseEntity<?> retrieveCourseCount()
    {

        ArrayList<ArrayList> obj = registerrepo.courseDetail();
        LinkedHashMap<Object, Object> linkedValues = new LinkedHashMap<>();
        for (ArrayList obj1 : obj)
        {
            ArrayList lt = obj1;
            linkedValues.put(lt.get(0), lt.get(1));
        }
        return new ResponseEntity<>(linkedValues, HttpStatus.OK);
    }

    public ResponseEntity<?> findAllId()
    {

        return new ResponseEntity<>(registerrepo.idList() , HttpStatus.OK);
    }
    public List<Registerent>getAllStudents()
    {
        return registerrepo.findAll();
    }

    public ResponseEntity<?> getbyid (Integer id)
    {
        return new ResponseEntity<>(onerep.findById(id), HttpStatus.OK);
    }


    public ResponseEntity<?> getAll5 (@RequestParam String field, @RequestParam Integer page, @RequestParam Integer size,@RequestParam String type) {

// Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, field);
// return new ResponseEntity<>(curdRepo.findAll(pageable).toList(),HttpStatus.OK) ;
        try {
            if (type.equalsIgnoreCase("asc")) {
                Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, field);
                return new ResponseEntity<>(registerrepo.findAll(pageable).toList(), HttpStatus.OK);
            }
            if (type.equalsIgnoreCase("desc")) {

                Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, field);
                return new ResponseEntity<>(registerrepo.findAll(pageable).toList(), HttpStatus.OK);
            }
            return null;
        } catch (Exception ex) {
            return new ResponseEntity<>("Page starts from index 1", HttpStatus.BAD_REQUEST);
        }
    }


        public ResponseEntity<List<Registerent>> getbydob (LocalDate start, LocalDate end)
        {
            return new ResponseEntity<>(registerrepo.findByDateBetween(start, end), HttpStatus.OK);
        }


    public Manytomainent manytomany(Integer mainid, Integer simpleid) {
        Set<Manytomanyent> manytomanyentSet=null;
        Manytomainent manytomainent=manytomainrep.findById(mainid).get();
        Manytomanyent manytomanyent=manytomanyrep.findById(simpleid).get();
        manytomanyentSet = manytomainent.getManytomanyents();
        manytomanyentSet.add(manytomanyent);
        manytomainent.setManytomanyents(manytomanyentSet);
        return manytomainrep.save(manytomainent);
    }

    public void saveManytomainent(Manytomainent manytomainent)
    {
        manytomainrep.save(manytomainent);
    }

    public List<Manytomainent> getdetials(Integer mainid) {
        if (null !=mainid){
            return manytomainrep.findAllById(Collections.singleton(mainid));
        }
        else {
            return manytomainrep.findAll();
        }
    }

    public void saveManytomanyent(Manytomanyent empobj) {
        manytomanyrep.save(empobj);
    }


    public void sendemail(Emailent mail)
            throws MessagingException {
        MimeMessage minemessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(minemessage,true);


      mimeMessageHelper.setFrom("aspin6789@gmail.com");
      mimeMessageHelper.setTo(mail.getTo());
      mimeMessageHelper.setText(mail.getBody());
      mimeMessageHelper.setSubject(mail.getSubject());


        FileSystemResource fileSystemResource=new FileSystemResource(new File(mail.getAttachment()));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        javaMailSender.send(minemessage);

        System.out.println("mail send successfully");


    }
}


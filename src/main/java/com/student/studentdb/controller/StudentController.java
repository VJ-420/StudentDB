package com.student.studentdb.controller;
import com.student.studentdb.Exceptions.ResourceNotFound;
import com.student.studentdb.Exceptions.UnableToAdd;
import com.student.studentdb.entity.Student;
import com.student.studentdb.service.Service;
import com.student.studentdb.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private final StudentService service;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    public StudentController(Service service) {
        this.service = service;
    }

    @GetMapping("/student")
    public List<Student> getStudents(){
            List<Student> list = this.service.getAllStudents();
            if(list.isEmpty()){
                throw new ResourceNotFound("List is empty");
            }
            logger.info("List of students given");
            return list;
    }

    @GetMapping("/student/{id}")
    public Student getStudentByid(@PathVariable("id") int id ){
        Student s= this.service.getStudentById(id);
        if(s==null){
            throw new ResourceNotFound("No Data Present");

        }
        logger.info("Student data given");
        return s;
    }

    @PostMapping("/student/add")
    public Student addStudent(@RequestBody Student student){
        Student s = this.service.addStudent(student);
        if(s==null){
            throw new UnableToAdd("Not able to add");
        }
        logger.info("Student Added");
        return s;
    }

    @DeleteMapping("/student/delete/{id}")
    public void deleteStudent(@PathVariable("id") int id) throws Exception {
       try{
           this.service.deleteStudent(id);
           logger.info("Student Deleted");
       }catch(Exception e){
           throw new Exception("Unable to delete");
       }
    }

    @PutMapping("/student/update/{id}")
    public Student updateStudent(@PathVariable("id") int id , @RequestBody Student student ) throws Exception {
        Student s = this.service.update(id,student);
        if(s==null){
            throw new Exception("Unable to Update");
        }
        logger.info("Student Deleted");
        return s;
    }
}


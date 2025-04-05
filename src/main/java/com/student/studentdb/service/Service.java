package com.student.studentdb.service;

import com.student.studentdb.Repo.StudentRepo;
import com.student.studentdb.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Service implements StudentService {
    @Autowired
    private StudentRepo StudentRepo;

    @Override
    public List<Student> getAllStudents(){
        List<Student> list = (List<Student>) this.StudentRepo.findAll();
        return list;
    }
    @Override
    public Student getStudentById(int id){
        Student student = this.StudentRepo.findById(id).orElse(null);
        return student;
    }
    @Override
    public Student addStudent(Student student){
        Student s= this.StudentRepo.save(student);
        return s;
    }
    @Override
    public void deleteStudent(int id){
        StudentRepo.deleteById(id);
    }
    @Override
    public Student update(int id, Student student){
        student.setId(id);
        this.StudentRepo.save(student);
        return student;
    }










}

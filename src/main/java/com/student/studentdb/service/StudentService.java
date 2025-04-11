package com.student.studentdb.service;

import com.student.studentdb.entity.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getAllStudents();

    public Student getStudentById(int id);

    public Student addStudent(Student student);

    public void deleteStudent(int id);

    public Student update(int id, Student student);

    public Student getStudentByName(String Username);
}

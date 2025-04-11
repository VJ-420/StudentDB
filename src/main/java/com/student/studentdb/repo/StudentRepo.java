package com.student.studentdb.repo;
import com.student.studentdb.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends CrudRepository<Student, Integer> {

    public Student getStudentByName(String username);
}

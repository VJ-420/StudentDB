package com.student.studentdb.Repo;
import com.student.studentdb.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Integer> {
}

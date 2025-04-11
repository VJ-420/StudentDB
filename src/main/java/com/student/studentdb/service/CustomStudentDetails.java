package com.student.studentdb.service;

import com.student.studentdb.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomStudentDetails implements UserDetailsService {

    @Autowired
    private final StudentService service;

    public CustomStudentDetails(StudentService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = service.getStudentByName(username);
        if(student != null){
            return new CustomStudentDetailsImpl(student);
        }
       throw new UsernameNotFoundException("User not found with Username"+ username);
    }
}

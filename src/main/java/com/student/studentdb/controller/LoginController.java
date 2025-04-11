package com.student.studentdb.controller;

import com.student.studentdb.entity.Student;
import com.student.studentdb.service.JwtService;
import com.student.studentdb.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private final StudentService service;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(StudentService service, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.service = service;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @GetMapping("/csrf")
    public CsrfToken getToken (HttpServletRequest request ){
       return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/register")
    public Student register(@RequestBody Student user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return service.addStudent(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Student user){

       Authentication authenticate =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getName() , user.getPassword()
                )
        );

        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(user);
        }
        return "Failed";
    }
}

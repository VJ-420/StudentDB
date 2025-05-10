package com.student.studentdb.controller;
import com.student.studentdb.entity.Student;
import com.student.studentdb.service.JWTService;
import com.student.studentdb.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private final StudentService service;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(StudentService service, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager) {
        this.service = service;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public Student register(@RequestBody Student user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return service.addStudent(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Student user) {
        logger.info("Login attempt for user: {}", user.getName());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

            if (authentication.isAuthenticated()) {
                logger.info("User authenticated: {}", user.getName());
                String token = jwtService.generateToken(user.getName());
                return ResponseEntity.ok(token);
            } else {
                logger.warn("Authentication failed for user: {}", user.getName());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
            }
        } catch (Exception e) {
            logger.error("Exception during login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login error: " + e.getMessage());
        }
    }


}



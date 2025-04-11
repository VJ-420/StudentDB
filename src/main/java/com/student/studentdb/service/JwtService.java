package com.student.studentdb.service;
import com.student.studentdb.entity.Student;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@Service
public class JwtService {
    private  final String key = "af965bb01038cf124bc2670893107c248815090c9bac1b926dd0634b46015d81";
    public String generateToken(Student user){
        Map<String , Object> claims
                = new HashMap<>();
        claims.put("username", user.getName());
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(user.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}


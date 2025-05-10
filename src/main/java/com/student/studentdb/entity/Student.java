package com.student.studentdb.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name="Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String name;
    private LocalDate dob;
    private LocalDate joinDate;
    private String password;

    public Student(){
    }

    public Student(int id, String name, LocalDate dob, LocalDate joinDate , String password) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.joinDate = joinDate;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDOB(LocalDate dob) {
        this.dob = dob;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getdob() {
        return dob;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }






}

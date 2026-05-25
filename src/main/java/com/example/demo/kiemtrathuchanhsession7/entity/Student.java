package com.example.demo.kiemtrathuchanhsession7.entity;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;


@Entity
@Table(name = "students")
@JsonRootName("student")
@JacksonXmlRootElement(localName = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String fullName;
    private String email;
    private double gpa;

    public Student(){}

    public Student(String email, double gpa, String fullName, Long id) {
        this.email = email;
        this.gpa = gpa;
        this.fullName = fullName;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}

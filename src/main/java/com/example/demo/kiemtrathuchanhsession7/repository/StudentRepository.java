package com.example.demo.kiemtrathuchanhsession7.repository;

import com.example.demo.kiemtrathuchanhsession7.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
}

package com.example.demo.kiemtrathuchanhsession7;

import com.example.demo.kiemtrathuchanhsession7.entity.Student;
import com.example.demo.kiemtrathuchanhsession7.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class KiemTraThucHanhSession7Application {

    public static void main(String[] args) {
        SpringApplication.run(KiemTraThucHanhSession7Application.class, args);
    }

    @Bean
    CommandLineRunner seedStudents(StudentRepository studentRepository) {
        return args -> {
            List<Student> students = List.of(
                    student("Nguyen Manh Hung", "hung@gmail.com", 3.4),
                    student("Mai Duy Anh", "anh@gmail.com", 3.8),
                    student("Nguyen Sy Trung", "trung@gmail.com", 3.6)
            );

            students.stream()
                    .filter(student -> !studentRepository.existsByEmail(student.getEmail()))
                    .forEach(studentRepository::save);
        };
    }

    private static Student student(String fullName, String email, double gpa) {
        Student student = new Student();
        student.setFullName(fullName);
        student.setEmail(email);
        student.setGpa(gpa);
        return student;
    }

}

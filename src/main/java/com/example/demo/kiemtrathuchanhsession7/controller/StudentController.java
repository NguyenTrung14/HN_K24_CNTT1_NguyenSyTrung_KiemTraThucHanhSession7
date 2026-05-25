package com.example.demo.kiemtrathuchanhsession7.controller;

import com.example.demo.kiemtrathuchanhsession7.entity.Student;
import com.example.demo.kiemtrathuchanhsession7.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository studentRepository;

    @GetMapping
    public ResponseEntity<List<?>> findAll(){
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id){
        return studentRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student){
        student.setId(null);
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(201).body(savedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student){
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setFullName(student.getFullName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setGpa(student.getGpa());

            Student updatedStudent = studentRepository.save(existingStudent);
            return ResponseEntity.ok(updatedStudent);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> patch(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        return studentRepository.findById(id).map(student ->{
            if(updates.containsKey("fullName")){
                student.setFullName(updates.get("fullName").toString());
            }
            if(updates.containsKey("email")){
                student.setEmail(updates.get("email").toString());
            }
            if(updates.containsKey("gpa")){
                student.setGpa(Double.valueOf(updates.get("gpa").toString()));
            }

            Student updatedStudent = studentRepository.save(student);
            return ResponseEntity.ok(updatedStudent);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!studentRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

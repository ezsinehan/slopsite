package com.fb2devs.slopsitebackend.controller;

import com.fb2devs.slopsitebackend.dto.StudentDTO;
import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
public List<StudentDTO> getAllStudents() {
    return studentService.getAllStudentDtos();
}


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(studentService.getStudentById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            return ResponseEntity.ok(studentService.saveStudent(student));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

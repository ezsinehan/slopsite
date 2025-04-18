package com.fb2devs.slopsitebackend.controller;

import com.fb2devs.slopsitebackend.dto.LoginRequest;
import com.fb2devs.slopsitebackend.dto.LoginResponse;
import com.fb2devs.slopsitebackend.model.Admin;
import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.model.Teacher;
import com.fb2devs.slopsitebackend.repository.AdminRepository;
import com.fb2devs.slopsitebackend.repository.StudentRepository;
import com.fb2devs.slopsitebackend.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Try to authenticate as Admin
        Optional<Admin> adminOpt = adminRepository.findByUsernameAndPassword(
                request.getUsername(), request.getPassword());

        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return ResponseEntity.ok(new LoginResponse("admin", admin.getId().longValue(), admin.getName()));
        }

        // Try to authenticate as Student
        Optional<Student> studentOpt = studentRepository.findByUsernameAndPassword(
                request.getUsername(), request.getPassword());

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return ResponseEntity.ok(new LoginResponse("student", student.getId().longValue(), student.getName()));
        }

        // Try to authenticate as Teacher
        Optional<Teacher> teacherOpt = teacherRepository.findByUsernameAndPassword(
                request.getUsername(), request.getPassword());

        if (teacherOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            return ResponseEntity.ok(new LoginResponse("teacher", teacher.getId().longValue(), teacher.getName()));
        }

        // None matched
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}

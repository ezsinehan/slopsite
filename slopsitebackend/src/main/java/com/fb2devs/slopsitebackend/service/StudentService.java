package com.fb2devs.slopsitebackend.service;

import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepo;

    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student getByUsername(String username) {
        return studentRepo.findByUsername(username);
    }

    // Add this method to StudentService
    public Student findById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public Student authenticate(String username, String password) {
        return studentRepo.findByUsernameAndPassword(username, password)
            .orElseThrow(() -> new RuntimeException("Invalid username or password"));
    }
    
}

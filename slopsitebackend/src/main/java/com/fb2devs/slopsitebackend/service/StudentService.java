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
}

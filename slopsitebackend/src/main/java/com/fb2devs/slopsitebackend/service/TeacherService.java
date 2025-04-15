package com.fb2devs.slopsitebackend.service;

import com.fb2devs.slopsitebackend.model.Teacher;
import com.fb2devs.slopsitebackend.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher findByUsername(String username) {
        return teacherRepository.findByUsername(username);
    }

    // Add this method to TeacherService
    public Teacher findById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
    }

    public Teacher authenticate(String username, String password) {
        return teacherRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
    }
}
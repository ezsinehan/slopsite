package com.fb2devs.slopsitebackend.service;

import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepo;

    @Autowired
    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(Integer id) {
      return studentRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Student with ID " + id + " not found"));
  }

    public Student saveStudent(Student student) {
      if (student.getUsername() == null || student.getUsername().isBlank()) {
          throw new IllegalArgumentException("Username must not be empty");
      }
      return studentRepo.save(student);
    }

    public void deleteStudent(Integer id) {
      if (!studentRepo.existsById(id)) {
          throw new IllegalStateException("Cannot delete: student with ID " + id + " does not exist");
      }
      studentRepo.deleteById(id);
    }
  

    // Example passthrough if you had a custom query like getEnrollments()
    // public List<Enrollment> getEnrollments(Integer studentId) {
    //     return studentRepo.getEnrollments(studentId);
    // }
}

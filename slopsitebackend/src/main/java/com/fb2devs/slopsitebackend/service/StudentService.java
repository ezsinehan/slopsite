package com.fb2devs.slopsitebackend.service;

import com.fb2devs.slopsitebackend.dto.EnrollmentInfo;
import com.fb2devs.slopsitebackend.dto.StudentDTO;
import com.fb2devs.slopsitebackend.model.Enrollment;
import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.repository.EnrollmentRepository;
import com.fb2devs.slopsitebackend.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepo;
    private final EnrollmentRepository enrollmentRepo; // Add this line to inject the EnrollmentRepository

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public StudentService(StudentRepository studentRepo , EnrollmentRepository enrollmentRepo) {
        this.studentRepo = studentRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    public Student getStudentById(Integer id) {
      Student student = studentRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Student not found"));
  
      // Force fetch fresh enrollments
      student.setEnrollments(enrollmentRepo.findByStudent(student));
  
      return student;
  }
  
  public StudentDTO getStudentDtoById(Integer id) {
    Student student = studentRepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));

    List<Enrollment> freshEnrollments = enrollmentRepo.findByStudent(student);
    return new StudentDTO(student, freshEnrollments);
}

public List<StudentDTO> getAllStudentDtos() {
  List<Student> students = studentRepo.findAll();
  return students.stream()
      .map(student -> {
          List<Enrollment> freshEnrollments = enrollmentRepo.findByStudent(student); // ✅ forces refresh
          return new StudentDTO(student, freshEnrollments);
      })
      .toList();
}


    // ✅ Get all students
    public List<Student> getAllStudents() {
      List<Student> students = studentRepo.findAll();
      students.forEach(s -> s.setEnrollments(enrollmentRepo.findByStudent(s)));
      return students;
  }
  

    // ✅ Create or update a student
    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    // ✅ Delete a student
    public void deleteStudent(Integer id) {
        if (!studentRepo.existsById(id)) {
            throw new IllegalStateException("Student with ID " + id + " does not exist");
        }
        studentRepo.deleteById(id);
    }
}

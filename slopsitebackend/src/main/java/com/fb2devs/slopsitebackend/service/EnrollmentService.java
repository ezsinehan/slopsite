package com.fb2devs.slopsitebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Enrollment;
import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.repository.EnrollmentRepository;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepo) {
        this.enrollmentRepo = enrollmentRepo;
    }

    public List<Enrollment> getEnrollmentsByStudent(Student student) {
        if (student == null || student.getId() == null) {
            throw new IllegalArgumentException("Student or student ID must not be null");
        }
        return enrollmentRepo.findByStudent(student);
    }

    public List<Enrollment> getEnrollmentsByCourse(Course course) {
        if (course == null || course.getId() == null) {
            throw new IllegalArgumentException("Course or course ID must not be null");
        }
        return enrollmentRepo.findByCourse(course);
    }

    public List<Course> getCoursesByStudent(Student student) {
        if (student == null || student.getId() == null) {
            throw new IllegalArgumentException("Student or student ID must not be null");
        }
        return enrollmentRepo.findCoursesByStudent(student);
    }

    public List<Student> getStudentsByCourse(Course course) {
        if (course == null || course.getId() == null) {
            throw new IllegalArgumentException("Course or course ID must not be null");
        }
        return enrollmentRepo.findStudentsByCourse(course);
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepo.save(enrollment);
    }

    public void deleteEnrollment(Integer id) {
        if (!enrollmentRepo.existsById(id)) {
            throw new IllegalStateException("Enrollment with ID " + id + " does not exist");
        }
        enrollmentRepo.deleteById(id);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }

}


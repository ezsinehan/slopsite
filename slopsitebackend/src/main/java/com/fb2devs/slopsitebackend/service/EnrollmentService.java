package com.fb2devs.slopsitebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Enrollment;
import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.repository.EnrollmentRepository;
import com.fb2devs.slopsitebackend.repository.CourseRepository;

import jakarta.transaction.Transactional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final CourseRepository courseRepo;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepo, CourseRepository courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.courseRepo = courseRepo;
    }

    public Enrollment getEnrollmentById(Integer id) {
        return enrollmentRepo.findById(id).orElseThrow(() ->
            new IllegalArgumentException("Enrollment with ID " + id + " not found"));
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
        Enrollment enrollment = enrollmentRepo.findById(id)
            .orElseThrow(() -> new IllegalStateException("Enrollment with ID " + id + " does not exist"));
    
        Student student = enrollment.getStudent();
        if (student != null) {
            student.getEnrollments().remove(enrollment); // Important: remove from both sides
        }
    
        enrollmentRepo.deleteById(id);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }

    public void deleteEnrollmentsByCourseId(Integer courseId) {
        Course course = courseRepo.findById(courseId)
            .orElseThrow(() -> new IllegalArgumentException("Course with ID " + courseId + " not found"));
    
        List<Enrollment> enrollments = getEnrollmentsByCourse(course);
    
        for (Enrollment enrollment : enrollments) {
            deleteEnrollment(enrollment.getId());
        }
    }
}

package com.fb2devs.slopsitebackend.controller;

import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Enrollment;
import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.service.CourseService;
import com.fb2devs.slopsitebackend.service.EnrollmentService;
import com.fb2devs.slopsitebackend.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentController(EnrollmentService enrollmentService, 
                               StudentService studentService,
                               CourseService courseService) {
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.saveEnrollment(enrollment);
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable Long studentId) {
        Student student = studentService.findById(studentId);
        return enrollmentService.getEnrollmentsByStudent(student);
    }

    @GetMapping("/course/{courseId}")
    public List<Enrollment> getEnrollmentsByCourse(@PathVariable Long courseId) {
        Course course = courseService.findById(courseId);
        return enrollmentService.getEnrollmentsByCourse(course);
    }

    @GetMapping("/check")
    public boolean checkEnrollment(@RequestParam Long studentId, @RequestParam Long courseId) {
        Student student = studentService.findById(studentId);
        Course course = courseService.findById(courseId);
        return enrollmentService.isStudentEnrolledInCourse(student, course);
    }

    @PatchMapping("/{enrollmentId}/grade")
public Enrollment updateGrade(@PathVariable Long enrollmentId, @RequestBody String grade) {
    return enrollmentService.updateGrade(enrollmentId, grade);
}
}
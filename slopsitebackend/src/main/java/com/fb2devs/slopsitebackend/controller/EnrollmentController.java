package com.fb2devs.slopsitebackend.controller;

import com.fb2devs.slopsitebackend.dto.CourseWithEnrollmentInfo;
import com.fb2devs.slopsitebackend.dto.EnrollmentInfo;
import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Enrollment;
import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.service.CourseService;
import com.fb2devs.slopsitebackend.service.EnrollmentService;
import com.fb2devs.slopsitebackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public EnrollmentController(
            EnrollmentService enrollmentService,
            StudentService studentService,
            CourseService courseService
    ) {
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    //to do make sure adding enrollments works for students
    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.saveEnrollment(enrollment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Integer id) {
        try {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudent(@PathVariable Integer studentId) {
        try {
            Student student = studentService.getStudentById(studentId);
            return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(student));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-course/{courseId}")
public ResponseEntity<List<EnrollmentInfo>> getEnrollmentsByCourse(@PathVariable Integer courseId) {
    try {
        Course course = courseService.getCourseById(courseId);
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourse(course);

        List<EnrollmentInfo> result = enrollments.stream()
            .map(e -> new EnrollmentInfo(
                e.getStudent().getId().longValue(),
                e.getStudent().getName(),
                e.getGrade()
            ))
            .toList();

        return ResponseEntity.ok(result);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
    }
}


    //asdfljka
    @GetMapping("/courses-by-student/{studentId}")
public ResponseEntity<List<CourseWithEnrollmentInfo>> getCoursesByStudent(@PathVariable Integer studentId) {
    try {
        Student student = studentService.getStudentById(studentId);
        List<Course> courses = enrollmentService.getCoursesByStudent(student);

        List<CourseWithEnrollmentInfo> result = courses.stream().map(course -> {
            int currentEnrollment = enrollmentService.getEnrollmentsByCourse(course).size();
            return new CourseWithEnrollmentInfo(
                course.getId(),
                course.getName(),
                course.getTotalCapacity(),    // ✅ correct method
                currentEnrollment
            );

        }).toList();

        return ResponseEntity.ok(result);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
    }
}


    @GetMapping("/students-by-course/{courseId}")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable Integer courseId) {
        try {
            Course course = courseService.getCourseById(courseId);
            return ResponseEntity.ok(enrollmentService.getStudentsByCourse(course));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

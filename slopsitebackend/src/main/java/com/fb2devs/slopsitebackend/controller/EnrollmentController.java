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

    // ✅ Create an enrollment
    @PostMapping
    public ResponseEntity<?> createEnrollment(@RequestBody Enrollment enrollment) {
        try {
            Course course = courseService.getCourseById(enrollment.getCourse().getId());
            int currentEnrollment = enrollmentService.getEnrollmentsByCourse(course).size();

            if (currentEnrollment >= course.getTotalCapacity()) {
                return ResponseEntity
                    .badRequest()
                    .body("Enrollment failed: course is already full.");
            }

            return ResponseEntity.ok(enrollmentService.saveEnrollment(enrollment));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid course or student.");
        }
    }


    // ✅ Delete an enrollment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Integer id) {
        try {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Get all courses a student is enrolled in (with details)
    @GetMapping("/courses-by-student/{studentId}")
    public ResponseEntity<List<CourseWithEnrollmentInfo>> getCoursesByStudent(@PathVariable Integer studentId) {
        try {
            Student student = studentService.getStudentById(studentId);
            List<Course> courses = enrollmentService.getCoursesByStudent(student);

            List<CourseWithEnrollmentInfo> result = courses.stream().map(course -> {
                int currentEnrollment = enrollmentService.getEnrollmentsByCourse(course).size();
                String teacherName = (course.getTeacher() != null) ? course.getTeacher().getName() : "TBD";

                return new CourseWithEnrollmentInfo(
                    course.getId(),
                    course.getName(),
                    teacherName,
                    course.getTime(),
                    course.getTotalCapacity(),
                    currentEnrollment
                );
            }).toList();

            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Get all enrollments in a course (student name + grade)
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
                )).toList();

            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Get all students enrolled in a course
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

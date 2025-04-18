package com.fb2devs.slopsitebackend.controller;

import com.fb2devs.slopsitebackend.dto.CourseWithEnrollmentInfo;
import com.fb2devs.slopsitebackend.dto.CourseWithStudentStatus;
import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Enrollment;
import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.model.Teacher;
import com.fb2devs.slopsitebackend.repository.EnrollmentRepository;
import com.fb2devs.slopsitebackend.service.CourseService;
import com.fb2devs.slopsitebackend.service.EnrollmentService;
import com.fb2devs.slopsitebackend.service.StudentService;
import com.fb2devs.slopsitebackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final EnrollmentRepository enrollmentRepo;
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;

    @Autowired
    public CourseController(
        CourseService courseService,
        TeacherService teacherService,
        EnrollmentRepository enrollmentRepo,
        StudentService studentService,
        EnrollmentService enrollmentService
    ) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.enrollmentRepo = enrollmentRepo;
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
    }

    // ✅ Get all courses with enrollment info
    @GetMapping
    public ResponseEntity<List<CourseWithEnrollmentInfo>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();

        List<CourseWithEnrollmentInfo> result = courses.stream().map(course -> {
            int enrollmentCount = enrollmentRepo.countByCourse(course);
            String teacherName = (course.getTeacher() != null) ? course.getTeacher().getName() : "TBD";

            return new CourseWithEnrollmentInfo(
                course.getId(),
                course.getName(),
                teacherName,
                course.getTime(),
                course.getTotalCapacity(),
                enrollmentCount
            );
        }).toList();

        return ResponseEntity.ok(result);
    }

    // ✅ Get a single course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(courseService.getCourseById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Create a course
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            return ResponseEntity.ok(courseService.saveCourse(course));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // ✅ Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Get courses by teacher
    @GetMapping("/by-teacher/{teacherId}")
    public ResponseEntity<List<CourseWithEnrollmentInfo>> getCoursesByTeacher(@PathVariable Integer teacherId) {
        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);
            List<Course> courses = courseService.getCoursesByTeacher(teacher);

            List<CourseWithEnrollmentInfo> result = courses.stream().map(course -> {
                int enrollmentCount = enrollmentRepo.countByCourse(course);
                String teacherName = (course.getTeacher() != null) ? course.getTeacher().getName() : "TBD";

                return new CourseWithEnrollmentInfo(
                    course.getId(),
                    course.getName(),
                    teacherName,
                    course.getTime(),
                    course.getTotalCapacity(),
                    enrollmentCount
                );
            }).toList();

            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Get all courses with enrollment status for a given student
    @GetMapping("/with-enrollment-status/{studentId}")
    public ResponseEntity<List<CourseWithStudentStatus>> getAllCoursesWithEnrollmentStatus(@PathVariable Integer studentId) {
        try {
            Student student = studentService.getStudentById(studentId);
            List<Course> allCourses = courseService.getAllCourses();
    
            // Get all enrollments for this student
            List<Enrollment> studentEnrollments = enrollmentService.getEnrollmentsByStudent(student);
    
            // Build a map from courseId → enrollmentId
            Map<Integer, Integer> enrolledCourseToEnrollmentId = studentEnrollments.stream()
                .collect(Collectors.toMap(
                    e -> e.getCourse().getId(),
                    Enrollment::getId
                ));
    
            List<CourseWithStudentStatus> result = allCourses.stream().map(course -> {
                int enrollmentCount = enrollmentRepo.countByCourse(course);
                boolean isEnrolled = enrolledCourseToEnrollmentId.containsKey(course.getId());
                Integer enrollmentId = enrolledCourseToEnrollmentId.get(course.getId());
                String teacherName = (course.getTeacher() != null) ? course.getTeacher().getName() : "TBD";
    
                return new CourseWithStudentStatus(
                    course.getId(),
                    course.getName(),
                    teacherName,
                    course.getTime(),
                    course.getTotalCapacity(),
                    enrollmentCount,
                    isEnrolled,
                    enrollmentId
                );
            }).toList();
    
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}

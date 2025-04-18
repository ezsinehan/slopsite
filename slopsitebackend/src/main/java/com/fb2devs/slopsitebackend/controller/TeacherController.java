package com.fb2devs.slopsitebackend.controller;

import com.fb2devs.slopsitebackend.dto.StudentGradeInfo;
import com.fb2devs.slopsitebackend.dto.TeacherCoursesResponse;
import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Teacher;
import com.fb2devs.slopsitebackend.service.CourseService;
import com.fb2devs.slopsitebackend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;

    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(teacherService.getTeacherById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        try {
            return ResponseEntity.ok(teacherService.saveTeacher(teacher));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Integer id) {
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{teacherId}/courses-with-grades")
    public ResponseEntity<List<TeacherCoursesResponse>> getCoursesWithGrades(@PathVariable Integer teacherId) {
        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);
            List<Course> courses = courseService.getCoursesByTeacher(teacher);

            List<TeacherCoursesResponse> response = courses.stream().map(course -> {
                List<StudentGradeInfo> studentGrades = course.getEnrollments().stream().map(enrollment ->
                    new StudentGradeInfo(
                        enrollment.getStudent().getId(),
                        enrollment.getStudent().getName(),
                        enrollment.getGrade()
                    )
                ).collect(Collectors.toList());

                return new TeacherCoursesResponse(course.getId(), course.getName(), studentGrades);
            }).collect(Collectors.toList());

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}



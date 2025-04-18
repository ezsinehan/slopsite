package com.fb2devs.slopsitebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Enrollment;
import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.model.Teacher;
import com.fb2devs.slopsitebackend.service.CourseService;
import com.fb2devs.slopsitebackend.service.EnrollmentService;
import com.fb2devs.slopsitebackend.service.StudentService;
import com.fb2devs.slopsitebackend.service.TeacherService;
import com.fb2devs.slopsitebackend.dto.AdminCourseDto;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired private StudentService studentService;
    @Autowired private TeacherService teacherService;
    @Autowired private CourseService courseService;
    @Autowired private EnrollmentService enrollmentService;

    // ===== STUDENTS =====
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);

    }
    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }


    // ===== TEACHERS =====
    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping("/teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @DeleteMapping("/teachers/{id}")
    public void deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
    
    }
    @PutMapping("/teachers/{id}")
public Teacher updateTeacher(@PathVariable Integer id, @RequestBody Teacher updatedTeacher) {
    Teacher existing = teacherService.getTeacherById(id);
    existing.setName(updatedTeacher.getName());
    return teacherService.saveTeacher(existing);
}

    // ===== COURSES =====
    @GetMapping("/courses")
    public List<AdminCourseDto> getAllCourses() {
    return courseService.getAllCourses().stream()
        .map(AdminCourseDto::new)
        .toList();
}


    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }

    @PutMapping("/courses/{id}")
public Course updateCourse(@PathVariable Integer id, @RequestBody Course updatedCourse) {
    Course existing = courseService.getCourseById(id);

    existing.setName(updatedCourse.getName());
    existing.setTime(updatedCourse.getTime());
    existing.setTotalCapacity(updatedCourse.getTotalCapacity());
    existing.setTeacher(updatedCourse.getTeacher());

    return courseService.saveCourse(existing);
}

@DeleteMapping("/courses/{courseId}/enrollments")
public void deleteEnrollmentsByCourse(@PathVariable Integer courseId) {
    enrollmentService.deleteEnrollmentsByCourseId(courseId);
}


    // ===== ENROLLMENTS =====
    @GetMapping("/enrollments")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @PostMapping("/enrollments")
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.saveEnrollment(enrollment);
    }

    @DeleteMapping("/enrollments/{id}")
    public void deleteEnrollment(@PathVariable Integer id) {
        enrollmentService.deleteEnrollment(id);

    }

    @PutMapping("/enrollments/{id}/grade")
    public Enrollment updateEnrollmentGrade(@PathVariable Integer id, @RequestParam int grade) {
    Enrollment enrollment = enrollmentService.getEnrollmentById(id);
    enrollment.setGrade(grade);
    return enrollmentService.saveEnrollment(enrollment);

}
}

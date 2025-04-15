package com.slopsite.slopsitebackend.repository;

import com.slopsite.slopsitebackend.model.Course;
import com.slopsite.slopsitebackend.model.Enrollment;
import com.slopsite.slopsitebackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(Student student);
    List<Enrollment> findByCourse(Course course);
    boolean existsByStudentAndCourse(Student student, Course course);
}
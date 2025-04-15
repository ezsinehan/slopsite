package com.slopsite.slopsitebackend.repository;

import com.slopsite.slopsitebackend.model.Course;
import com.slopsite.slopsitebackend.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacher(Teacher teacher);
    List<Course> findByNameContainingIgnoreCase(String name);
}
package com.fb2devs.slopsitebackend.repository;

import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Teacher;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
  List<Course> findByTeacher(Teacher teacher);
}

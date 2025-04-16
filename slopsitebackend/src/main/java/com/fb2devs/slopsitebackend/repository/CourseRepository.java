package com.fb2devs.slopsitebackend.repository;

import com.fb2devs.slopsitebackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {}

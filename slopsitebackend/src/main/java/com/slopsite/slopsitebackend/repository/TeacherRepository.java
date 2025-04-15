package com.slopsite.slopsitebackend.repository;

import com.slopsite.slopsitebackend.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByUsername(String username);
}
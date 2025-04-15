package com.slopsite.slopsitebackend.repository;

import com.slopsite.slopsitebackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);
}
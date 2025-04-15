package com.fb2devs.slopsitebackend.repository;

import com.fb2devs.slopsitebackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);
}
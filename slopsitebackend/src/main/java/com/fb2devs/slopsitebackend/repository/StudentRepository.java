package com.fb2devs.slopsitebackend.repository;

import com.fb2devs.slopsitebackend.model.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


// @Repository // Manually implementing a custom repo or Just want to make it obvious to humans
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByUsernameAndPassword(String username, String password);

}

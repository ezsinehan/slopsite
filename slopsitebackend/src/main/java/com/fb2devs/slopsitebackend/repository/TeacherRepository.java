package com.fb2devs.slopsitebackend.repository;

import com.fb2devs.slopsitebackend.model.Teacher;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByUsernameAndPassword(String username, String password);

}

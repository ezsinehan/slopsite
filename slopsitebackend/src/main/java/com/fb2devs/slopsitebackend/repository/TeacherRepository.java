package com.fb2devs.slopsitebackend.repository;

import com.fb2devs.slopsitebackend.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByUsername(String username);

    Optional<Teacher> findByUsernameAndPassword(String username, String password);
}
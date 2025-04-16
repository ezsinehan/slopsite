package com.fb2devs.slopsitebackend.repository;

import com.fb2devs.slopsitebackend.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {}

package com.fb2devs.slopsitebackend.dto;

import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.model.Enrollment;

import java.util.List;

public class StudentDTO {
    private Integer id;
    private String username;
    private String name;
    private List<EnrollmentSummaryDTO> enrollments;

    public StudentDTO(Student student, List<Enrollment> freshEnrollments) {
        this.id = student.getId();
        this.username = student.getUsername();
        this.name = student.getName();
        this.enrollments = freshEnrollments.stream()
            .map(e -> new EnrollmentSummaryDTO(e.getId(), e.getGrade()))
            .toList();
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public List<EnrollmentSummaryDTO> getEnrollments() {
        return enrollments;
    }
}

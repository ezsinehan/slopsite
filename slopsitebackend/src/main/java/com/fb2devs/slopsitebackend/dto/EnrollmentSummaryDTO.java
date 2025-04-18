package com.fb2devs.slopsitebackend.dto;

public class EnrollmentSummaryDTO {
    private Integer enrollmentId;
    private Integer grade;

    public EnrollmentSummaryDTO(Integer enrollmentId, Integer grade) {
        this.enrollmentId = enrollmentId;
        this.grade = grade;
    }

    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}

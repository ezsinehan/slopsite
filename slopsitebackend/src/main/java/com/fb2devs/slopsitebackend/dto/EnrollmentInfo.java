package com.fb2devs.slopsitebackend.dto;

public class EnrollmentInfo {
    private Long studentId;
    private String studentName;
    private Integer grade;

    public EnrollmentInfo(Long studentId, String studentName, Integer grade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.grade = grade;
    }

    // Getters and setters
    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}

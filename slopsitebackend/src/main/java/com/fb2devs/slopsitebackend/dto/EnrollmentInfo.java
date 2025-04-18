package com.fb2devs.slopsitebackend.dto;

public class EnrollmentInfo {
    private Integer enrollmentId;
    private Long studentId;
    private String studentName;
    private Integer grade;

    public EnrollmentInfo(Integer enrollmentId, Long studentId, String studentName, Integer grade) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.grade = grade;
    }

    // Getters
    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Integer getGrade() {
        return grade;
    }

    // Setters
    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
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

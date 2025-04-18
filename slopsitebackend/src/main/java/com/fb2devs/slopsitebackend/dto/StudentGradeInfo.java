package com.fb2devs.slopsitebackend.dto;

public class StudentGradeInfo {
    private Integer studentId;
    private String studentName;
    private Integer grade;

    public StudentGradeInfo() {}

    public StudentGradeInfo(Integer studentId, String studentName, Integer grade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.grade = grade;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}

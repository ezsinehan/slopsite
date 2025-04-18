package com.fb2devs.slopsitebackend.dto;

import java.util.List;

public class TeacherCoursesResponse {
    private Integer courseId;
    private String courseName;
    private List<StudentGradeInfo> students;

    public TeacherCoursesResponse() {}

    public TeacherCoursesResponse(Integer courseId, String courseName, List<StudentGradeInfo> students) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.students = students;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<StudentGradeInfo> getStudents() {
        return students;
    }

    public void setStudents(List<StudentGradeInfo> students) {
        this.students = students;
    }
}

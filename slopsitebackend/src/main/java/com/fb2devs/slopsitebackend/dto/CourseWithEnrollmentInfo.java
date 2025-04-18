package com.fb2devs.slopsitebackend.dto;

public class CourseWithEnrollmentInfo {
    private Integer courseId;
    private String courseName;
    private String teacherName;
    private String time;
    private int capacity;
    private int currentEnrollment;

    public CourseWithEnrollmentInfo(
        Integer courseId,
        String courseName,
        String teacherName,
        String time,
        int capacity,
        int currentEnrollment
    ) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.time = time;
        this.capacity = capacity;
        this.currentEnrollment = currentEnrollment;
    }

    // Getters and Setters
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    public void setCurrentEnrollment(int currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }
}

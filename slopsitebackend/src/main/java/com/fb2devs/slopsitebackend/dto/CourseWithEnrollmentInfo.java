package com.fb2devs.slopsitebackend.dto;

public class CourseWithEnrollmentInfo {
    private Integer courseId;
    private String courseName;
    private int capacity;
    private int currentEnrollment;

    public CourseWithEnrollmentInfo(Integer courseId, String courseName, int capacity, int currentEnrollment) {
        this.courseId = courseId;
        this.courseName = courseName;
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


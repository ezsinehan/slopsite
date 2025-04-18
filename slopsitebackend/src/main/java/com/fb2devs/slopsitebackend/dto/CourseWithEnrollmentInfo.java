package com.fb2devs.slopsitebackend.dto;

public class CourseWithEnrollmentInfo {
    private Integer courseId;
    private String courseName;
    private String teacherName; // 👈 add this
    private int capacity;
    private int currentEnrollment;

    public CourseWithEnrollmentInfo(Integer courseId, String courseName, String teacherName, int capacity, int currentEnrollment) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.capacity = capacity;
        this.currentEnrollment = currentEnrollment;
    }

    // Getters and setters...

    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getCurrentEnrollment() { return currentEnrollment; }
    public void setCurrentEnrollment(int currentEnrollment) { this.currentEnrollment = currentEnrollment; }
}

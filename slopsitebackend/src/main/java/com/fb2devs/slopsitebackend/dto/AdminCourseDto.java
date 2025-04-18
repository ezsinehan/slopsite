package com.fb2devs.slopsitebackend.dto;

import com.fb2devs.slopsitebackend.model.Course;

public class AdminCourseDto {
    private Integer id;
    private String name;
    private String time;
    private String teacherName;
    private int totalCapacity;
    private int currentCapacity;

    public AdminCourseDto(Course c) {
        this.id = c.getId();
        this.name = c.getName();
        this.time = c.getTime();
        this.totalCapacity = c.getTotalCapacity();
        this.currentCapacity = (c.getEnrollments() != null) ? c.getEnrollments().size() : 0;
        this.teacherName = (c.getTeacher() != null) ? c.getTeacher().getName() : "TBD";
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }
}

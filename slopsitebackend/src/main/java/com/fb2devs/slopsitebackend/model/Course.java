package com.fb2devs.slopsitebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "courses")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String time;

  @Column(name = "current_capacity", nullable = false)
  private int currentCapacity;

  @Column(name = "total_capacity", nullable = false)
  private int totalCapacity;

  @ManyToOne
  @JoinColumn(name = "teacher_id", nullable = true)
  @JsonBackReference
  private Teacher teacher;

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Enrollment> enrollments = new ArrayList<>();

  public Course() {}

  public Course(String name, String time, int totalCapacity, Teacher teacher) {
    this.name = name;
    this.time = time;
    this.totalCapacity = totalCapacity;
    this.currentCapacity = 0;
    this.teacher = teacher;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getCurrentCapacity() {
    return currentCapacity;
  }

  public void setCurrentCapacity(int currentCapacity) {
    this.currentCapacity = currentCapacity;
  }

  public int getTotalCapacity() {
    return totalCapacity;
  }

  public void setTotalCapacity(int totalCapacity) {
    this.totalCapacity = totalCapacity;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public List<Enrollment> getEnrollments() {
    return enrollments;
  }

  public void setEnrollments(List<Enrollment> enrollments) {
    this.enrollments = enrollments;
  }

  // Optional: convenience method to sync both sides of relationship
  public void addEnrollment(Enrollment enrollment) {
    enrollments.add(enrollment);
    enrollment.setCourse(this);
  }

  public void removeEnrollment(Enrollment enrollment) {
    enrollments.remove(enrollment);
    enrollment.setCourse(null);
  }

  public void printCourse() {
    System.out.println("course name:" + this.name);
    System.out.println("course time:" + this.time);
    System.out.println("course total capacity:" + this.totalCapacity);
    System.out.println("course current capacity:" + this.currentCapacity);
    System.out.println("course teacher:" + this.teacher.getName());
  }
}

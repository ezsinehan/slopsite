package com.fb2devs.slopsitebackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String time;

  @Column(nullable = false)
  private int currentCapacity;

  @Column(nullable = false)
  private int totalCapacity;

  @ManyToOne
  @JoinColumn(name = "teacher_id", nullable = true)
  private Teacher teacher;

  // no-arg constructor required by JPA
  public Course() {
  }

  public Course(String name, String time, int totalCapacity, Teacher teacher) {
    this.name = name;
    this.time = time;
    this.totalCapacity = totalCapacity;
    this.currentCapacity = 0;
    this.teacher = teacher;
  }

  // Getters/setters next (or use Lombok later)
}

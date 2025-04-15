package com.fb2devs.slopsitebackend.model;

import jakarta.persistence.*;

@Entity
@Table(
  name = "enrollments",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = { "student_id", "course_id" })
  }
)
public class Enrollment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "student_id", nullable = false)
  private Student student;

  @ManyToOne(optional = false)
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  public Enrollment() {
  }

  public Enrollment(Student student, Course course) {
    this.student = student;
    this.course = course;
  }

  // Getters/setters next (or use Lombok later)
}

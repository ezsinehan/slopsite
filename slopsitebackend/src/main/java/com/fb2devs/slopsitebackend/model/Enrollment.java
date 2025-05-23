package com.fb2devs.slopsitebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

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
  private Integer id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "student_id", nullable = false)
  @JsonBackReference("student-enrollments")
  private Student student;
  

  @ManyToOne(optional = false)
  @JoinColumn(name = "course_id", nullable = false)
  @JsonBackReference("course-enrollments")
  private Course course;

  public Enrollment() {
  }

  public Enrollment(Student student, Course course) {
    this.student = student;
    this.course = course;
  }

  public Integer getId() {
    return id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

@Column(name = "grade")
private Integer grade;  // or Double if you're using numeric grades

public Integer getGrade() {
    return grade;
}

public void setGrade(Integer grade) {
    this.grade = grade;
}


  public void printEnrollment() {
    System.out.println("enrollment id:" + this.id);
    System.out.println("enrollment student:" +this.student.getName());
    System.out.println("enrollment course:" +this.course.getName());
    System.out.println("enrollment grade:" +this.grade);
  }
}

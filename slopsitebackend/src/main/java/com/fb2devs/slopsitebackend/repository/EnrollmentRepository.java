package com.fb2devs.slopsitebackend.repository;

import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Enrollment;
import com.fb2devs.slopsitebackend.model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
  List<Enrollment> findByStudent(Student student);

  List<Enrollment> findByCourse(Course course);

  @Query("SELECT e.course FROM Enrollment e WHERE e.student = :student")
  List<Course> findCoursesByStudent(@Param("student") Student student);

  @Query("SELECT e.student FROM Enrollment e WHERE e.course = :course")
  List<Student> findStudentsByCourse(@Param("course") Course course);

  int countByCourse(Course course);

}

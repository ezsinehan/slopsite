package com.fb2devs.slopsitebackend;

import com.fb2devs.slopsitebackend.model.*;
import com.fb2devs.slopsitebackend.repository.*;

import jakarta.transaction.Transactional;

// import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersistenceTest {
  @Autowired
  private StudentRepository studentRepo;

  @Autowired
  private TeacherRepository teacherRepo;

  @Autowired
  private CourseRepository courseRepo;

  @Autowired
  private EnrollmentRepository enrollmentRepo;


  @Transactional
@Test
void testPersistence() {
  enrollmentRepo.deleteAll();
  studentRepo.deleteAll();
  courseRepo.deleteAll();
  teacherRepo.deleteAll();

  // 🔸 Teachers
  Teacher t1 = new Teacher("t1", "pass", "Dr. Alpha");
  Teacher t2 = new Teacher("t2", "pass", "Dr. Beta");
  teacherRepo.save(t1);
  teacherRepo.save(t2);

  // 🔸 Courses
  Course c1 = new Course("CS101", "MWF 9AM", 30, null);
  Course c2 = new Course("CS102", "TTh 1PM", 25, null);
  Course c3 = new Course("BIO101", "MWF 10AM", 20, null);
  Course c4 = new Course("CHEM101", "TTh 3PM", 15, null);
  
  t1.addCourse(c1);
  t1.addCourse(c2);
  t1.printCourses();
  t2.addCourse(c3);
  t2.addCourse(c4);
  t2.printCourses();
  
  courseRepo.save(c1);
  courseRepo.save(c2);
  courseRepo.save(c3);
  courseRepo.save(c4);

  // 🔸 Studentså
  Student s1 = new Student("stud1", "pass", "EZ");
  Student s2 = new Student("stud2", "pass", "Bob");
  Student s3 = new Student("stud3", "pass", "Alice");
  Student s4 = new Student("stud4", "pass", "Diana");
  studentRepo.save(s1);
  studentRepo.save(s2);
  studentRepo.save(s3);
  studentRepo.save(s4);

  Enrollment e1 = new Enrollment(s1, c1);
  s1.addEnrollment(e1);
  enrollmentRepo.save(e1);

  Enrollment e2 = new Enrollment(s2, c1);
  s2.addEnrollment(e2);
  enrollmentRepo.save(e2);

  Enrollment e3 = new Enrollment(s3, c2);
  s3.addEnrollment(e3);
  enrollmentRepo.save(e3);

  Enrollment e4 = new Enrollment(s4, c3);
  s4.addEnrollment(e4);
  enrollmentRepo.save(e4);
  
  // 🔎 Queries & Output
  System.out.println("\n--- All Students ---");
  studentRepo.findAll().forEach(s -> {
    System.out.println("Student: " + s.getName());
    System.out.println("Student(id): " + s.getId());
    System.out.println("Student(username): " + s.getUsername());
    System.out.println("Student(password): " + s.getPassword());
    s.printEnrollments();
  });


  System.out.println("\n--- All Courses ---");
  courseRepo.findAll().forEach(c -> {
    System.out.println("Course: " + c.getName() + ", Taught by: " + c.getTeacher().getName());
  });

  System.out.println("\n--- All Teachers ---");
  teacherRepo.findAll().forEach(t -> {
    System.out.println("Teacher: " + t.getName());
    System.out.println("Courses: " + t.getCourses().stream()
        .map(Course::getName).toList());
  });
}

}

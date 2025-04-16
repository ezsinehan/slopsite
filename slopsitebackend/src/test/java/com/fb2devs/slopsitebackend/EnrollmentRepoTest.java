package com.fb2devs.slopsitebackend;

import org.assertj.core.api.Assertions;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Enrollment;
import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.model.Teacher;
import com.fb2devs.slopsitebackend.repository.CourseRepository;
import com.fb2devs.slopsitebackend.repository.EnrollmentRepository;
import com.fb2devs.slopsitebackend.repository.StudentRepository;
import com.fb2devs.slopsitebackend.repository.TeacherRepository;





@SpringBootTest
public class EnrollmentRepoTest {

  @Autowired
  private StudentRepository studentRepo;

  @Autowired
  private CourseRepository courseRepo;

  @Autowired
  private EnrollmentRepository enrollmentRepo;

  @Autowired
  private TeacherRepository teacherRepo;

  @Test
  public void testFindCoursesByStudent() {
    // Setup
    Student student = new Student("testuser", "pass", "Test Student");
    studentRepo.save(student);

    Course course1 = new Course("Math 101", "MWF 10AM", 30, null);
    Course course2 = new Course("CS 201", "TTh 1PM", 25, null);
    courseRepo.save(course1);
    courseRepo.save(course2);

    Enrollment e1 = new Enrollment(student, course1);
    Enrollment e2 = new Enrollment(student, course2);
    enrollmentRepo.save(e1);
    enrollmentRepo.save(e2);

    // Test
    List<Course> studentCourses = enrollmentRepo.findCoursesByStudent(student);
    Assertions.assertThat(studentCourses.size()).isEqualTo(2);
    for (Course c : studentCourses) {
      System.out.println("Student is enrolled in: " + c.getName());
    }
  }

  @Test
  public void testFindStudentsByCourse() {
    Student s1 = new Student("alpha", "123", "Alpha A");
    Student s2 = new Student("beta", "123", "Beta B");
    studentRepo.save(s1);
    studentRepo.save(s2);

    Course course = new Course("Physics 101", "MWF 2PM", 20, null);
    courseRepo.save(course);

    enrollmentRepo.save(new Enrollment(s1, course));
    enrollmentRepo.save(new Enrollment(s2, course));

    List<Student> students = enrollmentRepo.findStudentsByCourse(course);
    Assertions.assertThat(students.size()).isEqualTo(2);
    for (Student s : students) {
      System.out.println("Enrolled student: " + s.getName());
    }
  }

  @Test
public void testFindCoursesByTeacher() {
  Teacher teacher = new Teacher("prof123", "secret", "Dr. Smith");
  teacherRepo.save(teacher);

  Course c1 = new Course("Bio 101", "MWF 10AM", 40, teacher);
  Course c2 = new Course("Chem 101", "TTh 2PM", 35, teacher);
  courseRepo.save(c1);
  courseRepo.save(c2);

  List<Course> courses = courseRepo.findByTeacher(teacher);
  Assertions.assertThat(courses.size()).isEqualTo(2);
  for (Course c : courses) {
    System.out.println("Taught by teacher: " + c.getName());
  }
}

}


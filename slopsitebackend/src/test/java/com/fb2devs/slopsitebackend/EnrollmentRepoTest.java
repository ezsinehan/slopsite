package com.fb2devs.slopsitebackend;

import org.assertj.core.api.Assertions;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fb2devs.slopsitebackend.model.Admin;
import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Enrollment;
import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.model.Teacher;
import com.fb2devs.slopsitebackend.repository.AdminRepository;
import com.fb2devs.slopsitebackend.repository.CourseRepository;
import com.fb2devs.slopsitebackend.repository.EnrollmentRepository;
import com.fb2devs.slopsitebackend.repository.StudentRepository;
import com.fb2devs.slopsitebackend.repository.TeacherRepository;

@SpringBootTest
public class EnrollmentRepoTest {

  @Autowired private StudentRepository studentRepo;
  @Autowired private CourseRepository courseRepo;
  @Autowired private EnrollmentRepository enrollmentRepo;
  @Autowired private TeacherRepository teacherRepo;
  @Autowired private AdminRepository adminRepo;

  @Test
  public void testEnrollmentWithGrades() {
    // 🛠 Create a test admin
    Admin admin = new Admin("admin", "adminpass", "Super Admin");
    adminRepo.save(admin);
    System.out.println("✅ Admin created: username=admin, password=adminpass");

    // 👨‍🏫 Create teachers
    Teacher t1 = new Teacher("teacher1", "pass", "Dr. A");
    Teacher t2 = new Teacher("teacher2", "pass", "Dr. B");
    teacherRepo.save(t1);
    teacherRepo.save(t2);

    // 👩‍🎓 Create students
    Student s1 = new Student("s1", "123", "Alice");
    Student s2 = new Student("s2", "123", "Bob");
    Student s3 = new Student("s3", "123", "Charlie");
    studentRepo.save(s1);
    studentRepo.save(s2);
    studentRepo.save(s3);

    // 📘 Create courses
    Course c1 = new Course("Math", "MWF 9AM", 30, t1);
    Course c2 = new Course("History", "TTh 2PM", 25, t1);
    Course c3 = new Course("Physics", "MWF 1PM", 40, t2);
    courseRepo.save(c1);
    courseRepo.save(c2);
    courseRepo.save(c3);

    // 📝 Create enrollments with grades
    Enrollment e1 = new Enrollment(s1, c1); e1.setGrade(95);
    Enrollment e2 = new Enrollment(s1, c2); e2.setGrade(88);
    Enrollment e3 = new Enrollment(s2, c1); e3.setGrade(90);
    Enrollment e4 = new Enrollment(s3, c3); e4.setGrade(82);
    Enrollment e5 = new Enrollment(s2, c3); e5.setGrade(75);

    enrollmentRepo.save(e1);
    enrollmentRepo.save(e2);
    enrollmentRepo.save(e3);
    enrollmentRepo.save(e4);
    enrollmentRepo.save(e5);

    // ✅ Verify: Find all courses by a student
    List<Course> coursesForS1 = enrollmentRepo.findCoursesByStudent(s1);
    Assertions.assertThat(coursesForS1.size()).isEqualTo(2);
    System.out.println("Courses for " + s1.getName() + ":");
    for (Course c : coursesForS1) {
      System.out.println("- " + c.getName());
    }

    // ✅ Verify: Find all students in a course
    List<Student> studentsInMath = enrollmentRepo.findStudentsByCourse(c1);
    Assertions.assertThat(studentsInMath.size()).isEqualTo(2);
    System.out.println("Students in Math:");
    for (Student s : studentsInMath) {
      System.out.println("- " + s.getName());
    }

    // ✅ Verify: Find all courses by teacher
    List<Course> coursesByT1 = courseRepo.findByTeacher(t1);
    Assertions.assertThat(coursesByT1.size()).isEqualTo(2);
    System.out.println("Courses taught by " + t1.getName() + ":");
    for (Course c : coursesByT1) {
      System.out.println("- " + c.getName());
    }

    // ✅ Verify: Grades
    List<Enrollment> enrollments = enrollmentRepo.findAll();
    Assertions.assertThat(enrollments.size()).isEqualTo(5);
    System.out.println("All enrollments with grades:");
    for (Enrollment e : enrollments) {
      System.out.println("- " + e.getStudent().getName() + " in " +
                         e.getCourse().getName() + ": " + e.getGrade());
    }
  }
}

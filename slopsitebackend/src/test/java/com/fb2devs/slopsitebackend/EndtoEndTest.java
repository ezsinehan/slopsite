package com.fb2devs.slopsitebackend;

import com.fb2devs.slopsitebackend.model.*;
import com.fb2devs.slopsitebackend.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EndtoEndTest {

    @Autowired private StudentRepository studentRepo;
    @Autowired private TeacherRepository teacherRepo;
    @Autowired private CourseRepository courseRepo;
    @Autowired private EnrollmentRepository enrollmentRepo;

    @Test
    @Transactional
    public void updateGradeAndVerify() {
        // ✅ Setup
        Teacher teacher = new Teacher("teachX", "pass", "Dr. T");
        teacherRepo.save(teacher);

        Course course = new Course("CS999", "WF 2PM", 50, teacher);
        courseRepo.save(course);

        Student student = new Student("studX", "pass", "Test Student");
        studentRepo.save(student);

        Enrollment enrollment = new Enrollment(student, course);
        enrollment.setGrade(70);
        enrollmentRepo.save(enrollment);

        Integer id = enrollment.getId();
        System.out.println("✅ Created test data with enrollment ID: " + id);

        // ✅ Update grade
        Enrollment toUpdate = enrollmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        toUpdate.setGrade(100);
        enrollmentRepo.save(toUpdate);

        Enrollment updated = enrollmentRepo.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        assertThat(updated.getGrade()).isEqualTo(100);
        System.out.println("✅ Grade updated correctly for ID: " + id);

        // ✅ Delete enrollment
        enrollmentRepo.deleteById(id);
        boolean exists = enrollmentRepo.existsById(id);
        assertThat(exists).isFalse();
        System.out.println("✅ Enrollment deleted successfully");
    }
}

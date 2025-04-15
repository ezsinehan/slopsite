package com.fb2devs.slopsitebackend.config;

import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Enrollment;
import com.fb2devs.slopsitebackend.model.Student;
import com.fb2devs.slopsitebackend.model.Teacher;
import com.fb2devs.slopsitebackend.service.CourseService;
import com.fb2devs.slopsitebackend.service.EnrollmentService;
import com.fb2devs.slopsitebackend.service.StudentService;
import com.fb2devs.slopsitebackend.service.TeacherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            StudentService studentService,
            TeacherService teacherService,
            CourseService courseService,
            EnrollmentService enrollmentService) {
        
        return args -> {
            // Create students
            Student student1 = new Student();
            student1.setUsername("john.doe");
            student1.setPassword("password123");
            student1.setName("John Doe");
            studentService.saveStudent(student1);
            
            Student student2 = new Student();
            student2.setUsername("jane.smith");
            student2.setPassword("password456");
            student2.setName("Jane Smith");
            studentService.saveStudent(student2);
            
            // Create teachers
            Teacher teacher1 = new Teacher();
            teacher1.setUsername("prof.anderson");
            teacher1.setPassword("teachpass123");
            teacher1.setName("Professor Anderson");
            teacherService.saveTeacher(teacher1);
            
            Teacher teacher2 = new Teacher();
            teacher2.setUsername("dr.johnson");
            teacher2.setPassword("teachpass456");
            teacher2.setName("Dr. Johnson");
            teacherService.saveTeacher(teacher2);
            
            // Create courses
            Course course1 = new Course();
            course1.setName("Introduction to Computer Science");
            course1.setTime("MWF 10:00-11:30");
            course1.setCurrentCapacity(0);
            course1.setTotalCapacity(30);
            course1.setTeacher(teacher1);
            courseService.saveCourse(course1);
            
            Course course2 = new Course();
            course2.setName("Advanced Mathematics");
            course2.setTime("TTh 13:00-14:30");
            course2.setCurrentCapacity(0);
            course2.setTotalCapacity(25);
            course2.setTeacher(teacher1);
            courseService.saveCourse(course2);
            
            Course course3 = new Course();
            course3.setName("Physics 101");
            course3.setTime("MWF 14:00-15:30");
            course3.setCurrentCapacity(0);
            course3.setTotalCapacity(40);
            course3.setTeacher(teacher2);
            courseService.saveCourse(course3);
            
            // Create enrollments
            Enrollment enrollment1 = new Enrollment();
            enrollment1.setStudent(student1);
            enrollment1.setCourse(course1);
            enrollmentService.saveEnrollment(enrollment1);
            
            Enrollment enrollment2 = new Enrollment();
            enrollment2.setStudent(student1);
            enrollment2.setCourse(course3);
            enrollmentService.saveEnrollment(enrollment2);
            
            Enrollment enrollment3 = new Enrollment();
            enrollment3.setStudent(student2);
            enrollment3.setCourse(course2);
            enrollmentService.saveEnrollment(enrollment3);
            
            // Update course capacities
            course1.setCurrentCapacity(1);
            courseService.saveCourse(course1);
            
            course2.setCurrentCapacity(1);
            courseService.saveCourse(course2);
            
            course3.setCurrentCapacity(1);
            courseService.saveCourse(course3);
            
            System.out.println("Sample data initialized successfully!");
        };
    }
}
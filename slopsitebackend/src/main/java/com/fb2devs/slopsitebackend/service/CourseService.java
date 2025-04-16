package com.fb2devs.slopsitebackend.service;

import com.fb2devs.slopsitebackend.model.Course;
import com.fb2devs.slopsitebackend.model.Teacher;
import com.fb2devs.slopsitebackend.repository.CourseRepository;
import com.fb2devs.slopsitebackend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepo;
    private final TeacherRepository teacherRepo;

    @Autowired
    public CourseService(CourseRepository courseRepo, TeacherRepository teacherRepo) {
        this.courseRepo = courseRepo;
        this.teacherRepo = teacherRepo;
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course getCourseById(Integer id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course with ID " + id + " not found"));
    }

    public Course saveCourse(Course course) {
        if (course.getName() == null || course.getName().isBlank()) {
            throw new IllegalArgumentException("Course name must not be empty");
        }
        return courseRepo.save(course);
    }

    public void deleteCourse(Integer id) {
        if (!courseRepo.existsById(id)) {
            throw new IllegalStateException("Cannot delete: course with ID " + id + " does not exist");
        }
        courseRepo.deleteById(id);
    }

    public List<Course> getCoursesByTeacher(Teacher teacher) {
        if (teacher == null || teacher.getId() == null) {
            throw new IllegalArgumentException("Teacher or teacher ID must not be null");
        }
        if (!teacherRepo.existsById(teacher.getId())) {
            throw new IllegalStateException("Teacher with ID " + teacher.getId() + " does not exist");
        }
        return courseRepo.findByTeacher(teacher);
    }
}


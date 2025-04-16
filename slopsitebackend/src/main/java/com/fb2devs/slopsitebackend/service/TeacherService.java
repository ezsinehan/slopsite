package com.fb2devs.slopsitebackend.service;

import com.fb2devs.slopsitebackend.model.Teacher;
import com.fb2devs.slopsitebackend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepo;

    @Autowired
    public TeacherService(TeacherRepository teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    public Teacher getTeacherById(Integer id) {
        return teacherRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Teacher with ID " + id + " not found"));
    }

    public Teacher saveTeacher(Teacher teacher) {
        if (teacher.getUsername() == null || teacher.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username must not be empty");
        }
        return teacherRepo.save(teacher);
    }

    public void deleteTeacher(Integer id) {
        if (!teacherRepo.existsById(id)) {
            throw new IllegalStateException("Cannot delete: teacher with ID " + id + " does not exist");
        }
        teacherRepo.deleteById(id);
    }
}

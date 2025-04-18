package com.fb2devs.slopsitebackend.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fb2devs.slopsitebackend.model.Student;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("/echo-student")
    public Student echoStudent(@RequestBody Student student) {
        System.out.println("✅ Received: " + student.getUsername());
        return student;
    }
}


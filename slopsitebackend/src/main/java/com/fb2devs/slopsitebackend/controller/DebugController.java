package com.fb2devs.slopsitebackend.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping; 


@RestController
@RequestMapping("/debug")
public class DebugController {

    @PostMapping("/raw")
    public String echoRaw(@RequestBody String body) {
        System.out.println("📦 Received raw body: " + body);
        return body;
    }
}

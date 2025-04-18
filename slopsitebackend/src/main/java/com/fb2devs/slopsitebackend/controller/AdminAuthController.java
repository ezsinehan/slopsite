package com.fb2devs.slopsitebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fb2devs.slopsitebackend.dto.AdminLoginRequest;
import com.fb2devs.slopsitebackend.dto.AdminLoginResponse;
import com.fb2devs.slopsitebackend.repository.AdminRepository;

@RestController
@RequestMapping("/auth/admin")
public class AdminAuthController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponse> login(@RequestBody AdminLoginRequest request) {
        return adminRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword())
            .map(admin -> ResponseEntity.ok(new AdminLoginResponse(admin.getId(), admin.getName())))
            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}

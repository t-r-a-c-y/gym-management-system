package com.gms.gym.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    public UserController() {
        System.out.println("UserController initialized for /api/user");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        System.out.println("UserController: Profile endpoint hit for path: /api/user/profile");
        // Optional: Extract user details from request attributes set by JwtFilter
        String email = (String) request.getAttribute("email");
        String role = (String) request.getAttribute("role");
        Long branchId = (Long) request.getAttribute("branchId");
        System.out.println("UserController: Authenticated user - email: " + email + ", role: " + role + ", branchId: " + branchId);
        return ResponseEntity.ok("Profile data for authenticated user");
    }
}
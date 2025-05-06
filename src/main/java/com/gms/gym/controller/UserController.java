package com.gms.gym.controller;

import com.gms.gym.entity.User;
import com.gms.gym.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController() {
        System.out.println("UserController initialized for /a/api/user");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        System.out.println("UserController: Profile endpoint hit for path: /a/api/user/profile");
        String email = (String) request.getAttribute("email");
        String role = (String) request.getAttribute("role");
        Object branchIdObj = request.getAttribute("branchId");
        Long branchId = null;
        if (branchIdObj instanceof Integer) {
            branchId = ((Integer) branchIdObj).longValue();
        } else if (branchIdObj instanceof Long) {
            branchId = (Long) branchIdObj;
        } else {
            System.out.println("UserController: Invalid branchId type: " + branchIdObj);
        }
        System.out.println("UserController: Authenticated user - email: " + email + ", role: " + role + ", branchId: " + branchId);
        return ResponseEntity.ok("Profile data for authenticated user");
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.findAll(); // Assuming UserService returns a list
    }
}
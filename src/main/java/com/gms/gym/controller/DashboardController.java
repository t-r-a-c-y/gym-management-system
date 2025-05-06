package com.gms.gym.controller;

import com.gms.gym.repository.BranchRepository;
import com.gms.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public Object getDashboard() {
        System.out.println("DashboardController: Handling GET /a/api/dashboard");
        return new Object() {
            public int branches = (int) branchRepository.count();
            public int users = userService.getUserCount();
            public int workouts = 0; // Replace with workout service if available
        };
    }
}
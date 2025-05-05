package com.gms.gym.controller;

import com.gms.gym.dto.RegistrationRequest;
import com.gms.gym.dto.UserDTO;
import com.gms.gym.dto.UserLoginDTO;
import com.gms.gym.entity.Branch;
import com.gms.gym.repository.BranchRepository;
import com.gms.gym.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final BranchRepository branchRepository;

    public AuthController(UserService userService, BranchRepository branchRepository) {
        this.userService = userService;
        this.branchRepository = branchRepository;
        System.out.println("AuthController initialized for /api/auth");
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("AuthController: Test endpoint hit");
        return "Test endpoint working";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        try {
            System.out.println("AuthController: Received registration request: " + request);

            if (request == null || request.getBranch() == null || request.getUser() == null) {
                System.out.println("AuthController: Invalid request - missing branch or user");
                return ResponseEntity.badRequest().body("Invalid request: branch and user are required");
            }

            RegistrationRequest.BranchDTO branchDTO = request.getBranch();
            if (branchDTO.getName() == null || branchDTO.getEmail() == null || branchDTO.getLocation() == null) {
                System.out.println("AuthController: Invalid branch data");
                return ResponseEntity.badRequest().body("Invalid branch data: name, email, and location are required");
            }

            UserDTO userDTO = request.getUser();
            if (userDTO.getEmail() == null || userDTO.getPassword() == null || userDTO.getRole() == null) {
                System.out.println("AuthController: Invalid user data");
                return ResponseEntity.badRequest().body("Invalid user data: email, password, and role are required");
            }

            // Check if branch already exists by email
            Branch branch;
            var existingBranch = branchRepository.findByEmail(branchDTO.getEmail());
            if (existingBranch.isPresent()) {
                System.out.println("AuthController: Using existing branch with email: " + branchDTO.getEmail());
                branch = existingBranch.get();
            } else {
                branch = new Branch();
                branch.setName(branchDTO.getName());
                branch.setEmail(branchDTO.getEmail());
                branch.setLocation(branchDTO.getLocation());
                System.out.println("AuthController: Saving new branch: " + branch);
                branch = branchRepository.save(branch);
                System.out.println("AuthController: Saved branch with ID: " + branch.getId());
            }

            userDTO.setBranchId(branch.getId());

            System.out.println("AuthController: Registering user: " + userDTO);
            userService.register(userDTO);
            System.out.println("AuthController: User registered successfully");

            return ResponseEntity.ok("Branch and admin registered successfully");
        } catch (Exception e) {
            System.out.println("AuthController: Error during registration: " + e.getMessage());
            e.printStackTrace();
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                return ResponseEntity.badRequest().body("Registration failed: A branch with email " + request.getBranch().getEmail() + " already exists");
            }
            return ResponseEntity.status(500).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO loginDTO) {
        try {
            System.out.println("AuthController: Received login request: " + loginDTO);
            if (loginDTO.getEmail() == null || loginDTO.getPassword() == null) {
                System.out.println("AuthController: Invalid login data");
                return ResponseEntity.badRequest().body("Invalid login data: email and password are required");
            }

            // Map UserLoginDTO to UserDTO
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(loginDTO.getEmail());
            userDTO.setPassword(loginDTO.getPassword());

            String token = userService.login(userDTO);
            System.out.println("AuthController: Login successful, token generated");
            return ResponseEntity.ok("{\"token\": \"" + token + "\"}");
        } catch (Exception e) {
            System.out.println("AuthController: Error during login: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(401).body("Login failed: " + e.getMessage());
        }
    }
}
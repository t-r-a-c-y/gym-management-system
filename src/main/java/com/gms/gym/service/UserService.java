package com.gms.gym.service;

import com.gms.gym.dto.UserDTO;
import com.gms.gym.entity.Branch;
import com.gms.gym.entity.User;
import com.gms.gym.repository.BranchRepository;
import com.gms.gym.repository.UserRepository;
import com.gms.gym.util.JwtUtil;
import com.gms.gym.util.PasswordEncoderUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, BranchRepository branchRepository,
                       PasswordEncoderUtil passwordEncoderUtil, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.passwordEncoderUtil = passwordEncoderUtil;
        this.jwtUtil = jwtUtil;
    }

    public void register(UserDTO userDTO) {
        System.out.println("UserService: Registering user with DTO: " + userDTO);
        if (userDTO.getRole() == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoderUtil.encode(userDTO.getPassword()));
        user.setRole(User.Role.valueOf(userDTO.getRole().toUpperCase())); // Ensure case-insensitive
        Branch branch = branchRepository.findById(userDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        user.setBranch(branch);
        userRepository.save(user);
    }

    public String login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoderUtil.matches(userDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtUtil.generateToken(user.getEmail(), user.getRole().name(), user.getBranch().getId());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public int getUserCount() {
        return (int) userRepository.count();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
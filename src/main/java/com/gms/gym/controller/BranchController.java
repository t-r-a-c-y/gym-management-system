package com.gms.gym.controller;

import com.gms.gym.dto.BranchDTO;
import com.gms.gym.service.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/branch")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<?> createBranch(@RequestBody BranchDTO branchDTO) {
        return ResponseEntity.ok(branchService.createBranch(branchDTO));
    }
}

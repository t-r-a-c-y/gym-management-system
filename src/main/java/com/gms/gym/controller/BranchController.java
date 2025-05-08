package com.gms.gym.controller;

import com.gms.gym.entity.Branch;
import com.gms.gym.repository.BranchRepository;
import com.gms.gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new branch
    @PostMapping
    public Branch addBranch(@RequestBody Branch branch) {
        return branchRepository.save(branch);
    }

    // Read all branches
    @GetMapping
    public List<Branch> getBranches() {
        return branchRepository.findAll();
    }

    // Read a single branch by ID
    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        Optional<Branch> branch = branchRepository.findById(id);
        return branch.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing branch
    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable Long id, @RequestBody Branch updatedBranch) {
        Optional<Branch> existingBranch = branchRepository.findById(id);
        if (existingBranch.isPresent()) {
            Branch branch = existingBranch.get();
            branch.setName(updatedBranch.getName());
            branch.setEmail(updatedBranch.getEmail());
            branch.setLocation(updatedBranch.getLocation());
            return ResponseEntity.ok(branchRepository.save(branch));
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a branch
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        if (!branchRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Check if any users are associated with this branch
        long userCount = userRepository.countByBranchId(id);
        if (userCount > 0) {
            return ResponseEntity.status(409)
                    .header("X-Error-Message", "Cannot delete branch with associated users. Reassign or delete users first.")
                    .build();
        }

        branchRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
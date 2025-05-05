package com.gms.gym.service;

import com.gms.gym.dto.BranchDTO;
import com.gms.gym.entity.Branch;
import com.gms.gym.repository.BranchRepository;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public BranchDTO createBranch(BranchDTO branchDTO) {
        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setEmail(branchDTO.getEmail());
        branch.setLocation(branchDTO.getLocation());
        branch = branchRepository.save(branch);
        branchDTO.setId(branch.getId());
        return branchDTO;
    }
}
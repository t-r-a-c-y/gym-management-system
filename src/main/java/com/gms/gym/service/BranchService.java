package com.gms.gym.service;

import com.gms.gym.dto.BranchDTO;
import com.gms.gym.entity.Branch;
import com.gms.gym.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<Branch> findAll() {
        return branchRepository.findAll();
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
    public BranchDTO updateBranch(BranchDTO branchDTO) {
        Branch branch = branchRepository.findById(branchDTO.getId()).get();
        branch.setName(branchDTO.getName());
        branch.setEmail(branchDTO.getEmail());
        branch.setLocation(branchDTO.getLocation());
        branch = branchRepository.save(branch);
        branchDTO.setId(branch.getId());
        return branchDTO;
    }
    public void deleteBranch(BranchDTO branchDTO) {
        Branch branch = branchRepository.findById(branchDTO.getId()).get();
        branchRepository.delete(branch);
    }
}
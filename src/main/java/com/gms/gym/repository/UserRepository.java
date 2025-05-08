package com.gms.gym.repository;

import com.gms.gym.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.branch.id = :branchId")
    long countByBranchId(@Param("branchId") Long branchId);

    @Query("UPDATE User u SET u.branch = NULL WHERE u.branch.id = :branchId")
    void setBranchToNull(@Param("branchId") Long branchId);
}
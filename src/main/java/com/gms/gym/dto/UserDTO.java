package com.gms.gym.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String role;
    private Long branchId;

    @Override
    public String toString() {
        return "UserDTO(id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", branchId=" + branchId + ")";
    }
}
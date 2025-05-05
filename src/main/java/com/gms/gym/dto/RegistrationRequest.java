package com.gms.gym.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private BranchDTO branch;
    private UserDTO user;

    @Getter
    @Setter
    public static class BranchDTO {
        private String name;
        private String email;
        private String location;
    }
}
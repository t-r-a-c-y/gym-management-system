package com.gms.gym.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
    private String email;
    private String password;

    @Override
    public String toString() {
        return "UserLoginDTO(email=" + email + ", password=[REDACTED])";
    }
}
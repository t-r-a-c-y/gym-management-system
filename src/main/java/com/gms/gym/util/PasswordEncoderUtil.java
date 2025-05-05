package com.gms.gym.util;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordEncoderUtil {

    public String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
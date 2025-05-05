package com.gms.gym.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        // Generate a secure key if no secret is provided or if it's insecure
        this.secretKey = (secret != null && secret.length() >= 32) ? Keys.hmacShaKeyFor(secret.getBytes()) : Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    }

    public String generateToken(String email, String role, Long branchId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("branchId", branchId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(secretKey)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("JwtUtil: Token validation failed: " + e.getMessage());
            return false;
        }
    }
}
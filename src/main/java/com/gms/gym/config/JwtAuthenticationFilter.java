package com.gms.gym.config;

import com.gms.gym.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        System.out.println("JwtFilter: Processing path: " + path);

        // Skip JWT validation for auth, Swagger, and OPTIONS requests
        if (path.startsWith("/a/api/auth/") ||
                "OPTIONS".equals(request.getMethod())) {
            System.out.println("JwtFilter: Skipping JWT validation for: " + path);
            filterChain.doFilter(request, response);
            return;
        }

        // Validate JWT for other endpoints
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("JwtFilter: Missing or invalid Authorization header");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            System.out.println("JwtFilter: Invalid JWT token");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
            return;
        }

        Claims claims = jwtUtil.getClaims(token);
        request.setAttribute("email", claims.getSubject());
        request.setAttribute("role", claims.get("role"));
        request.setAttribute("branchId", claims.get("branchId"));
        System.out.println("JwtFilter: Token validated, proceeding with request");

        filterChain.doFilter(request, response);
    }
}
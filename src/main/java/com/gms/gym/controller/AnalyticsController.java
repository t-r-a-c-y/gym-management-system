package com.gms.gym.controller;

import com.gms.gym.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<?> getAnalytics(@PathVariable Long branchId) {
        return ResponseEntity.ok(analyticsService.getAnalytics(branchId));
    }
}

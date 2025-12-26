package com.example.demo.controller;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@Tag(name = "Performance Scores", description = "Vendor performance score endpoints")
@SecurityRequirement(name = "Bearer Authentication")
public class VendorPerformanceScoreController {
    
    private final VendorPerformanceScoreService vendorPerformanceScoreService;
    
    public VendorPerformanceScoreController(VendorPerformanceScoreService vendorPerformanceScoreService) {
        this.vendorPerformanceScoreService = vendorPerformanceScoreService;
    }
    
    @PostMapping("/calculate/{vendorId}")
    @Operation(summary = "Calculate score for vendor")
    public ResponseEntity<VendorPerformanceScore> calculateScore(@PathVariable Long vendorId) {
        return ResponseEntity.ok(vendorPerformanceScoreService.calculateScore(vendorId));
    }
    
    @GetMapping("/latest/{vendorId}")
    @Operation(summary = "Get latest score for vendor")
    public ResponseEntity<VendorPerformanceScore> getLatestScore(@PathVariable Long vendorId) {
        VendorPerformanceScore score = vendorPerformanceScoreService.getLatestScore(vendorId);
        if (score == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(score);
    }
    
    @GetMapping("/vendor/{vendorId}")
    @Operation(summary = "Get score history for vendor")
    public ResponseEntity<List<VendorPerformanceScore>> getScoresForVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(vendorPerformanceScoreService.getScoresForVendor(vendorId));
    }
}
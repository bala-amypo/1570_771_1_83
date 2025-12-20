package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;

@RestController
@RequestMapping("/api/scores")
public class VendorPerformanceScoreController {

    private final VendorPerformanceScoreService scoreService;

    public VendorPerformanceScoreController(
            VendorPerformanceScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/calculate/{vendorId}")
    public ResponseEntity<VendorPerformanceScore> calculateScore(
            @PathVariable Long vendorId) {

        return ResponseEntity.status(201)
                .body(scoreService.calculateScore(vendorId));
    }

    @GetMapping("/latest/{vendorId}")
    public ResponseEntity<VendorPerformanceScore> getLatestScore(
            @PathVariable Long vendorId) {

        return ResponseEntity.status(200)
                .body(scoreService.getLatestScore(vendorId));
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VendorPerformanceScore>> getScoresForVendor(
            @PathVariable Long vendorId) {

        return ResponseEntity.status(200)
                .body(scoreService.getScoresForVendor(vendorId));
    }
}

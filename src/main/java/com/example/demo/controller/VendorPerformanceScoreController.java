package com.example.demo.controller;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class VendorPerformanceScoreController {

    private final VendorPerformanceScoreService scoreService;

    public VendorPerformanceScoreController(
            VendorPerformanceScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/calculate/{vendorId}")
    public ResponseEntity<VendorPerformanceScore>
    calculate(@PathVariable Long vendorId) {
        return ResponseEntity.ok(
                scoreService.calculateScore(vendorId)
        );
    }

    @GetMapping("/latest/{vendorId}")
    public ResponseEntity<VendorPerformanceScore>
    latest(@PathVariable Long vendorId) {
        return ResponseEntity.ok(
                scoreService.getLatestScore(vendorId)
        );
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VendorPerformanceScore>>
    history(@PathVariable Long vendorId) {
        return ResponseEntity.ok(
                scoreService.getScoresForVendor(vendorId)
        );
    }
}

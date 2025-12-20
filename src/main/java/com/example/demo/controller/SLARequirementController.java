package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.SLARequirement;
import com.example.demo.service.SLARequirementService;

@RestController
@RequestMapping("/api/sla-requirements")
public class SLARequirementController {
    private final SLARequirementService slaRequirementService;

    public SLARequirementController(SLARequirementService slaRequirementService) {
        this.slaRequirementService = slaRequirementService;
    }

    @PostMapping
    public ResponseEntity<SLARequirement> createRequirement(@RequestBody SLARequirement req) {
        SLARequirement created = slaRequirementService.createRequirement(req);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SLARequirement> updateRequirement(
            @PathVariable Long id,
            @RequestBody SLARequirement req) {
        SLARequirement updated = slaRequirementService.updateRequirement(id, req);
        return ResponseEntity.status(200).body(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SLARequirement> getRequirementById(@PathVariable Long id) {
        SLARequirement req = slaRequirementService.getRequirementById(id);
        return ResponseEntity.status(200).body(req);
    }

    @GetMapping
    public ResponseEntity<List<SLARequirement>> getAllRequirements() {
        return ResponseEntity.status(200)
                .body(slaRequirementService.getAllRequirements());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateRequirement(@PathVariable Long id) {
        slaRequirementService.deactivateRequirement(id);
        return ResponseEntity.status(200)
                .body("SLA Requirement deactivated successfully");
    }
}
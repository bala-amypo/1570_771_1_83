package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.VendorTier;
import com.example.demo.service.VendorTierService;

@RestController
@RequestMapping("/api/tiers")
public class VendorTierController {

    private final VendorTierService vendorTierService;

    public VendorTierController(VendorTierService vendorTierService) {
        this.vendorTierService = vendorTierService;
    }

    @PostMapping
    public ResponseEntity<VendorTier> createTier(@RequestBody VendorTier tier) {
        VendorTier created = vendorTierService.createTier(tier);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorTier> updateTier(
            @PathVariable Long id,
            @RequestBody VendorTier tier) {

        VendorTier updated = vendorTierService.updateTier(id, tier);
        return ResponseEntity.status(200).body(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorTier> getTierById(@PathVariable Long id) {
        return ResponseEntity.status(200)
                .body(vendorTierService.getTierById(id));
    }

    @GetMapping
    public ResponseEntity<List<VendorTier>> getAllTiers() {
        return ResponseEntity.status(200)
                .body(vendorTierService.getAllTiers());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateTier(@PathVariable Long id) {
        vendorTierService.deactivateTier(id);
        return ResponseEntity.status(200)
                .body("Vendor tier deactivated successfully");
    }
}

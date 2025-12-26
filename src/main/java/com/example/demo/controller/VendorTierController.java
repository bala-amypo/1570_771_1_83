package com.example.demo.controller;

import com.example.demo.model.VendorTier;
import com.example.demo.service.VendorTierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiers")
public class VendorTierController {

    private final VendorTierService vendorTierService;

    public VendorTierController(VendorTierService vendorTierService) {
        this.vendorTierService = vendorTierService;
    }

    @PostMapping
    public ResponseEntity<VendorTier> create(@RequestBody VendorTier tier) {
        return ResponseEntity.ok(
                vendorTierService.createTier(tier)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorTier> update(@PathVariable Long id,
                                             @RequestBody VendorTier tier) {
        return ResponseEntity.ok(
                vendorTierService.updateTier(id, tier)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorTier> get(@PathVariable Long id) {
        return ResponseEntity.ok(
                vendorTierService.getTierById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<VendorTier>> getAll() {
        return ResponseEntity.ok(
                vendorTierService.getAllTiers()
        );
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        vendorTierService.deactivateTier(id);
        return ResponseEntity.ok().build();
    }
}

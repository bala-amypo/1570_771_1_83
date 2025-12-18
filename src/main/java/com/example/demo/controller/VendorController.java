package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Vendor;
import com.example.demo.service.VendorService;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }
    
    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        Vendor v = vendorService.createVendor(vendor);
        return ResponseEntity.status(201).body(v);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(
            @PathVariable Long id,
            @RequestBody Vendor vendor) {
        Vendor updated = vendorService.updateVendor(id, vendor);
        return ResponseEntity.status(200).body(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Long id) {
        Vendor vendor = vendorService.getVendorById(id);
        return ResponseEntity.status(200).body(vendor);
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.status(200).body(vendorService.getAllVendors());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateVendor(@PathVariable Long id) {
        vendorService.deactivateVendor(id);
        return ResponseEntity.status(200).body("Vendor deactivated successfully");
    }
}

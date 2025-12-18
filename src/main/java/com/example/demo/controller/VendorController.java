package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private VendorService vendorService;

    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        Vendor vnd = vendorService.createVendor(vendor);
        return ResponseEntity.status(201).body(vnd);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVendor(
            @PathVariable int id,
            @RequestBody Vendor vendor) {

        Vendor updated = vendorService.updateVendor(id, vendor);
        if (updated != null) {
            return ResponseEntity.ok("Updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateVendor(@PathVariable int id) {
        Vendor deactivated = vendorService.deactivateVendor(id);
        if (deactivated != null) {
            return ResponseEntity.ok("Deactivated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getById(@PathVariable int id) {
        Vendor vnd = vendorService.getVendorById(id);
        if (vnd == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vnd);
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getAll() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }
}

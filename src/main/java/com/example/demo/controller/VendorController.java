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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired
    VendorService vendorService;

    @PostMapping
    public ResponseEntity<Vendor> createAll(@Valid @RequestBody Vendor vendor) {
        try {
            Vendor vnd=vendorService.createVendor(vendor);
            return ResponseEntity.status(201).body(vnd);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> putAll(int id, @RequestBody Vendor vendor) {
        if(vendorService.updateVendor(id, vendor)!=null) {
            return ResponseEntity.status(201).body("Successful");
        }
        else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getById(@PathVariable int id) {
        Vendor vnd=vendorService.getVendorById(id);
        return ResponseEntity.status(200).body(vnd);
    }

    @GetMapping
    public List<Vendor> getAll() {
        return vendorService.getAllVendors();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putAlll(int id, @RequestBody Vendor vendor) {
        if(vendorService.deactivateVendor(id, vendor)!=null) {
            return ResponseEntity.status(201).body("Successful");
        }
        else {
            return ResponseEntity.status(404).build();
        }
    }
}

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
    VendorService vendorService;

    @PostMapping
    public ResponseEntity<Vendor> createAll(@RequestBody Vendor vendor) {
        Vendor vnd=vendorService.createVendor(vendor);
        return ResponseEntity.status(201).body(vnd);
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
}

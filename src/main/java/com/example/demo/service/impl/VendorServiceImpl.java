package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    VendorRepository vendorRepository;

    @Override
    public Vendor createVendor(Vendor vendor) {
        if (vendorRepository.existsByName(vendor.getName())) {
            throw new IllegalArgumentException("Vendor name must be unique");
        }
        vendor.setActive(true);
        vendor.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        vendor.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor updateVendor(Long id, Vendor vendor) {
        Vendor existing = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        existing.setName(vendor.getName());
        existing.setContactEmail(vendor.getContactEmail());
        existing.setContactPhone(vendor.getContactPhone());
        existing.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return vendorRepository.save(existing);
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public void deactivateVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setActive(false);
        vendor.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        vendorRepository.save(vendor);
    }
}

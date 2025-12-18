package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Vendor;

@Service
public interface VendorService {
    Vendor createVendor(Vendor vendor);
    Vendor updateVendor(Long id, Vendor vendor);
    Vendor getVendorById(Long id);
    List<Vendor> getAllVendors();
    void deactivateVendor(Long id);
}

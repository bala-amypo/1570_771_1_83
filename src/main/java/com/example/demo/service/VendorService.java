package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Vendor;

@Service
public interface VendorService {
    Vendor createVendor(Vendor vendor);
    Vendor updateVendor(int id, Vendor UpdatedVendor);
    Vendor getVendorById(int id);
    List<Vendor>getAllVendors();
    Vendor deactivateVendor(int id, Vendor UpdatedVendor);
}

package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

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
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor updateVendor(int id,Vendor UpdatedVendor){
        Optional<Vendor> optionalVendor = vendorRepository. findById(id);
        if(optionalVendor.isPresent()){
        Vendor oldVendor = optionalVendor.get();
        oldVendor.setName(UpdatedVendor.getName());
        oldVendor.setContactEmail(UpdatedVendor.getContactEmail());
        oldVendor.setContactPhone(UpdatedVendor.getContactPhone());
        oldVendor.setActive(UpdatedVendor.getActive());
        oldVendor.setCreatedAt(UpdatedVendor.getCreatedAt());
        oldVendor.setUpdatedAt(UpdatedVendor.getUpdatedAt());
        return vendorRepository.save(oldVendor);
        }
        return null;
    }

    @Override
    public Vendor getVendorById(int id){
      Optional<Vendor> optionalVendor = vendorRepository. findById(id);
      return optionalVendor.orElse(null);
    }

    @Override
    public List<Vendor> getAllVendors(){
       return vendorRepository.findAll();
    }

    @Override
    public Vendor deactivateVendor(int id,Vendor UpdatedVendor){
        Optional<Vendor> optionalVendor = vendorRepository. findById(id);
        if(optionalVendor.isPresent()){
        Vendor oldVendor = optionalVendor.get();
        oldVendor.setName(UpdatedVendor.getName());
        oldVendor.setContactEmail(UpdatedVendor.getContactEmail());
        oldVendor.setContactPhone(UpdatedVendor.getContactPhone());
        oldVendor.setActive(UpdatedVendor.getActive());
        oldVendor.setCreatedAt(UpdatedVendor.getCreatedAt());
        oldVendor.setUpdatedAt(UpdatedVendor.getUpdatedAt());
        return vendorRepository.save(oldVendor);
        }
        return null;
    }
}

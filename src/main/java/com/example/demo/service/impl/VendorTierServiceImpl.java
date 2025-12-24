package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorTierServiceImpl implements VendorTierService {
    
    private final VendorTierRepository vendorTierRepository;
    
    public VendorTierServiceImpl(VendorTierRepository vendorTierRepository) {
        this.vendorTierRepository = vendorTierRepository;
    }
    
    @Override
    public VendorTier createTier(VendorTier tier) {
        if (tier.getMinScoreThreshold() == null || 
            tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException("Score threshold must be between 0–100");
        }
        
        if (vendorTierRepository.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique");
        }
        
        if (tier.getActive() == null) {
            tier.setActive(true);
        }
        
        return vendorTierRepository.save(tier);
    }
    
    @Override
    public VendorTier updateTier(Long id, VendorTier tier) {
        VendorTier existing = vendorTierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tier not found"));
        
        if (!existing.getTierName().equals(tier.getTierName())) {
            if (vendorTierRepository.existsByTierName(tier.getTierName())) {
                throw new IllegalArgumentException("Tier name must be unique");
            }
        }
        
        if (tier.getMinScoreThreshold() != null && 
            (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100)) {
            throw new IllegalArgumentException("Score threshold must be between 0–100");
        }
        
        existing.setTierName(tier.getTierName());
        existing.setDescription(tier.getDescription());
        if (tier.getMinScoreThreshold() != null) {
            existing.setMinScoreThreshold(tier.getMinScoreThreshold());
        }
        if (tier.getActive() != null) {
            existing.setActive(tier.getActive());
        }
        
        return vendorTierRepository.save(existing);
    }
    
    @Override
    public VendorTier getTierById(Long id) {
        return vendorTierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tier not found"));
    }
    
    @Override
    public List<VendorTier> getAllTiers() {
        return vendorTierRepository.findAll();
    }
    
    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = getTierById(id);
        tier.setActive(false);
        vendorTierRepository.save(tier);
    }
}
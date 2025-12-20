package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;

@Service
public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository vendorTierRepository;

    public VendorTierServiceImpl(VendorTierRepository vendorTierRepository) {
        this.vendorTierRepository = vendorTierRepository;
    }

    @Override
    public VendorTier createTier(VendorTier tier) {

        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException(
                    "Minimum score threshold must be between 0 and 100");
        }

        if (vendorTierRepository.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique");
        }

        tier.setActive(true);
        return vendorTierRepository.save(tier);
    }

    @Override
    public VendorTier updateTier(Long id, VendorTier tier) {

        VendorTier existing = vendorTierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tier not found"));

        if (!existing.getTierName().equals(tier.getTierName())
                && vendorTierRepository.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique");
        }

        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException(
                    "Minimum score threshold must be between 0 and 100");
        }

        existing.setTierName(tier.getTierName());
        existing.setMinScoreThreshold(tier.getMinScoreThreshold());
        existing.setDescription(tier.getDescription());

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
        VendorTier tier = vendorTierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tier not found"));
        tier.setActive(false);
        vendorTierRepository.save(tier);
    }
}

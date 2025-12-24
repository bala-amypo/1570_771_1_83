package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SLARequirementServiceImpl implements SLARequirementService {
    
    private final SLARequirementRepository slaRequirementRepository;
    
    public SLARequirementServiceImpl(SLARequirementRepository slaRequirementRepository) {
        this.slaRequirementRepository = slaRequirementRepository;
    }
    
    @Override
    public SLARequirement createRequirement(SLARequirement req) {
        if (req.getMaxDeliveryDays() == null || req.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days must be greater than 0");
        }
        if (req.getMinQualityScore() == null || req.getMinQualityScore() < 0 || req.getMinQualityScore() > 100) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }
        if (slaRequirementRepository.existsByRequirementName(req.getRequirementName())) {
            throw new IllegalArgumentException("Requirement name must be unique");
        }
        if (req.getActive() == null) {
            req.setActive(true);
        }
        return slaRequirementRepository.save(req);
    }
    
    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement req) {
        SLARequirement existing = slaRequirementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("SLA requirement not found"));
        
        if (!existing.getRequirementName().equals(req.getRequirementName())) {
            if (slaRequirementRepository.existsByRequirementName(req.getRequirementName())) {
                throw new IllegalArgumentException("Requirement name must be unique");
            }
        }
        
        if (req.getMaxDeliveryDays() != null && req.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days must be greater than 0");
        }
        if (req.getMinQualityScore() != null && 
            (req.getMinQualityScore() < 0 || req.getMinQualityScore() > 100)) {
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        }
        
        existing.setRequirementName(req.getRequirementName());
        existing.setDescription(req.getDescription());
        if (req.getMaxDeliveryDays() != null) {
            existing.setMaxDeliveryDays(req.getMaxDeliveryDays());
        }
        if (req.getMinQualityScore() != null) {
            existing.setMinQualityScore(req.getMinQualityScore());
        }
        if (req.getActive() != null) {
            existing.setActive(req.getActive());
        }
        
        return slaRequirementRepository.save(existing);
    }
    
    @Override
    public SLARequirement getRequirementById(Long id) {
        return slaRequirementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("SLA requirement not found"));
    }
    
    @Override
    public List<SLARequirement> getAllRequirements() {
        return slaRequirementRepository.findAll();
    }
    
    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement requirement = getRequirementById(id);
        requirement.setActive(false);
        slaRequirementRepository.save(requirement);
    }
}
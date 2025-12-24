package com.example.demo.service.impl;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {
    
    private final VendorPerformanceScoreRepository vendorPerformanceScoreRepository;
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorTierRepository vendorTierRepository;
    
    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository vendorPerformanceScoreRepository,
            DeliveryEvaluationRepository deliveryEvaluationRepository,
            VendorRepository vendorRepository,
            VendorTierRepository vendorTierRepository) {
        this.vendorPerformanceScoreRepository = vendorPerformanceScoreRepository;
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
        this.vendorTierRepository = vendorTierRepository;
    }
    
    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new RuntimeException("Vendor not found"));
        
        List<DeliveryEvaluation> evaluations = deliveryEvaluationRepository.findByVendorId(vendorId);
        
        double onTimePercentage = 0.0;
        double qualityCompliancePercentage = 0.0;
        
        if (!evaluations.isEmpty()) {
            long onTimeCount = evaluations.stream()
                .filter(e -> e.getMeetsDeliveryTarget() != null && e.getMeetsDeliveryTarget())
                .count();
            
            long qualityCount = evaluations.stream()
                .filter(e -> e.getMeetsQualityTarget() != null && e.getMeetsQualityTarget())
                .count();
            
            onTimePercentage = (onTimeCount * 100.0) / evaluations.size();
            qualityCompliancePercentage = (qualityCount * 100.0) / evaluations.size();
        }
        
        // Calculate overall score (using 50-50 weight)
        double overallScore = (onTimePercentage + qualityCompliancePercentage) / 2.0;
        
        // Ensure values are between 0 and 100
        onTimePercentage = Math.max(0, Math.min(100, onTimePercentage));
        qualityCompliancePercentage = Math.max(0, Math.min(100, qualityCompliancePercentage));
        overallScore = Math.max(0, Math.min(100, overallScore));
        
        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTimePercentage);
        score.setQualityCompliancePercentage(qualityCompliancePercentage);
        score.setOverallScore(overallScore);
        score.setCalculatedAt(new Timestamp(System.currentTimeMillis()));
        
        return vendorPerformanceScoreRepository.save(score);
    }
    
    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> scores = vendorPerformanceScoreRepository
            .findByVendorIdOrderByCalculatedAtDesc(vendorId);
        
        if (scores.isEmpty()) {
            return null;
        }
        
        return scores.get(0);
    }
    
    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return vendorPerformanceScoreRepository.findByVendorIdOrderByCalculatedAtDesc(vendorId);
    }
}
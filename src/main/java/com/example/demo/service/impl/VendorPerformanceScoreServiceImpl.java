package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorPerformanceScoreService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository scoreRepository;
    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorTierRepository tierRepository;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepository,
            DeliveryEvaluationRepository evaluationRepository,
            VendorRepository vendorRepository,
            VendorTierRepository tierRepository) {
        this.scoreRepository = scoreRepository;
        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
        this.tierRepository = tierRepository;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        List<DeliveryEvaluation> evaluations = evaluationRepository.findByVendorId(vendorId);

        long total = evaluations.size();
        long onTime = evaluations.stream().filter(DeliveryEvaluation::getMeetsDeliveryTarget).count();
        long quality = evaluations.stream().filter(DeliveryEvaluation::getMeetsQualityTarget).count();

        double onTimePct = total == 0 ? 0 : (onTime * 100.0 / total);
        double qualityPct = total == 0 ? 0 : (quality * 100.0 / total);
        double overall = (onTimePct + qualityPct) / 2;

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTimePct);
        score.setQualityCompliancePercentage(qualityPct);
        score.setOverallScore(overall);
        score.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return scoreRepository.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        return scoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId).get(0);
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}

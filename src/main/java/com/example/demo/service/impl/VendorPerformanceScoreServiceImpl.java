package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorPerformanceScoreService;

@Service
public class VendorPerformanceScoreServiceImpl
        implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository scoreRepository;
    private final DeliveryEvaluationRepository evaluationRepository;
    private final VendorRepository vendorRepository;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository scoreRepository,
            DeliveryEvaluationRepository evaluationRepository,
            VendorRepository vendorRepository) {

        this.scoreRepository = scoreRepository;
        this.evaluationRepository = evaluationRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        List<DeliveryEvaluation> evaluations =
                evaluationRepository.findByVendor_Id(vendorId);

        double onTimePercentage = 0;
        double qualityPercentage = 0;

        if (!evaluations.isEmpty()) {

            long onTimeCount = evaluations.stream()
                    .filter(e -> Boolean.TRUE.equals(e.getMeetsDeliveryTarget()))
                    .count();

            long qualityCount = evaluations.stream()
                    .filter(e -> Boolean.TRUE.equals(e.getMeetsQualityTarget()))
                    .count();

            onTimePercentage = (onTimeCount * 100.0) / evaluations.size();
            qualityPercentage = (qualityCount * 100.0) / evaluations.size();
        }

        double overallScore = (onTimePercentage + qualityPercentage) / 2;

        VendorPerformanceScore score = new VendorPerformanceScore();
        score.setVendor(vendor);
        score.setOnTimePercentage(onTimePercentage);
        score.setQualityCompliancePercentage(qualityPercentage);
        score.setOverallScore(overallScore);
        score.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

        return scoreRepository.save(score);
    }

    @Override
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        return scoreRepository
                .findByVendor_IdOrderByCalculatedAtDesc(vendorId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Score not found"));
    }

    @Override
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return scoreRepository
                .findByVendor_IdOrderByCalculatedAtDesc(vendorId);
    }
}

package com.example.demo.model;

import java.sql.Timestamp;

public class VendorPerformanceScore {
    private Long id;
    private String vendor;
    private Double onTimePercentage;
    private Double qualityCompliancePercentage;
    private Double overallScore;
    private Timestamp calculatedAt;

    public VendorPerformanceScore() {}

    public VendorPerformanceScore(String vendor, Double onTimePercentage, Double qualityCompliancePercentage, Double overallScore, Timestamp calculatedAt) {
        this.vendor = vendor;
        this.onTimePercentage = onTimePercentage;
        this.qualityCompliancePercentage = qualityCompliancePercentage;
        this.overallScore = overallScore;
        this.calculatedAt = calculatedAt;
    }

    public Long getId() {
        return id;
    }

    public get() {
        return ;
    }
}


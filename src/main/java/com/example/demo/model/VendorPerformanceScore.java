package com.example.demo.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="vendor_performance_scores")
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="vendor_id",nullable=false)
    private Vendor vendor;

    private Double onTimePercentage;
    
    private Double qualityCompliancePercentage;
    
    private Double overallScore;
    
    private Timestamp calculatedAt;

    public VendorPerformanceScore() {}

    public VendorPerformanceScore(Vendor vendor, Double onTimePercentage, Double qualityCompliancePercentage, Double overallScore, Timestamp calculatedAt) {
        this.vendor = vendor;
        this.onTimePercentage = onTimePercentage;
        this.qualityCompliancePercentage = qualityCompliancePercentage;
        this.overallScore = overallScore;
        this.calculatedAt = calculatedAt;
    }

    public Long getId() {
        return id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Double getOnTimePercentage() {
        return onTimePercentage;
    }

    public Double getQualityCompliancePercentage() {
        return qualityCompliancePercentage;
    }

    public Double getOverallScore() {
        return overallScore;
    }

    public Timestamp getCalculatedAt() {
        return calculatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setOnTimePercentage(Double onTimePercentage) {
        this.onTimePercentage = onTimePercentage;
    }

    public void setQualityCompliancePercentage(Double qualityCompliancePercentage) {
        this.qualityCompliancePercentage = qualityCompliancePercentage;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    public void setCalculatedAt(Timestamp calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
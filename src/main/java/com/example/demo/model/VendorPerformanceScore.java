package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "vendor_performance_scores")
public class VendorPerformanceScore {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;
    
    @Column(nullable = false)
    private Double onTimePercentage;
    
    @Column(nullable = false)
    private Double qualityCompliancePercentage;
    
    @Column(nullable = false)
    private Double overallScore;
    
    @Column(nullable = false)
    private Timestamp calculatedAt;
    
    // Constructors
    public VendorPerformanceScore() {}
    
    public VendorPerformanceScore(Vendor vendor, Double onTimePercentage, 
                                  Double qualityCompliancePercentage, Double overallScore) {
        this.vendor = vendor;
        this.onTimePercentage = onTimePercentage;
        this.qualityCompliancePercentage = qualityCompliancePercentage;
        this.overallScore = overallScore;
        this.calculatedAt = new Timestamp(System.currentTimeMillis());
    }
    
    @PrePersist
    protected void onCreate() {
        if (calculatedAt == null) {
            calculatedAt = new Timestamp(System.currentTimeMillis());
        }
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Vendor getVendor() {
        return vendor;
    }
    
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    
    public Double getOnTimePercentage() {
        return onTimePercentage;
    }
    
    public void setOnTimePercentage(Double onTimePercentage) {
        this.onTimePercentage = onTimePercentage;
    }
    
    public Double getQualityCompliancePercentage() {
        return qualityCompliancePercentage;
    }
    
    public void setQualityCompliancePercentage(Double qualityCompliancePercentage) {
        this.qualityCompliancePercentage = qualityCompliancePercentage;
    }
    
    public Double getOverallScore() {
        return overallScore;
    }
    
    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }
    
    public Timestamp getCalculatedAt() {
        return calculatedAt;
    }
    
    public void setCalculatedAt(Timestamp calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
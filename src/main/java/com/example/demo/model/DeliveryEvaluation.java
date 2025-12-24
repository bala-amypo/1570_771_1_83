package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery_evaluations")
public class DeliveryEvaluation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;
    
    @ManyToOne
    @JoinColumn(name = "sla_requirement_id", nullable = false)
    private SLARequirement slaRequirement;
    
    @Column(nullable = false)
    private Integer actualDeliveryDays;
    
    @Column(nullable = false)
    private Double qualityScore;
    
    @Temporal(TemporalType.DATE)
    private Date evaluationDate;
    
    private Boolean meetsDeliveryTarget;
    
    private Boolean meetsQualityTarget;
    
    // Constructors
    public DeliveryEvaluation() {}
    
    public DeliveryEvaluation(Vendor vendor, SLARequirement slaRequirement, 
                              Integer actualDeliveryDays, Double qualityScore) {
        this.vendor = vendor;
        this.slaRequirement = slaRequirement;
        this.actualDeliveryDays = actualDeliveryDays;
        this.qualityScore = qualityScore;
        this.evaluationDate = new Date();
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
    
    public SLARequirement getSlaRequirement() {
        return slaRequirement;
    }
    
    public void setSlaRequirement(SLARequirement slaRequirement) {
        this.slaRequirement = slaRequirement;
    }
    
    public Integer getActualDeliveryDays() {
        return actualDeliveryDays;
    }
    
    public void setActualDeliveryDays(Integer actualDeliveryDays) {
        this.actualDeliveryDays = actualDeliveryDays;
    }
    
    public Double getQualityScore() {
        return qualityScore;
    }
    
    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }
    
    public Date getEvaluationDate() {
        return evaluationDate;
    }
    
    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }
    
    public Boolean getMeetsDeliveryTarget() {
        return meetsDeliveryTarget;
    }
    
    public void setMeetsDeliveryTarget(Boolean meetsDeliveryTarget) {
        this.meetsDeliveryTarget = meetsDeliveryTarget;
    }
    
    public Boolean getMeetsQualityTarget() {
        return meetsQualityTarget;
    }
    
    public void setMeetsQualityTarget(Boolean meetsQualityTarget) {
        this.meetsQualityTarget = meetsQualityTarget;
    }
}
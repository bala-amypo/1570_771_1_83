package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "delivery_evaluations")
public class DeliveryEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "sla_requirement_id")
    private SLARequirement slaRequirement;

    private Integer actualDeliveryDays;

    private Double qualityScore;

    private Boolean meetsDeliveryTarget;

    private Boolean meetsQualityTarget;

    private LocalDate evaluationDate;

    public DeliveryEvaluation() {
    }

    public DeliveryEvaluation(Vendor vendor,
                              SLARequirement slaRequirement,
                              Integer actualDeliveryDays,
                              Double qualityScore,
                              LocalDate evaluationDate) {
        this.vendor = vendor;
        this.slaRequirement = slaRequirement;
        this.actualDeliveryDays = actualDeliveryDays;
        this.qualityScore = qualityScore;
        this.evaluationDate = evaluationDate;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public SLARequirement getSlaRequirement() {
        return slaRequirement;
    }

    public Integer getActualDeliveryDays() {
        return actualDeliveryDays;
    }

    public Double getQualityScore() {
        return qualityScore;
    }

    public Boolean getMeetsDeliveryTarget() {
        return meetsDeliveryTarget;
    }

    public Boolean getMeetsQualityTarget() {
        return meetsQualityTarget;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setSlaRequirement(SLARequirement slaRequirement) {
        this.slaRequirement = slaRequirement;
    }

    public void setActualDeliveryDays(Integer actualDeliveryDays) {
        this.actualDeliveryDays = actualDeliveryDays;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public void setMeetsDeliveryTarget(Boolean meetsDeliveryTarget) {
        this.meetsDeliveryTarget = meetsDeliveryTarget;
    }

    public void setMeetsQualityTarget(Boolean meetsQualityTarget) {
        this.meetsQualityTarget = meetsQualityTarget;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }
}

package com.example.demo.model;

import java.sql.Date;

public class DeliveryEvaluation() {
    private Long id;
    private String vendor;
    private String slaRequirement;
    private Integer actualDeliveryDays;
    private Double qualityScore;
    private Date evaluationDate;
    private Boolean meetsDeliveryTarget;
    private Boolean meetsQualityTarget;

    public DeliveryEvaluation() {}

    public DeliveryEvaluation(String vendor, String slaRequirement, Integer actualDeliveryDays, Double qualityScore, Date evaluationDate, Boolean meetsDeliveryTarget, Boolean meetsQualityTarget) {
        this.vendor = vendor;
        this.slaRequirement = slaRequirement;
        this.actualDeliveryDays = actualDeliveryDays;
        this.qualityScore = qualityScore;
        this.evaluationDate = evaluationDate;
        this.meetsDeliveryTarget = meetsDeliveryTarget;
        this.meetsQualityTarget = meetsQualityTarget;
    }

    public Long getId() {
        return id;
    }

    public String getVendor() {
        return vendor;
    }

    public String getSlaRequirement() {
        return slaRequirement;
    }

    public Integer getActualDeliveryDays() {
        return actualDeliveryDays;
    }

    public Double getQualityScore() {
        return qualityScore;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public Boolean getMeetsDeliveryTarget() {
        return meetsDeliveryTarget;
    }

    public Boolean getMeetsQualityTarget() {
        return meetsQualityTarget;
    }

    public void 
}
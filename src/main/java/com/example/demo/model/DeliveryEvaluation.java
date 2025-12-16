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
        
    }
}
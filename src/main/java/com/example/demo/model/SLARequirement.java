package com.example.demo.model;

public class SLARequirement() {
    private Long id; 
    private String requirementName;
    private String description;
    private Integer maxDeliveryDays;
    private Double minQualityScore;
    private Boolean active;

    public SLARequirement() {}

    public SLARequirement(String requirementName, String description, Integer maxDeliveryDays, Double minQualityScore, Boolean active) {
        this.requirementName = requirementName;
        this.description = description;
        this.maxDeliveryDays = maxDeliveryDays;
        this.minQualityScore = minQualityScore;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMaxDeliveryDays() {
        return maxDeliveryDays;
    }

    public Double getMinQualityScore() {
        return minQualityScore;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRequirementName(String id) {
        this.id = id;
    }

    public void setDescription(String id) {
        this.id = id;
    }

    public void setMaxDeliveryDays(Integer id) {
        this.id = id;
    }

    public void setMinQualityScore(Double id) {
        this.id = id;
    }

    public void setActive(Boolean id) {
        this.id = id;
    }
}
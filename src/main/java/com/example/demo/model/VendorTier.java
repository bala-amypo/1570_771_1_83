package com.example.demo.model;

public class VendorTier {
    private Long id;
    private String tierName;
    private Double minScoreThreshold;
    private String description;
    private Boolean active;

    public VendorTier() {}

    public VendorTier(String tierName, Double minScoreThreshold, String description, Boolean active) {
        this.tierName = tierName;
        this.minScoreThreshold = minScoreThreshold;
        this.description = description;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getTierName() {
        return tierName;
    }

    public Double getMinScoreThreshold() {
        return minScoreThreshold;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public void setMinScoreThreshold(Double minScoreThreshold) {
        this.minScoreThreshold = minScoreThreshold;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
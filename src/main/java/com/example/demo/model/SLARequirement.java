package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sla_requirements", uniqueConstraints = @UniqueConstraint(columnNames = "requirementName"))
public class SLARequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String requirementName;

    private String description;
    private Integer maxDeliveryDays;
    private Double minQualityScore;

    private Boolean active = true;

    @OneToMany(mappedBy = "slaRequirement")
    private List<DeliveryEvaluation> deliveryEvaluations;

    @PrePersist
    public void prePersist() {
        if (active == null) active = true;
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

    public List<DeliveryEvaluation> getDeliveryEvaluations() {
        return deliveryEvaluations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxDeliveryDays(Integer maxDeliveryDays) {
        this.maxDeliveryDays = maxDeliveryDays;
    }

    public void setMinQualityScore(Double minQualityScore) {
        this.minQualityScore = minQualityScore;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setDeliveryEvaluations(List<DeliveryEvaluation> deliveryEvaluations) {
        this.deliveryEvaluations = deliveryEvaluations;
    }   
}

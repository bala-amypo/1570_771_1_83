package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "vendors", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String contactEmail;
    private String contactPhone;

    private Boolean active = true;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "vendor")
    private List<DeliveryEvaluation> deliveryEvaluations;

    @OneToMany(mappedBy = "vendor")
    private List<VendorPerformanceScore> performanceScores;

    @PrePersist
    public void prePersist() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = createdAt;
        if (active == null) active = true;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public Boolean getActive() {
        return active;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public List<DeliveryEvaluation> getDeliveryEvaluations() {
        return deliveryEvaluations;
    }

    public List<VendorPerformanceScore> getPerformanceScores() {
        return performanceScores;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeliveryEvaluations(List<DeliveryEvaluation> deliveryEvaluations) {
        this.deliveryEvaluations = deliveryEvaluations;
    }

    public void setPerformanceScores(List<VendorPerformanceScore> performanceScores) {
        this.performanceScores = performanceScores;
    }    
}

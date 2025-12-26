package com.example.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String contactEmail;
    private String contactPhone;
    private Boolean active = true;

    @OneToMany(mappedBy = "vendor")
    private List<DeliveryEvaluation> evaluations = new ArrayList<>();

    @OneToMany(mappedBy = "vendor")
    private List<VendorPerformanceScore> performanceScores = new ArrayList<>();

    public Vendor() {
    }

    public Vendor(String name, String contactEmail, String contactPhone) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.active = true;
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

    public List<DeliveryEvaluation> getEvaluations() {
        return evaluations;
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

    public void setEvaluations(List<DeliveryEvaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public void setPerformanceScores(List<VendorPerformanceScore> performanceScores) {
        this.performanceScores = performanceScores;
    }
}

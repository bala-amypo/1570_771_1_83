package com.example.demo.model;

import java.sql.Timestamp;

public class Vendor {
    private Long id;
    private String name;
    private String contactEmail;
    private String contactPhone;
    private Boolean active;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Vendor() {}

    public Vendor(String name, String contactEmail, String contactPhone, Boolean active, Timestamp createdAt, Timestamp updatedAt) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public void setId(Long id) {
        this.id = id;
    }
}
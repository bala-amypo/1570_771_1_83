package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SLARequirement;

@Repository
public interface SLARequirementRepository extends JpaRepository<SLARequirement, Long> {

    boolean existsByRequirementName(String requirementName);
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;

@RestController
@RequestMapping("/api/evaluations")
public class DeliveryEvaluationController {

    private final DeliveryEvaluationService deliveryEvaluationService;

    public DeliveryEvaluationController(
            DeliveryEvaluationService deliveryEvaluationService) {
        this.deliveryEvaluationService = deliveryEvaluationService;
    }

    @PostMapping
    public ResponseEntity<DeliveryEvaluation> createEvaluation(
            @RequestBody DeliveryEvaluation evaluation) {

        return ResponseEntity.status(201)
                .body(deliveryEvaluationService.createEvaluation(evaluation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryEvaluation> getEvaluationById(
            @PathVariable Long id) {

        return ResponseEntity.status(200)
                .body(deliveryEvaluationService.getEvaluationById(id));
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<DeliveryEvaluation>> getEvaluationsForVendor(
            @PathVariable Long vendorId) {

        return ResponseEntity.status(200)
                .body(deliveryEvaluationService.getEvaluationsForVendor(vendorId));
    }

    @GetMapping("/requirement/{reqId}")
    public ResponseEntity<List<DeliveryEvaluation>> getEvaluationsForRequirement(
            @PathVariable Long reqId) {

        return ResponseEntity.status(200)
                .body(deliveryEvaluationService.getEvaluationsForRequirement(reqId));
    }
}

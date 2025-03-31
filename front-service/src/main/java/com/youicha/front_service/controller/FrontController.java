package com.youicha.front_service.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;

@Controller
public class FrontController {

    private final RestTemplate restTemplate;

    public FrontController(RestTemplateBuilder builder) {
        this.restTemplate = builder
                .basicAuthentication("admin", "admin123")
                .build();
    }

    @GetMapping("/patients")
    public String showPatients(Model model){
        try {
            // Fetch patients from patient-service via gateway
            Object[] patients = restTemplate.getForObject("http://gateway-service:8080/api/patients", Object[].class);
            model.addAttribute("patients", patients != null ? Arrays.asList(patients) : Collections.emptyList());
        } catch (Exception e) {
            model.addAttribute("error", "Unable to fetch patients: " + e.getMessage());
            model.addAttribute("patients", Collections.emptyList());
        }
        return "patients";
    }
}
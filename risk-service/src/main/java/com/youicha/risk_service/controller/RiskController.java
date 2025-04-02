package com.youicha.risk_service.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.youicha.risk_service.dto.Note;
import com.youicha.risk_service.dto.Patient;
import com.youicha.risk_service.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Controller for evaluating a patient's risk level based on their notes history
 */
@RestController
@RequestMapping("/risk")
public class RiskController {

    @Autowired
    private RiskService riskService;

    private final RestTemplate restTemplate;

    /**
     * Constructs a RiskController with a RestTemplate configured for authentication
     *
     * @param builder RestTemplateBuilder used to configure the RestTemplate
     */
    public RiskController(RestTemplateBuilder builder) {
        this.restTemplate = builder
                .basicAuthentication("admin", "admin123")
                .build();
    }

    /**
     * Retrieves the risk evaluation for a given patient ID
     *
     * @param patientId The ID of the patient
     * @return A string representing the patient's risk level
     * @throws ResponseStatusException If the patient is not found
     */
    @GetMapping("/{patientId}")
    public String getRisk(@PathVariable Long patientId){

        // Retrieving patient from patient-service via gateway-service
        Patient patient = restTemplate.getForObject("http://gateway-service:8080/api/patients/" + patientId, Patient.class);

        if (patient == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient not found with ID: " + patientId);
        }

        // Retrieving medicals notes from notes-service via gateway-service
        Note[] notesArray = restTemplate.getForObject("http://gateway-service:8080/notes/" + patientId, Note[].class);
        List<Note> notes = (notesArray != null) ? Arrays.asList(notesArray) : Collections.emptyList();

        // Return the result of risk evaluation
        return riskService.evaluateRisk(patient, notes);
    }
}

package com.youicha.risk_service.controller;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.youicha.risk_service.model.Note;
import com.youicha.risk_service.model.Patient;
import com.youicha.risk_service.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/risk")
public class RiskController {

    @Autowired
    private RiskService riskService;

    private final RestTemplate restTemplate;

    public RiskController(RestTemplateBuilder builder) {
        this.restTemplate = builder
                .basicAuthentication("admin", "admin123")
                .build();
    }

    @GetMapping("/{patientId}")
    public String getRisk(@PathVariable Long patientId){

        // Retrieving patient from patient-service via gateway-service
        Patient patient = restTemplate.getForObject("http://gateway-service:8080/patients/" + patientId, Patient.class);

        if (patient == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient not found with ID: " + patientId);
        }

        // Retrieving medicals notes from notes-service via gateway-service
        Note[] notesArray = restTemplate.getForObject("http://gateway-service:8080/notes/" + patientId, Note[].class);
        List<Note> notes = Arrays.asList(notesArray);

        return riskService.evaluateRisk(patient, notes);
    }
}

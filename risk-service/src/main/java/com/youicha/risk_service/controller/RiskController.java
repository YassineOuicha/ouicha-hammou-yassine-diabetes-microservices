package com.youicha.risk_service.controller;

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

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{patientId}")
    public String getRisk(@PathVariable Long patientId){

        // Retrieving patient from patient-service by using the Gateway Service
        Patient patient = restTemplate.getForObject("http://localhost:8080/patients/" + patientId, Patient.class);

        // Retrieving medicals notes from notes-service by using the Gateway Service
        Note[] notesArray = restTemplate.getForObject("http://localhost:8080/notes/" + patientId, Note[].class);
        List<Note> notes = Arrays.asList(notesArray);

        return riskService.evaluateRisk(patient, notes);
    }
}

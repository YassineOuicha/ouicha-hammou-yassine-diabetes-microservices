package com.youicha.front_service.controller;

import com.youicha.front_service.dto.Note;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
            // Fetching patients from patient-service via gateway-service
            Object[] patients = restTemplate.getForObject("http://gateway-service:8080/api/patients", Object[].class);
            model.addAttribute("patients", patients != null ? Arrays.asList(patients) : Collections.emptyList());
        } catch (Exception e) {
            model.addAttribute("error", "Unable to fetch patients: " + e.getMessage());
            model.addAttribute("patients", Collections.emptyList());
        }
        return "patients";
    }

    @GetMapping("/patients/{id}")
    public String showPatientDetails(@PathVariable Long id, Model model) {
        try {
            // Fetching patient details from patient-service via gateway-service
            Object patient = restTemplate.getForObject("http://gateway-service:8080/api/patients/" + id, Object.class);
            model.addAttribute("patient", patient);

            // Fetching patient's notes from notes-service via gateway-service
            Object[] notes = restTemplate.getForObject("http://gateway-service:8080/notes/" + id, Object[].class);
            model.addAttribute("notes", notes != null ? Arrays.asList(notes) : Collections.emptyList());

            // A new Note DTO for the add form
            Note newNote = new Note();
            newNote.setPatientId(id);
            model.addAttribute("note", newNote);

            // Fetching risk evaluation from risk-service via gateway-service
            String riskEvaluation = restTemplate.getForObject("http://gateway-service:8080/risk/" + id, String.class);
            model.addAttribute("riskEvaluation", riskEvaluation);

        } catch (Exception e) {
            model.addAttribute("error", "Unable to fetch patient details: " + e.getMessage());
        }
        return "patient-details";
    }

    @PostMapping("/notes")
    public String addNote(@ModelAttribute("note") Note note) {
        try {
            // Sending the new note to notes-service via gateway-service
            restTemplate.postForObject("http://gateway-service:8080/notes", note, Note.class);
        } catch (Exception e) {
            System.out.println("Error while adding the note: " + e.getMessage());
        }
        // Redirects to patient details page after adding the note
        return "redirect:/patients/" + note.getPatientId();
    }
}
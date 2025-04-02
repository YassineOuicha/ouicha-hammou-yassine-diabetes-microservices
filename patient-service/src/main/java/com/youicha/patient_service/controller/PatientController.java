package com.youicha.patient_service.controller;

import com.youicha.patient_service.model.Patient;
import com.youicha.patient_service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Patient related CRUD operations
 * Handles HTTP requests for patient management
 */
@RestController
@RequestMapping("api/patients")
public class PatientController {

    /**
     * Service layer dependency for patient's related CRUD operations
     */
    @Autowired
    private PatientService patientService;

    /**
     * Retrieves all patients from the database of patients
     * @return List of all patients
     */
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    /**
     * Retrieves a specific patient by their ID
     * @param id The unique identifier of the patient
     * @return Patient if found, otherwise empty Optional
     */
    @GetMapping("/{id}")
    public Optional<Patient> getPatient(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    /**
     * Creates a new patient or updates an existing one
     * @param patient The patient object with data to save / update in database
     * @return The saved / updated patient with generated ID
     */
    @PostMapping
    public Patient createOrUpdatePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    /**
     * Deletes a patient from the database
     * @param id The unique identifier of the patient to be deleted
     */
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatientById(id);
    }
}
package com.youicha.patient_service.service;

import com.youicha.patient_service.model.Patient;
import com.youicha.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Patient related CRUD operations
 * Handles business logic for patient management
 */
@Service
public class PatientService {

    /**
     * Repository for database crud operations related to patients
     */
    @Autowired
    private PatientRepository patientRepository;

    /**
     * Retrieves all patients from the database
     * @return List of all patients
     */
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    /**
     * Finds a patient by their ID
     * @param id The unique identifier of the patient to find
     * @return Optional containing the patient if found and empty otherwise
     */
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    /**
     * Saves a new patient or updates an existing one
     * @param patient The patient object to save / update
     * @return The saved / updated patient with assigned ID
     */
    public Patient savePatient(Patient patient) {
        if (patient.getId() == null) {
            return patientRepository.save(patient);
        }

        Optional<Patient> existingPatientOpt = patientRepository.findById(patient.getId());
        if (existingPatientOpt.isPresent()) {
            Patient existingPatient = existingPatientOpt.get();
            existingPatient.setFirstName(patient.getFirstName());
            existingPatient.setLastName(patient.getLastName());
            existingPatient.setBirthDate(patient.getBirthDate());
            existingPatient.setGender(patient.getGender());
            existingPatient.setPhoneNumber(patient.getPhoneNumber());
            existingPatient.setAddress(patient.getAddress());

            return patientRepository.save(existingPatient);
        }
        return patientRepository.save(patient);
    }

    /**
     * Deletes a patient by their ID
     * @param patientId The unique identifier of the patient to delete
     */
    public void deletePatientById(Long patientId){
        patientRepository.deleteById(patientId);
    }
}
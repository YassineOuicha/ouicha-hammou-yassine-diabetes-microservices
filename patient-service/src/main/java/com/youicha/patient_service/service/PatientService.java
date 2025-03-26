package com.youicha.patient_service.service;

import com.youicha.patient_service.model.Patient;
import com.youicha.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patient) {
        Optional<Patient> existingPatient = patientRepository.findById(id);
        if (existingPatient.isPresent()) {
            Patient updatedPatient = existingPatient.get();
            updatedPatient.setFirstName(patient.getFirstName());
            updatedPatient.setLastName(patient.getLastName());
            updatedPatient.setBirthDate(patient.getBirthDate());
            updatedPatient.setGender(patient.getGender());
            updatedPatient.setAddress(patient.getAddress());
            updatedPatient.setPhoneNumber(patient.getPhoneNumber());
            return patientRepository.save(updatedPatient);
        }
        return null;
    }

    public void deletePatientById(Long patientId){
        patientRepository.deleteById(patientId);
    }
}

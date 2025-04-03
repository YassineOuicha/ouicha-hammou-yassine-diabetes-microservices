package com.youicha.risk_service.service;

import org.springframework.stereotype.Service;
import com.youicha.risk_service.dto.Patient;
import com.youicha.risk_service.dto.Note;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

/**
 * Service for evaluating the risk level of a patient based on his medical notes
 */
@Service
public class RiskService {

    // Indicators terms to evaluate the risk
    private static final Set<String> TRIGGER_TERMS = Set.of(
            "Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Fumeuse",
            "Anormal", "Cholestérol", "Vertiges", "Rechute", "Réaction", "Anticorps"
    );

    /**
     * Counts the number of trigger terms found in the patient's medical notes
     *
     * @param notes The list of medical notes
     * @return The count of trigger terms found
     */
    public int countTriggers(List<Note> notes) {
        int count = 0;
        for (Note note : notes) {
            if (note.getNoteText() != null) {
                for (String trigger : TRIGGER_TERMS) {
                    if (note.getNoteText().toLowerCase().contains(trigger.toLowerCase())) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Evaluates the risk level of a patient based on their age, gender, and medical notes
     *
     * @param patient The patient whose risk level needs to be evaluated
     * @param notes The list of medical notes associated with the patient
     * @return A string representing the patient's risk level
     */
    public String evaluateRisk(Patient patient, List<Note> notes) {

        int triggerCount = countTriggers(notes);
        int age = calculateAge(patient.getBirthDate());
        String gender = patient.getGender().toLowerCase();

        // EarlyOnset
        if ((age < 30 && gender.equals("m") && triggerCount >= 5) ||
                (age < 30 && gender.equals("f") && triggerCount >= 7) ||
                (age > 30 && triggerCount >= 8)) {
            return "EarlyOnset";
        }

        // InDanger
        if ((age < 30 && gender.equals("m") && triggerCount >= 3) ||
                (age < 30 && gender.equals("f") && triggerCount >= 4) ||
                (age > 30 && triggerCount >= 6 && triggerCount <= 7)) {
            return "InDanger";
        }

        // Borderline
        if (age > 30 && triggerCount >= 2 && triggerCount <= 5) {
            return "Borderline";
        }

        return "None";
    }

    /**
     * Calculates the age of a patient based on their birthdate
     *
     * @param birthDate The birthdate of the patient
     * @return The calculated age
     */
    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}

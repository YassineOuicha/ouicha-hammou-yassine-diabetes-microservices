package com.youicha.risk_service.service;

import org.springframework.stereotype.Service;
import com.youicha.risk_service.model.Patient;
import com.youicha.risk_service.model.Note;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

@Service
public class RiskService {

    private static final Set<String> TRIGGER_TERMS = Set.of(
            "Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Fumeuse",
            "Anormal", "Cholestérol", "Vertiges", "Rechute", "Réaction", "Anticorps"
    );

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

    public String evaluateRisk(Patient patient, List<Note> notes) {

        int triggerCount = countTriggers(notes);
        int age = calculateAge(patient.getBirthDate());
        String gender = patient.getGender().toLowerCase();

        // Early Onset
        if ((age < 30 && gender.equals("m") && triggerCount >= 5) ||
                (age < 30 && gender.equals("f") && triggerCount >= 7) ||
                (age > 30 && triggerCount >= 8)) {
            return "Early Onset";
        }

        // In Danger
        if ((age < 30 && gender.equals("m") && triggerCount >= 3) ||
                (age < 30 && gender.equals("f") && triggerCount >= 4) ||
                (age > 30 && triggerCount >= 6 && triggerCount <= 7)) {
            return "In Danger";
        }

        // Borderline
        if (age > 30 && triggerCount >= 2 && triggerCount <= 5) {
            return "Borderline";
        }

        return "None";
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

}

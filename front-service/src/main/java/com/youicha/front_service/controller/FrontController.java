package com.youicha.front_service.controller;

import com.youicha.front_service.dto.Note;
import com.youicha.front_service.dto.Patient;
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

/**
 * Controller for the front-service that handles the presentation layer
 * and manages HTTP requests related to the user interface
 */
@Controller
public class FrontController {

    // Instance of RestTemplate used to make HTTP calls to other services
    private final RestTemplate restTemplate;

    /**
     * Constructor that initializes a RestTemplate with basic authentication credentials
     *
     * @param builder A RestTemplateBuilder used to create a configured RestTemplate
     */
    public FrontController(RestTemplateBuilder builder) {
        // Configure the RestTemplate with basic authentication ("admin"/"admin123")
        this.restTemplate = builder
                .basicAuthentication("admin", "admin123")
                .build();
    }

    /**
     * Handles GET requests to display all patients
     * Fetches patient data from the patient-service via the gateway-service
     *
     * @param model The Spring Model object used to add attributes for the view template
     * @return The name of the view template to be completed
     */
    @GetMapping("/patients")
    public String showPatients(Model model) {
        try {
            // Call the patient-service via the gateway-service to retrieve all patients
            Object[] patients = restTemplate.getForObject("http://gateway-service:8080/api/patients", Object[].class);
            // Add the list of patients to the model, and if no patients are returned add an empty list instead
            model.addAttribute("patients", patients != null ? Arrays.asList(patients) : Collections.emptyList());
        } catch (Exception e) {
            // In case of error add an error message and an empty list to the model
            model.addAttribute("error", "Unable to fetch patients: " + e.getMessage());
            model.addAttribute("patients", Collections.emptyList());
        }
        // Return the view template name for displaying the list of patients
        return "patients";
    }

    /**
     * Handles GET requests to display the details of a specific patient
     * Retrieves patient details, associated notes, and risk evaluation from different services via the gateway-service
     *
     * @param id The ID of the patient whose details are to be displayed
     * @param model The Spring Model object used to add attributes for the view template
     * @return The name of the view template to display the patient's details
     */
    @GetMapping("/patients/{id}")
    public String showPatientDetails(@PathVariable Long id, Model model) {
        try {
            // Retrieve patient details from the patient-service via the gateway-service
            Object patient = restTemplate.getForObject("http://gateway-service:8080/api/patients/" + id, Object.class);
            model.addAttribute("patient", patient);

            // Retrieve notes associated with the patient from the notes-service via the gateway-service
            Object[] notes = restTemplate.getForObject("http://gateway-service:8080/notes/" + id, Object[].class);
            model.addAttribute("notes", notes != null ? Arrays.asList(notes) : Collections.emptyList());

            // Create a new Note object for the add note form and assign the patient's ID
            Note newNote = new Note();
            newNote.setPatientId(id);
            model.addAttribute("note", newNote);

            // Retrieve the risk evaluation for the patient from the risk-service via the gateway-service
            String riskEvaluation = restTemplate.getForObject("http://gateway-service:8080/risk/" + id, String.class);
            model.addAttribute("riskEvaluation", riskEvaluation);

        } catch (Exception e) {
            // In case of error add an error message to the model
            model.addAttribute("error", "Unable to fetch patient details: " + e.getMessage());
        }
        // Return the view template name for displaying the patient's details
        return "patient-details";
    }

    /**
     * Displays the form for editing an existing patient
     *
     * @param id the unique identifier of the patient to edit
     * @param model the Spring Model object used to pass attributes to the view
     * @return patient-form if the patient is retrieved successfully, otherwise redirects to the patients list
     */
    @GetMapping("/patients/edit/{id}")
    public String showEditPatientForm(@PathVariable Long id, Model model) {
        try {
            // Retrieving patient from patient-service via gateway-service
            Patient patient = restTemplate.getForObject("http://gateway-service:8080/api/patients/" + id, Patient.class);
            model.addAttribute("patient", patient);
        } catch (Exception e) {
            model.addAttribute("error", "Error while fetching the patient : " + id + " : " + e.getMessage());
            return "redirect:/patients";
        }
        return "patient-form";
    }

    /**
     * Processes the form submission for creating or updating a patient
     *
     * @param patient the patient object populated from the form data
     * @param model the Spring model to add attributes for the view
     * @return a redirect to the list of patients after successful save or the form view if an error occurs
     */
    @PostMapping("/patients/save")
    public String savePatient(@ModelAttribute("patient") Patient patient, Model model) {
        try {
            // Sending the modified data of the patient to patient-service via gateway-service
            restTemplate.postForObject("http://gateway-service:8080/api/patients/save", patient, Patient.class);
        } catch (Exception e) {
            model.addAttribute("error", "Error while saving the patient : " + e.getMessage());
            return "patient-form";
        }
        return "redirect:/patients";
    }

    /**
     * Displays the form for adding a new patient
     *
     * @param model the Spring model to add attributes for the view
     * @return the form view for adding a new patient
     */
    @GetMapping("/patients/new")
    public String showNewPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-form";
    }

    /**
     * Handles POST requests for adding a new note
     * Sends the new note to the notes-service via the gateway-service
     *
     * @param note The Note object entered from the submitted form
     * @return A redirection to the patient's details page
     */
    @PostMapping("/notes")
    public String addNote(@ModelAttribute("note") Note note, Model model) {
        try {
            // Send the new note to the notes-service via the gateway-service
            restTemplate.postForObject("http://gateway-service:8080/notes", note, Note.class);
        } catch (Exception e) {
            // Print an error message to the console if there is an issue while adding the note
            model.addAttribute("error", "Error while adding the note: " + e.getMessage());
        }
        // Redirect to the patient's details page after adding the note
        return "redirect:/patients/" + note.getPatientId();
    }

    /**
     * Handles the deletion of an existing patient
     *
     * @param id the unique identifier of the patient to be deleted
     * @param model the Spring model to add attributes for the view
     * @return A redirection to the patient's details page
     */
    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable Long id, Model model) {
        try {
            // Sending the id of patient to delete from patient-service via gateway-service
            restTemplate.delete("http://gateway-service:8080/api/patients/" + id);
            model.addAttribute("successMessage", "The patient with the ID : " + id +" has been deleted");
        } catch (Exception e) {
            model.addAttribute("error", "Error while deleting the patient  with the ID : " + id + ", Error : " + e.getMessage());
        }
        return "redirect:/patients";
    }
}

package com.youicha.notes_service.service;

import com.youicha.notes_service.model.Note;
import com.youicha.notes_service.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Service class for Note CRUD operations
 * Handles business logic for patient notes
 */
@Service
public class NoteService {

    /**
     * Repository for database CRUD operations related to notes
     */
    @Autowired
    private NoteRepository noteRepository;

    /**
     * Retrieves all notes for a specific patient
     * @param patientId The unique identifier of the patient
     * @return List of notes associated with the patient
     */
    public List<Note> getNotesByPatient(@PathVariable Long patientId) {
        return noteRepository.findByPatientId(patientId);
    }

    /**
     * Creates a new note in the database
     * @param note The note object to be saved in database
     * @return The saved note with generated ID
     */
    public Note createNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    /**
     * Deletes a note from the database
     * @param note The note object to be deleted from database
     */
    public void deleteNote(@RequestBody Note note){
        noteRepository.delete(note);
    }
}
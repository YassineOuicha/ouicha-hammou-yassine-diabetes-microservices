package com.youicha.notes_service.controller;

import com.youicha.notes_service.model.Note;
import com.youicha.notes_service.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for Note CRUD operations
 * Handles HTTP requests for managing patient's notes
 */
@RestController
@RequestMapping("/notes")
public class NoteController {

    /**
     * Service layer dependency for note CRUD operations
     */
    @Autowired
    private NoteService noteService;

    /**
     * Retrieves all notes for a specific patient
     * @param patientId The unique identifier of the patient
     * @return List of notes associated with the patient
     */
    @GetMapping("/{patientId}")
    public List<Note> getNotesByPatient(@PathVariable Long patientId) {
        return noteService.getNotesByPatient(patientId);
    }

    /**
     * Creates a new note
     * @param note The note object containing information to save
     * @return The saved note with generated ID
     */
    @PostMapping
    public Note addNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    /**
     * Deletes a note from the database
     * @param note The note object to be deleted from database
     */
    @DeleteMapping
    public void deleteNote(@RequestBody Note note){
        noteService.deleteNote(note);
    }
}
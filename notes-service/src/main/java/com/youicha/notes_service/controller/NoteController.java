package com.youicha.notes_service.controller;

import com.youicha.notes_service.model.Note;
import com.youicha.notes_service.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/{patientId}")
    public List<Note> getNotesByPatient(@PathVariable Long patientId) {
        return noteService.getNotesByPatient(patientId);
    }

    @PostMapping
    public Note addNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @DeleteMapping
    public void deleteNote(@RequestBody Note note){
        noteService.deleteNote(note);
    }
}

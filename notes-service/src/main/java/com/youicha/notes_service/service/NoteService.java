package com.youicha.notes_service.service;

import com.youicha.notes_service.model.Note;
import com.youicha.notes_service.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getNotesByPatient(@PathVariable Long patientId) {
        return noteRepository.findByPatientId(patientId);
    }

    public Note createNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    public void deleteNote(@RequestBody Note note){
        noteRepository.delete(note);
    }
}

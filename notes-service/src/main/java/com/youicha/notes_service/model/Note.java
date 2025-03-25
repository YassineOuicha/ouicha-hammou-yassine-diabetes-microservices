package com.youicha.notes_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collation = "notes")
public class Note {

    @Id
    private String id;
    private Long patientId;
    private String noteText;

    // Constructors

    public Note() {
    }

    public Note(Long patientId, String noteText) {
        this.patientId = patientId;
        this.noteText = noteText;
    }

    // Setters and getters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}

package com.youicha.risk_service.dto;

/**
 * Data Transfer Object representing a medical note
 */
public class Note {

    private String id;
    private Long patientId;
    private String noteText;

    // Constructors

    public Note() {
    }

    public Note(String noteText, Long patientId, String id) {
        this.noteText = noteText;
        this.patientId = patientId;
        this.id = id;
    }

    // Getters et setters

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

package com.youicha.front_service.dto;


/**
 * Data Transfer Object representing a Note
 */
public class Note {

    private String id;
    private Long patientId;
    private String noteText;

    // constructors

    public Note() {
    }

    public Note(String id, Long patientId, String noteText) {
        this.id = id;
        this.patientId = patientId;
        this.noteText = noteText;
    }

    // Getters and Setters

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}

package com.youicha.notes_service.repository;

import com.youicha.notes_service.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for Note CRUD operations
 * Extends MongoRepository for MongoDB database access
 */
@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    /**
     * Finds all notes associated with a specific patient
     * @param patientId The unique identifier of the patient
     * @return List of notes belonging to the patient
     */
    List<Note> findByPatientId(Long patientId);
}

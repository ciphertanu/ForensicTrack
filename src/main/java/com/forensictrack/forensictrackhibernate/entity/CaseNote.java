package com.forensictrack.forensictrackhibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "case_notes")
public class CaseNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private int noteId;

    @Column(name = "case_id")
    private Integer caseId;

    @Column(name = "investigator_id")
    private Integer investigatorId;

    @Column(name = "note")
    private String note;

    @Column(name = "note_date")
    private LocalDateTime noteDate;

    // Default constructor
    public CaseNote() {
    }

    // Parameterized constructor
    public CaseNote(Integer caseId, Integer investigatorId,
                    String note, LocalDateTime noteDate) {

        this.caseId = caseId;
        this.investigatorId = investigatorId;
        this.note = note;
        this.noteDate = noteDate;
    }

    // Getters and Setters

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getInvestigatorId() {
        return investigatorId;
    }

    public void setInvestigatorId(Integer investigatorId) {
        this.investigatorId = investigatorId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(LocalDateTime noteDate) {
        this.noteDate = noteDate;
    }
}
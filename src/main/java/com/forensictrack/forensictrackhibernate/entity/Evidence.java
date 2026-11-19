package com.forensictrack.forensictrackhibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "evidence")
public class Evidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evidence_id")
    private int evidenceId;

    @Column(name = "case_id")
    private Integer caseId;

    @Column(name = "evidence_type")
    private String evidenceType;

    @Column(name = "description")
    private String description;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "collected_date")
    private LocalDate collectedDate;

    // Default constructor
    public Evidence() {
    }

    // Parameterized constructor
    public Evidence(Integer caseId, String evidenceType,
                    String description, String filePath,
                    LocalDate collectedDate) {

        this.caseId = caseId;
        this.evidenceType = evidenceType;
        this.description = description;
        this.filePath = filePath;
        this.collectedDate = collectedDate;
    }

    // Getters and Setters

    public int getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(int evidenceId) {
        this.evidenceId = evidenceId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceType(String evidenceType) {
        this.evidenceType = evidenceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDate getCollectedDate() {
        return collectedDate;
    }

    public void setCollectedDate(LocalDate collectedDate) {
        this.collectedDate = collectedDate;
    }
}
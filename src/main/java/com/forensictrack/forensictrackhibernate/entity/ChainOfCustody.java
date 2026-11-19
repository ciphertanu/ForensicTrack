package com.forensictrack.forensictrackhibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "chain_of_custody")
public class ChainOfCustody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custody_id")
    private int custodyId;

    @Column(name = "evidence_id")
    private Integer evidenceId;

    @Column(name = "handled_by")
    private String handledBy;

    @Column(name = "action_taken")
    private String actionTaken;

    @Column(name = "action_date")
    private LocalDateTime actionDate;

    // Default constructor
    public ChainOfCustody() {
    }

    // Parameterized constructor
    public ChainOfCustody(Integer evidenceId,
                          String handledBy,
                          String actionTaken,
                          LocalDateTime actionDate) {

        this.evidenceId = evidenceId;
        this.handledBy = handledBy;
        this.actionTaken = actionTaken;
        this.actionDate = actionDate;
    }

    // Getters and Setters

    public int getCustodyId() {
        return custodyId;
    }

    public void setCustodyId(int custodyId) {
        this.custodyId = custodyId;
    }

    public Integer getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(Integer evidenceId) {
        this.evidenceId = evidenceId;
    }

    public String getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(String handledBy) {
        this.handledBy = handledBy;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public LocalDateTime getActionDate() {
        return actionDate;
    }

    public void setActionDate(LocalDateTime actionDate) {
        this.actionDate = actionDate;
    }
}
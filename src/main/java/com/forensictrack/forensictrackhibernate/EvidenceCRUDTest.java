package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.Evidence;
import java.time.LocalDate;

public class EvidenceCRUDTest {

    public static void main(String[] args) {

        EvidenceDAO dao = new EvidenceDAO();

        // =========================
        // CREATE
        // =========================

        Evidence newEvidence = new Evidence(
                1,
                "Digital Evidence",
                "Suspicious login activity captured from the college server.",
                "C:\\evidence\\server_log.txt",
                LocalDate.now()
        );

        dao.createEvidence(newEvidence);

        // =========================
        // READ
        // =========================

        dao.readEvidence();

        // =========================
        // UPDATE
        // =========================

        dao.updateEvidence(
                newEvidence.getEvidenceId(),
                "Digital Evidence - Updated",
                "Updated forensic evidence from the college server investigation.",
                "C:\\evidence\\updated_server_log.txt"
        );

        // READ after UPDATE
        dao.readEvidence();

        // =========================
        // DELETE
        // =========================

        dao.deleteEvidence(newEvidence.getEvidenceId());

        // READ after DELETE
        dao.readEvidence();

        HibernateUtil.shutdown();
    }
}
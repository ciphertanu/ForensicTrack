package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.ChainOfCustody;
import java.time.LocalDateTime;

public class ChainOfCustodyCRUDTest {

    public static void main(String[] args) {

        ChainOfCustodyDAO dao = new ChainOfCustodyDAO();

        // =========================
        // CREATE
        // =========================

        ChainOfCustody newRecord = new ChainOfCustody(
                1,
                "Tanushree",
                "Evidence received and documented.",
                LocalDateTime.now()
        );

        dao.createCustody(newRecord);

        // =========================
        // READ
        // =========================

        dao.readCustodyRecords();

        // =========================
        // UPDATE
        // =========================

        dao.updateCustody(
                newRecord.getCustodyId(),
                "Tanushree",
                "Evidence transferred to forensic laboratory."
        );

        // READ after UPDATE
        dao.readCustodyRecords();

        // =========================
        // DELETE
        // =========================

        dao.deleteCustody(newRecord.getCustodyId());

        // READ after DELETE
        dao.readCustodyRecords();

        HibernateUtil.shutdown();
    }
}
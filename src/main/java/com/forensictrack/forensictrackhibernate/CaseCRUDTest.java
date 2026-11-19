package com.forensictrack.forensictrackhibernate;
import com.forensictrack.forensictrackhibernate.entity.Case;
import java.time.LocalDate;
public class CaseCRUDTest {
public static void main(String[] args) {
CaseDAO dao = new CaseDAO();
// =========================
// CREATE
// =========================
Case newCase = new Case(
"Case Hibernate Test",
"Cyber Crime",
"Test forensic case created using Hibernate.",
"High",
"Open",
LocalDate.now(),
1
);
dao.createCase(newCase);
// =========================
// READ
// =========================
dao.readCases();
// =========================
// UPDATE
// =========================
dao.updateCase(
newCase.getCaseId(),
"Medium",
"Under Investigation"
);
// READ after UPDATE
dao.readCases();
// =========================
// DELETE
// =========================
dao.deleteCase(newCase.getCaseId());
// READ after DELETE
dao.readCases();
HibernateUtil.shutdown();
}
}
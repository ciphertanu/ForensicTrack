
package com.forensictrack.forensictrackhibernate;

import com.forensictrack.forensictrackhibernate.entity.ChainOfCustody;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/custody")
public class ChainOfCustodyServlet extends HttpServlet {

    private ChainOfCustodyDAO custodyDAO;
    private EvidenceDAO evidenceDAO;

    // SPRING DEPENDENCY INJECTION
    @Override
    public void init() throws ServletException {

        custodyDAO = SpringUtil.getBean(
                getServletContext(),
                ChainOfCustodyDAO.class
        );

        evidenceDAO = SpringUtil.getBean(
                getServletContext(),
                EvidenceDAO.class
        );
    }

    // DISPLAY ALL CHAIN OF CUSTODY RECORDS
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.setAttribute(
                "custodyList",
                custodyDAO.getAllCustodyRecords()
        );

        request.getRequestDispatcher("custody.jsp")
                .forward(request, response);
    }

    // ADD NEW CHAIN OF CUSTODY RECORD
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {

            String evidenceIdValue =
                    request.getParameter("evidenceId");

            String actionTaken =
                    request.getParameter("actionTaken");

            // VALIDATE INPUT
            if (evidenceIdValue == null
                    || actionTaken == null
                    || evidenceIdValue.trim().isEmpty()
                    || actionTaken.trim().isEmpty()) {

                request.setAttribute(
                        "error",
                        "Please enter Evidence ID and Action Taken."
                );

                request.getRequestDispatcher("addCustody.jsp")
                        .forward(request, response);
                return;
            }

            int evidenceId =
                    Integer.parseInt(evidenceIdValue.trim());

            actionTaken = actionTaken.trim();

            if (evidenceId <= 0) {

                request.setAttribute(
                        "error",
                        "Please enter a valid Evidence ID."
                );

                request.getRequestDispatcher("addCustody.jsp")
                        .forward(request, response);
                return;
            }

            // CHECK WHETHER EVIDENCE EXISTS
            if (evidenceDAO.getEvidenceById(evidenceId) == null) {

                request.setAttribute(
                        "error",
                        "The entered Evidence ID does not exist."
                );

                request.getRequestDispatcher("addCustody.jsp")
                        .forward(request, response);
                return;
            }

            // GET CURRENT INVESTIGATOR
            String handledBy =
                    session.getAttribute("userId").toString();

            // CREATE CUSTODY RECORD
            ChainOfCustody custody = new ChainOfCustody(
                    evidenceId,
                    handledBy,
                    actionTaken,
                    LocalDateTime.now()
            );

            custodyDAO.createCustody(custody);

            session.setAttribute(
                    "custodyMessage",
                    "Chain of Custody record submitted."
            );

            response.sendRedirect("custody");

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Please enter a valid Evidence ID."
            );

            request.getRequestDispatcher("addCustody.jsp")
                    .forward(request, response);
        }
    }
}


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.forensictrack.forensictrackhibernate.entity.CaseNote"%>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<CaseNote> noteList =
        (List<CaseNote>) request.getAttribute("noteList");

    String message =
        (String) session.getAttribute("caseNoteMessage");

    session.removeAttribute("caseNoteMessage");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Case Notes - ForensicTrack</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f5f9;
            padding: 30px;
        }

        .container {
            max-width: 1100px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        h1 { color: #172b4d; }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background: #172b4d;
            color: white;
        }

        tr:nth-child(even) {
            background: #f5f7fa;
        }

        .button {
            display: inline-block;
            padding: 10px 15px;
            background: #1769aa;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin: 5px 3px;
        }

        .message {
            background: #e4f6e9;
            color: #176b35;
            padding: 10px;
            border-radius: 5px;
        }

        .empty {
            text-align: center;
            padding: 20px;
            color: #666;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Case Notes Management</h1>

    <p>Record and review investigation observations and case-related notes.</p>

    <% if (message != null) { %>
        <p class="message"><%= message %></p>
    <% } %>

    <a href="addCaseNote.jsp" class="button">+ Add Case Note</a>
    <a href="dashboard.jsp" class="button">Dashboard</a>
    <a href="logout" class="button">Logout</a>

    <table>
        <tr>
            <th>Note ID</th>
            <th>Case ID</th>
            <th>Investigator ID</th>
            <th>Note</th>
            <th>Note Date</th>
            <th>Actions</th>
        </tr>

        <% if (noteList != null && !noteList.isEmpty()) {
            for (CaseNote note : noteList) { %>

            <tr>
                <td><%= note.getNoteId() %></td>
                <td><%= note.getCaseId() %></td>
                <td><%= note.getInvestigatorId() %></td>
                <td><%= note.getNote() %></td>
                <td><%= note.getNoteDate() %></td>

<td>
    <a href="editCaseNote?id=<%= note.getNoteId() %>"
       class="button">Edit</a>

    <form action="deleteCaseNote" method="post"
          style="display:inline;"
          onsubmit="return confirm('Are you sure you want to delete this note?');">

        <input type="hidden" name="noteId"
               value="<%= note.getNoteId() %>">

        <button type="submit"
                style="background:#dc3545;color:white;padding:10px 15px;border:none;border-radius:5px;cursor:pointer;">
            Delete
        </button>
    </form>
</td>
            </tr>

        <%  }
           } else { %>

            <tr>
                <td colspan="6" class="empty">
                    No case notes found.
                </td>
            </tr>

        <% } %>
    </table>

</div>

</body>
</html>

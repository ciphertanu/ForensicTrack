
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.forensictrack.forensictrackhibernate.entity.CaseNote"%>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    CaseNote note = (CaseNote) request.getAttribute("caseNote");

    if (note == null) {
        response.sendRedirect("caseNotes");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Case Note - ForensicTrack</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f5f9;
            padding: 30px;
        }

        .container {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
        }

        h1 { color: #172b4d; }

        label {
            display: block;
            margin-top: 18px;
            font-weight: bold;
        }

        textarea {
            width: 100%;
            height: 140px;
            padding: 11px;
            margin-top: 7px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            margin-top: 20px;
            padding: 12px 20px;
            background: #1769aa;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        a { color: #1769aa; }
    </style>
</head>

<body>

<div class="container">

    <h1>Edit Case Note</h1>

    <p>Note ID: <strong><%= note.getNoteId() %></strong></p>
    <p>Case ID: <strong><%= note.getCaseId() %></strong></p>

    <form action="editCaseNote" method="post">

        <input type="hidden" name="noteId"
               value="<%= note.getNoteId() %>">

        <label>Investigation Note</label>

        <textarea name="note" required><%= note.getNote() %></textarea>

        <button type="submit">Update Note</button>

    </form>

    <p><a href="caseNotes">← Back to Case Notes</a></p>

</div>

</body>
</html>

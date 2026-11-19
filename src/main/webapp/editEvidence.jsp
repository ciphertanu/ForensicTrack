
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.forensictrack.forensictrackhibernate.entity.Evidence"%>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Evidence evidence =
        (Evidence) request.getAttribute("evidence");

    if (evidence == null) {
        response.sendRedirect("evidence");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Evidence - ForensicTrack</title>

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

        input, textarea, select {
            width: 100%;
            padding: 11px;
            margin-top: 7px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        textarea { height: 110px; }

        button {
            margin-top: 22px;
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

    <h1>Edit Evidence</h1>

    <p>Evidence ID: <strong><%= evidence.getEvidenceId() %></strong></p>
    <p>Case ID: <strong><%= evidence.getCaseId() %></strong></p>

    <form action="editEvidence" method="post">

        <input type="hidden" name="evidenceId"
               value="<%= evidence.getEvidenceId() %>">

        <label>Evidence Type</label>
        <input type="text" name="evidenceType"
               value="<%= evidence.getEvidenceType() %>"
               required>

        <label>Description</label>
        <textarea name="description"
                  required><%= evidence.getDescription() %></textarea>

        <label>File Path</label>
        <input type="text" name="filePath"
               value="<%= evidence.getFilePath() %>"
               required>

        <button type="submit">Update Evidence</button>

    </form>

    <p><a href="evidence">← Back to Evidence Management</a></p>

</div>

</body>
</html>

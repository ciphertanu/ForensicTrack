<%@page import="java.util.List"%>
<%@page import="com.forensictrack.forensictrackhibernate.entity.Evidence"%>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Evidence> evidenceList =
        (List<Evidence>) request.getAttribute("evidenceList");

    String message = (String) session.getAttribute("evidenceMessage");
    session.removeAttribute("evidenceMessage");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Evidence Management - ForensicTrack</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f5f9;
            margin: 0;
            padding: 30px;
        }
        .container {
            max-width: 1150px;
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
        tr:nth-child(even) { background: #f5f7fa; }
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

    <h1>Evidence Management</h1>
    <p>View and manage digital evidence linked to investigation cases.</p>

    <% if (message != null) { %>
        <p class="message"><%= message %></p>
    <% } %>

    <a href="addEvidence.jsp" class="button">+ Add New Evidence</a>
    <a href="dashboard.jsp" class="button">Dashboard</a>
    <a href="logout" class="button">Logout</a>

    <table>
        <tr>
            <th>Evidence ID</th>
            <th>Case ID</th>
            <th>Evidence Type</th>
            <th>Description</th>
            <th>File Path</th>
           <th>Collected Date</th>
<th>Actions</th>
        </tr>

        <% if (evidenceList != null && !evidenceList.isEmpty()) {
            for (Evidence evidence : evidenceList) { %>

            <tr>
                <td><%= evidence.getEvidenceId() %></td>
                <td><%= evidence.getCaseId() %></td>
                <td><%= evidence.getEvidenceType() %></td>
                <td><%= evidence.getDescription() %></td>
                <td><%= evidence.getFilePath() %></td>
                <td><%= evidence.getCollectedDate() %></td>
               <td>
    <a href="editEvidence?id=<%= evidence.getEvidenceId() %>"
       class="button">Edit</a>

    <form action="deleteEvidence" method="post"
          style="display:inline;"
          onsubmit="return confirm('Are you sure you want to delete this evidence?');">

        <input type="hidden" name="evidenceId"
               value="<%= evidence.getEvidenceId() %>">

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
                <td colspan="7" class="empty">No evidence records found.</td>
            </tr>
        <% } %>

    </table>

</div>

</body>
</html>
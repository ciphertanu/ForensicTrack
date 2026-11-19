
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.forensictrack.forensictrackhibernate.entity.ChainOfCustody"%>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<ChainOfCustody> custodyList =
        (List<ChainOfCustody>) request.getAttribute("custodyList");

    String message =
        (String) session.getAttribute("custodyMessage");

    session.removeAttribute("custodyMessage");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chain of Custody - ForensicTrack</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f5f9;
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

    <h1>Chain of Custody Management</h1>

    <p>
        Track evidence handling activities and maintain
        investigation custody history.
    </p>

    <% if (message != null) { %>
        <p class="message"><%= message %></p>
    <% } %>

    <a href="addCustody.jsp" class="button">+ Add Custody Record</a>
    <a href="dashboard.jsp" class="button">Dashboard</a>
    <a href="logout" class="button">Logout</a>

    <table>
        <tr>
            <th>Custody ID</th>
            <th>Evidence ID</th>
            <th>Handled By</th>
            <th>Action Taken</th>
            <th>Action Date</th>
        </tr>

        <% if (custodyList != null && !custodyList.isEmpty()) {
            for (ChainOfCustody record : custodyList) { %>

            <tr>
                <td><%= record.getCustodyId() %></td>
                <td><%= record.getEvidenceId() %></td>
                <td><%= record.getHandledBy() %></td>
                <td><%= record.getActionTaken() %></td>
                <td><%= record.getActionDate() %></td>
            </tr>

        <%  }
           } else { %>

            <tr>
                <td colspan="5" class="empty">
                    No custody records found.
                </td>
            </tr>

        <% } %>
    </table>

</div>

</body>
</html>

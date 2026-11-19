
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.forensictrack.forensictrackhibernate.entity.Case" %>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Case caseObj = (Case) request.getAttribute("caseObj");

    if (caseObj == null) {
        response.sendRedirect("cases");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ForensicTrack - Edit Case</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #eef2f7;
            padding: 30px;
        }

        .container {
            max-width: 550px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            color: #17365d;
        }

        label {
            display: block;
            margin-top: 20px;
            font-weight: bold;
            color: #344054;
        }

        input, select {
            width: 100%;
            padding: 12px;
            margin-top: 8px;
            box-sizing: border-box;
            border: 1px solid #ccd3dd;
            border-radius: 6px;
        }

        button {
            width: 100%;
            padding: 13px;
            margin-top: 25px;
            background: #17365d;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background: #24558a;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #24558a;
            text-decoration: none;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Edit Investigation Case</h1>

    <p>Case ID: <%= caseObj.getCaseId() %></p>

    <p>Case Title: <%= caseObj.getCaseTitle() %></p>

    <form action="editCase" method="post">

        <input type="hidden"
               name="caseId"
               value="<%= caseObj.getCaseId() %>">

        <label for="priority">Priority</label>

        <select id="priority" name="priority" required>
            <option value="Low"
                <%= "Low".equals(caseObj.getPriority()) ? "selected" : "" %>>
                Low
            </option>

            <option value="Medium"
                <%= "Medium".equals(caseObj.getPriority()) ? "selected" : "" %>>
                Medium
            </option>

            <option value="High"
                <%= "High".equals(caseObj.getPriority()) ? "selected" : "" %>>
                High
            </option>

            <option value="Critical"
                <%= "Critical".equals(caseObj.getPriority()) ? "selected" : "" %>>
                Critical
            </option>
        </select>

        <label for="status">Status</label>

        <select id="status" name="status" required>
            <option value="Open"
                <%= "Open".equals(caseObj.getStatus()) ? "selected" : "" %>>
                Open
            </option>

            <option value="Under Investigation"
                <%= "Under Investigation".equals(caseObj.getStatus()) ? "selected" : "" %>>
                Under Investigation
            </option>

            <option value="Closed"
                <%= "Closed".equals(caseObj.getStatus()) ? "selected" : "" %>>
                Closed
            </option>
        </select>

        <button type="submit">Update Case</button>

    </form>

    <a class="back-link" href="cases">Back to Case Management</a>

</div>

</body>
</html>

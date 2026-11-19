
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ForensicTrack - Add Case</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #eef2f7;
            padding: 30px;
        }

        .container {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }

        h1 {
            color: #17365d;
            text-align: center;
        }

        label {
            display: block;
            margin-top: 18px;
            font-weight: bold;
            color: #344054;
        }

        input, select, textarea {
            width: 100%;
            padding: 12px;
            margin-top: 7px;
            box-sizing: border-box;
            border: 1px solid #ccd3dd;
            border-radius: 6px;
        }

        textarea {
            min-height: 100px;
            resize: vertical;
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

    <h1>Add New Investigation Case</h1>

    <form action="cases" method="post">

        <label for="caseTitle">Case Title</label>
        <input type="text"
               id="caseTitle"
               name="caseTitle"
               maxlength="150"
               required>

        <label for="caseType">Case Type</label>
        <select id="caseType" name="caseType" required>
            <option value="">Select Case Type</option>
            <option value="Unauthorized Access">Unauthorized Access</option>
            <option value="Network Intrusion">Network Intrusion</option>
            <option value="Data Theft">Data Theft</option>
            <option value="Malware Investigation">Malware Investigation</option>
            <option value="Digital Fraud">Digital Fraud</option>
            <option value="Other">Other</option>
        </select>

        <label for="description">Description</label>
        <textarea id="description"
                  name="description"
                  maxlength="2000"
                  required></textarea>

        <label for="priority">Priority</label>
        <select id="priority" name="priority" required>
            <option value="">Select Priority</option>
            <option value="Low">Low</option>
            <option value="Medium">Medium</option>
            <option value="High">High</option>
            <option value="Critical">Critical</option>
        </select>

        <label for="status">Status</label>
        <select id="status" name="status" required>
            <option value="Open">Open</option>
            <option value="Under Investigation">Under Investigation</option>
            <option value="Closed">Closed</option>
        </select>

        <button type="submit">Create Case</button>

    </form>

    <a class="back-link" href="cases">Back to Case Management</a>

</div>

</body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Evidence - ForensicTrack</title>
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
            margin-top: 15px;
            font-weight: bold;
        }
        input, textarea, select {
            width: 100%;
            padding: 10px;
            margin-top: 7px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        textarea { height: 100px; }
        button {
            margin-top: 20px;
            padding: 12px 20px;
            background: #1769aa;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .error {
            color: #b42318;
            background: #feeceb;
            padding: 10px;
            border-radius: 5px;
        }
        a { color: #1769aa; }
    </style>
</head>
<body>

<div class="container">

    <h1>Add New Evidence</h1>
    <p>Enter the evidence details below.</p>

    <% if (error != null) { %>
        <p class="error"><%= error %></p>
    <% } %>

    <form action="evidence" method="post">

        <label>Case ID</label>
        <input type="number" name="caseId" min="1" required>

        <label>Evidence Type</label>
        <select name="evidenceType" required>
            <option value="">Select Evidence Type</option>
            <option value="Email">Email</option>
            <option value="Document">Document</option>
            <option value="Image">Image</option>
            <option value="Audio">Audio</option>
            <option value="Video">Video</option>
            <option value="Log File">Log File</option>
            <option value="Other">Other</option>
        </select>

        <label>Description</label>
        <textarea name="description"
                  placeholder="Enter evidence description"
                  required></textarea>

        <label>File Path</label>
        <input type="text" name="filePath"
               placeholder="Enter evidence file path"
               required>

        <button type="submit">Save Evidence</button>

    </form>

    <p><a href="evidence">← Back to Evidence Management</a></p>

</div>

</body>
</html>
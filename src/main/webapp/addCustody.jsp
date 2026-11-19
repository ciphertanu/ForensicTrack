
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
    <meta charset="UTF-8">
    <title>Add Custody Record - ForensicTrack</title>

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

        input, textarea {
            width: 100%;
            padding: 11px;
            margin-top: 7px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        textarea { height: 120px; }

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

    <h1>Add Chain of Custody Record</h1>

    <p>Record a new evidence handling activity.</p>

    <% if (error != null) { %>
        <p class="error"><%= error %></p>
    <% } %>

    <form action="custody" method="post">

        <label>Evidence ID</label>
        <input type="number" name="evidenceId"
               min="1" required>

        <label>Action Taken</label>
        <textarea name="actionTaken"
                  placeholder="Describe the evidence handling activity..."
                  required></textarea>

        <button type="submit">Save Custody Record</button>

    </form>

    <p><a href="custody">← Back to Chain of Custody</a></p>

</div>

</body>
</html>

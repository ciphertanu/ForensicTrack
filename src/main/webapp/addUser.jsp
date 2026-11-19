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
    <title>Add User | ForensicTrack</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f3f6fa;
            margin: 0;
            padding: 30px;
        }
        .container {
            max-width: 520px;
            margin: 30px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 3px 12px rgba(0,0,0,0.1);
        }
        h2 { color: #17365d; text-align: center; }
        label { display: block; margin-top: 15px; font-weight: bold; }
        input, select {
            width: 100%;
            padding: 11px;
            margin-top: 6px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            width: 100%;
            margin-top: 22px;
            padding: 12px;
            background: #17365d;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .error { color: #c62828; text-align: center; }
        a { color: #17365d; }
    </style>
</head>
<body>
<div class="container">
    <h2>Add New User</h2>

    <% if (error != null) { %>
        <p class="error"><%= error %></p>
    <% } %>

    <form action="users" method="post">
        <label>Full Name</label>
        <input type="text" name="fullName" required>

        <label>Email</label>
        <input type="email" name="email" required>

        <label>Username</label>
        <input type="text" name="username" required>

        <label>Password</label>
        <input type="password" name="password" minlength="8" required>

        <label>Role</label>
        <select name="role" required>
            <option value="Investigator">Investigator</option>
            <option value="Admin">Admin</option>
        </select>

        <button type="submit">Create User</button>
    </form>

    <p><a href="users">← Back to User Management</a></p>
</div>
</body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.forensictrack.forensictrackhibernate.entity.User"%>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    User user = (User) request.getAttribute("editUser");
    String error = (String) request.getAttribute("error");

    if (user == null) {
        response.sendRedirect("users");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User | ForensicTrack</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f3f6fa;
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

        h2 {
            text-align: center;
            color: #17365d;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

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

        .error {
            color: #c62828;
            text-align: center;
        }

        a {
            color: #17365d;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Edit User Details</h2>

    <% if (error != null) { %>
        <p class="error"><%= error %></p>
    <% } %>

    <form action="editUser" method="post">

        <input type="hidden"
               name="userId"
               value="<%= user.getUserId() %>">

        <label>Full Name</label>
        <input type="text"
               name="fullName"
               value="<%= user.getFullName() %>"
               required>

        <label>Email</label>
        <input type="email"
               name="email"
               value="<%= user.getEmail() %>"
               required>

        <label>Username</label>
        <input type="text"
               value="<%= user.getUsername() %>"
               readonly>

        <label>Role</label>
        <select name="role" required>
            <option value="Investigator"
                <%= "Investigator".equals(user.getRole()) ? "selected" : "" %>>
                Investigator
            </option>

            <option value="Admin"
                <%= "Admin".equals(user.getRole()) ? "selected" : "" %>>
                Admin
            </option>
        </select>

        <button type="submit">Update User</button>

    </form>

    <p><a href="users">← Back to User Management</a></p>

</div>

</body>
</html>

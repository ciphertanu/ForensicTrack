
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ForensicTrack - Login</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #101827, #1e3a5f);
        }

        .login-container {
            width: 380px;
            padding: 35px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.3);
        }

        h1 {
            margin-bottom: 8px;
            text-align: center;
            color: #183153;
        }

        .subtitle {
            margin-bottom: 25px;
            text-align: center;
            color: #64748b;
            font-size: 14px;
        }

        label {
            display: block;
            margin-bottom: 7px;
            color: #263445;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-bottom: 18px;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            font-size: 15px;
        }

        input:focus {
            outline: none;
            border-color: #2563eb;
        }

        button {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 6px;
            background: #1d4ed8;
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
        }

        button:hover {
            background: #1e40af;
        }

        .message {
            margin: 10px 0 16px;
            font-size: 14px;
            text-align: center;
        }

        .register-link {
            margin-top: 20px;
            text-align: center;
            font-size: 14px;
        }

        .register-link a {
            color: #1d4ed8;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>

<body>

<div class="login-container">

    <h1>ForensicTrack</h1>

    <p class="subtitle">
        Digital Forensics Case and Evidence Management System
    </p>

    <% if (request.getAttribute("success") != null) { %>
        <p class="message" style="color: green;">
            <%= request.getAttribute("success") %>
        </p>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
        <p class="message" style="color: red;">
            <%= request.getAttribute("error") %>
        </p>
    <% } %>

    <form action="login" method="post">

        <label for="username">Username</label>
        <input
            type="text"
            id="username"
            name="username"
            placeholder="Enter your username"
            required
        >

        <label for="password">Password</label>
        <input
            type="password"
            id="password"
            name="password"
            placeholder="Enter your password"
            required
        >

        <button type="submit">Login</button>

    </form>

    <div class="register-link">
        Don't have an account?
        <a href="register.jsp">Register here</a>
    </div>

</div>

</body>
</html>

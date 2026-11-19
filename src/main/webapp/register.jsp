
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ForensicTrack - Registration</title>

    <style>
        body {
            margin: 0;
            padding: 40px 15px;
            font-family: Arial, sans-serif;
            background: #eef2f7;
        }

        .register-box {
            background: white;
            width: 380px;
            max-width: 90%;
            margin: 30px auto;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }

        h1, h2, p {
            text-align: center;
        }

        h1 { color: #17365d; }
        h2 { color: #26364b; }
        p { color: #667085; }

        label {
            display: block;
            margin-top: 18px;
            font-weight: bold;
            color: #344054;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-top: 7px;
            border: 1px solid #ccd3dd;
            border-radius: 6px;
            box-sizing: border-box;
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

        button:hover { background: #24558a; }

        .error-message {
            color: #d92d20;
            background: #fef3f2;
            padding: 10px;
            border-radius: 6px;
            font-size: 14px;
        }

        .success-message {
            color: #067647;
            background: #ecfdf3;
            padding: 12px;
            border-radius: 6px;
            font-size: 14px;
        }

        .login-link {
            margin-top: 20px;
            text-align: center;
        }

        a {
            color: #24558a;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>

<body>
    <div class="register-box">
        <h1>ForensicTrack</h1>
        <h2>Create Account</h2>
        <p>Register for the Digital Forensics Management System</p>

        <% if (request.getAttribute("success") != null) { %>
            <p class="success-message">
                <%= request.getAttribute("success") %>
            </p>
        <% } %>

        <% if (request.getAttribute("error") != null) { %>
            <p class="error-message">
                <%= request.getAttribute("error") %>
            </p>
        <% } %>

        <form action="${pageContext.request.contextPath}/register" method="post">

            <label for="fullName">Full Name</label>
            <input type="text" id="fullName" name="fullName"
                   placeholder="Enter your full name"
                   required maxlength="100">

            <label for="email">Email Address</label>
            <input type="email" id="email" name="email"
                   placeholder="Enter your email"
                   required maxlength="150">

            <label for="username">Username</label>
            <input type="text" id="username" name="username"
                   placeholder="Choose a username"
                   required maxlength="50">

            <label for="password">Password</label>
            <input type="password" id="password" name="password"
                   placeholder="Create a password"
                   required minlength="8">

            <button type="submit">Create Account</button>
        </form>

        <div class="login-link">
            Already have an account?
            <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
        </div>
    </div>
</body>
</html>

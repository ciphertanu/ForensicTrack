
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String username = (String) session.getAttribute("username");
    String fullName = (String) session.getAttribute("fullName");
    String role = (String) session.getAttribute("role");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - ForensicTrack</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f2f5f9;
            color: #263238;
        }

        .header {
            background: #172b4d;
            color: white;
            padding: 22px 35px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
        }

        .header h1 {
            margin: 0;
            font-size: 25px;
        }

        .header p {
            margin: 7px 0 0;
            color: #d5deeb;
        }

        .logout {
            background: #e74c3c;
            color: white;
            padding: 11px 18px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }

        .logout:hover {
            background: #c0392b;
        }

        .container {
            max-width: 1150px;
            margin: 35px auto;
            padding: 0 20px;
        }

        .welcome {
            margin-bottom: 25px;
        }

        .welcome h2 {
            color: #172b4d;
            margin-bottom: 8px;
        }

        .welcome p {
            color: #667085;
        }

        .grid {
            display: grid;
            grid-template-columns: repeat(2, minmax(0, 1fr));
            gap: 22px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 3px 12px rgba(0,0,0,0.07);
            border-top: 4px solid #1769aa;
        }

        .card h3 {
            margin-top: 0;
            color: #172b4d;
        }

        .card p {
            color: #667085;
            line-height: 1.5;
            min-height: 45px;
        }

        .card a {
            display: inline-block;
            background: #1769aa;
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 5px;
            margin-top: 10px;
        }

        .card a:hover {
            background: #0f4f83;
        }

        .footer {
            text-align: center;
            color: #7b8794;
            padding: 25px;
            margin-top: 30px;
        }

        @media (max-width: 650px) {
            .grid {
                grid-template-columns: 1fr;
            }

            .header {
                gap: 18px;
                padding: 20px;
            }
        }
    </style>
</head>

<body>

    <!-- HEADER -->
    <div class="header">
        <div>
            <h1>ForensicTrack</h1>
            <p>Digital Forensics Case and Evidence Management System</p>
        </div>

        <a href="logout" class="logout">Logout</a>
    </div>

    <!-- MAIN DASHBOARD -->
    <div class="container">

        <div class="welcome">
            <h2>Welcome to ForensicTrack!</h2>

            <p>
                Logged in as:
                <strong><%= fullName != null ? fullName : username %></strong>
                |
                Role:
                <strong><%= role != null ? role : "Investigator" %></strong>
            </p>

            <p>
                Manage investigation cases, digital evidence,
                forensic notes and chain of custody records.
            </p>
        </div>

        <div class="grid">

            <!-- CASE MANAGEMENT -->
            <div class="card">
                <h3>Case Management</h3>
                <p>Create, view, update and manage digital forensic investigation cases.</p>
                <a href="cases">Manage Cases</a>
            </div>

            <!-- EVIDENCE MANAGEMENT -->
            <div class="card">
                <h3>Evidence Management</h3>
                <p>Register, view and manage digital evidence associated with investigation cases.</p>
                <a href="evidence">Manage Evidence</a>
            </div>

            <!-- CASE NOTES -->
            <div class="card">
                <h3>Case Notes</h3>
                <p>Record investigation observations and important case-related notes.</p>
                <a href="caseNotes">Manage Case Notes</a>
            </div>

            <!-- CHAIN OF CUSTODY -->
            <div class="card">
                <h3>Chain of Custody</h3>
                <p>Track evidence handling activities and maintain custody history.</p>
                <a href="custody">View Chain of Custody</a>
            </div>

            <!-- USER MANAGEMENT -->
            <div class="card">
                <h3>User Management</h3>
                <p>Manage investigator accounts and user information.</p>
                <a href="users">Manage Users</a>
            </div>

            <!-- INVESTIGATION OVERVIEW -->
            <div class="card">
                <h3>Investigation Overview</h3>
                <p>Access the investigation workspace and review case information.</p>
                <a href="overview">View Investigations</a>
            </div>

        </div>

    </div>

    <!-- FOOTER -->
    <div class="footer">
        ForensicTrack | Digital Forensics Management System
    </div>

</body>
</html>

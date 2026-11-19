
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

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
    <title>Investigation Overview | ForensicTrack</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f3f6fa;
            margin: 0;
            padding: 30px;
            color: #263238;
        }

        .container {
            max-width: 1100px;
            margin: auto;
        }

        h1 {
            color: #17365d;
            text-align: center;
        }

        .subtitle {
            text-align: center;
            color: #607080;
            margin-bottom: 30px;
        }

        .cards {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 3px 12px rgba(0,0,0,0.08);
            text-align: center;
        }

        .card h3 {
            color: #607080;
        }

        .number {
            font-size: 34px;
            font-weight: bold;
            color: #17365d;
        }

        .section {
            background: white;
            margin-top: 25px;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 3px 12px rgba(0,0,0,0.08);
        }

        .section h2 {
            color: #17365d;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background: #17365d;
            color: white;
        }

        tr:nth-child(even) {
            background: #f3f6fa;
        }

        .navigation {
            text-align: center;
            margin-top: 30px;
        }

        .navigation a {
            display: inline-block;
            margin: 8px;
            padding: 11px 18px;
            background: #17365d;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        @media(max-width: 650px) {
            .cards {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Investigation Overview</h1>

    <p class="subtitle">
        Digital Forensics Case and Evidence Management Dashboard
    </p>

    <!-- Statistics using Expression Language -->

    <div class="cards">

        <div class="card">
            <h3>Total Cases</h3>
            <div class="number">
                <c:out value="${empty totalCases ? 0 : totalCases}"/>
            </div>
        </div>

        <div class="card">
            <h3>Open Cases</h3>
            <div class="number">
                <c:out value="${empty openCases ? 0 : openCases}"/>
            </div>
        </div>

        <div class="card">
            <h3>Total Evidence</h3>
            <div class="number">
                <c:out value="${empty totalEvidence ? 0 : totalEvidence}"/>
            </div>
        </div>

        <div class="card">
            <h3>Chain of Custody Records</h3>
            <div class="number">
                <c:out value="${empty totalCustodyRecords ? 0 : totalCustodyRecords}"/>
            </div>
        </div>

    </div>

    <!-- Cases by Status using JSTL -->

    <div class="section">
        <h2>Cases by Status</h2>

        <table>
            <tr>
                <th>Case Status</th>
                <th>Number of Cases</th>
            </tr>

            <c:choose>
                <c:when test="${not empty casesByStatus}">
                    <c:forEach var="entry" items="${casesByStatus}">
                        <tr>
                            <td><c:out value="${entry.key}"/></td>
                            <td><c:out value="${entry.value}"/></td>
                        </tr>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <tr>
                        <td colspan="2">No case records available.</td>
                    </tr>
                </c:otherwise>
            </c:choose>

        </table>
    </div>

    <!-- Cases by Priority using JSTL -->

    <div class="section">
        <h2>Cases by Priority</h2>

        <table>
            <tr>
                <th>Priority</th>
                <th>Number of Cases</th>
            </tr>

            <c:choose>
                <c:when test="${not empty casesByPriority}">
                    <c:forEach var="entry" items="${casesByPriority}">
                        <tr>
                            <td><c:out value="${entry.key}"/></td>
                            <td><c:out value="${entry.value}"/></td>
                        </tr>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <tr>
                        <td colspan="2">No case records available.</td>
                    </tr>
                </c:otherwise>
            </c:choose>

        </table>
    </div>

    <div class="navigation">
        <a href="dashboard.jsp">Dashboard</a>
        <a href="cases">Manage Cases</a>
        <a href="evidence">Manage Evidence</a>
        <a href="custody">Chain of Custody</a>
        <a href="logout">Logout</a>
    </div>

</div>

</body>
</html>

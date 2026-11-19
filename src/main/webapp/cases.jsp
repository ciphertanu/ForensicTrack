
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String caseMessage =
            (String) session.getAttribute("caseMessage");

    session.removeAttribute("caseMessage");

    request.setAttribute("displayMessage", caseMessage);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>ForensicTrack - Case Management</title>

    <style>
        body {
            margin: 0;
            padding: 30px;
            font-family: Arial, sans-serif;
            background: #eef2f7;
            color: #263445;
        }

        .container {
            max-width: 1200px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
        }

        h1 {
            color: #17365d;
            margin-bottom: 8px;
        }

        .description {
            color: #667085;
        }

        .top-links {
            margin: 25px 0;
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }

        .top-links a {
            display: inline-block;
            padding: 10px 16px;
            background: #17365d;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }

        .top-links a:hover {
            background: #24558a;
        }

        .add-button {
            background: #15803d !important;
        }

        .add-button:hover {
            background: #166534 !important;
        }

        .table-wrapper {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            min-width: 850px;
        }

        th, td {
            padding: 13px;
            text-align: left;
            border-bottom: 1px solid #e2e8f0;
        }

        th {
            background: #17365d;
            color: white;
        }

        tr:hover {
            background: #f1f5f9;
        }

        .edit-button {
            display: inline-block;
            background: #2563eb;
            color: white;
            padding: 7px 12px;
            border-radius: 5px;
            text-decoration: none;
            margin-right: 5px;
        }

        .delete-button {
            background: #dc2626;
            color: white;
            border: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
        }

        .delete-button:hover {
            background: #b91c1c;
        }

        .empty-message {
            padding: 25px;
            text-align: center;
            color: #667085;
            background: #f8fafc;
            border-radius: 8px;
        }

        .logout-link {
            background: #dc2626 !important;
        }

        .message {
            padding: 12px;
            margin: 15px 0;
            background: #e8f5e9;
            color: #166534;
            border-radius: 6px;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Case Management</h1>

    <p class="description">
        Manage digital forensic investigation cases.
    </p>

    <!-- Session message using JSTL and EL -->

    <c:if test="${not empty displayMessage}">
        <div class="message">
            <c:out value="${displayMessage}"/>
        </div>
    </c:if>

    <div class="top-links">

        <a href="dashboard.jsp">Back to Dashboard</a>

        <a href="addCase.jsp" class="add-button">
            + Add New Case
        </a>

        <a href="logout" class="logout-link">
            Logout
        </a>

    </div>

    <h2>Investigation Cases</h2>

    <!-- Case records using JSTL -->

    <c:choose>

        <c:when test="${not empty cases}">

            <div class="table-wrapper">

                <table>

                    <thead>
                        <tr>
                            <th>Case ID</th>
                            <th>Case Title</th>
                            <th>Case Type</th>
                            <th>Priority</th>
                            <th>Status</th>
                            <th>Created Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>

                    <tbody>

                        <c:forEach var="caseObj" items="${cases}">

                            <tr>

                                <td>
                                    <c:out value="${caseObj.caseId}"/>
                                </td>

                                <td>
                                    <c:out value="${caseObj.caseTitle}"/>
                                </td>

                                <td>
                                    <c:out value="${caseObj.caseType}"/>
                                </td>

                                <td>
                                    <c:out value="${caseObj.priority}"/>
                                </td>

                                <td>
                                    <c:out value="${caseObj.status}"/>
                                </td>

                                <td>
                                    <c:out value="${caseObj.createdDate}"/>
                                </td>

                                <td>

                                    <a class="edit-button"
                                       href="editCase?id=${caseObj.caseId}">
                                        Edit
                                    </a>

                                    <form action="deleteCase"
                                          method="post"
                                          onsubmit="return confirm('Are you sure you want to delete this case?');"
                                          style="display:inline;">

                                        <input type="hidden"
                                               name="caseId"
                                               value="${caseObj.caseId}">

                                        <button type="submit"
                                                class="delete-button">
                                            Delete
                                        </button>

                                    </form>

                                </td>

                            </tr>

                        </c:forEach>

                    </tbody>

                </table>

            </div>

        </c:when>

        <c:otherwise>

            <div class="empty-message">
                No investigation cases found.
            </div>

        </c:otherwise>

    </c:choose>

</div>

</body>
</html>

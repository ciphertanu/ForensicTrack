
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%
    if (session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String message = (String) session.getAttribute("message");
    session.removeAttribute("message");
    request.setAttribute("displayMessage", message);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management | ForensicTrack</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f3f6fa;
            margin: 0;
            padding: 30px;
        }

        .container {
            max-width: 1150px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 3px 12px rgba(0,0,0,0.1);
        }

        h2 {
            color: #17365d;
            text-align: center;
        }

        .message {
            color: green;
            text-align: center;
            font-weight: bold;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 25px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background: #17365d;
            color: white;
        }

        tr:nth-child(even) {
            background: #f3f6fa;
        }

        .btn {
            display: inline-block;
            padding: 8px 12px;
            margin: 3px;
            border-radius: 5px;
            text-decoration: none;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 13px;
        }

        .edit {
            background: #1976d2;
        }

        .delete {
            background: #c62828;
        }

        .add {
            background: #17365d;
        }

        .navigation {
            margin-top: 25px;
            text-align: center;
        }

        .navigation a {
            margin: 10px;
            color: #17365d;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>User Management</h2>

    <c:if test="${not empty displayMessage}">
        <p class="message">
            <c:out value="${displayMessage}"/>
        </p>
    </c:if>

    <c:if test="${sessionScope.role eq 'Admin'}">
        <p style="text-align:right;">
            <a class="btn add" href="addUser.jsp">
                + Add New User
            </a>
        </p>
    </c:if>

    <table>
        <tr>
            <th>User ID</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Username</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>

        <c:choose>

            <c:when test="${not empty userList}">

                <c:forEach var="user" items="${userList}">

                    <tr>
                        <td><c:out value="${user.userId}"/></td>
                        <td><c:out value="${user.fullName}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.role}"/></td>

                        <td>

                            <c:choose>

                                <c:when test="${sessionScope.role eq 'Admin'}">

                                    <a class="btn edit"
                                       href="editUser?userId=${user.userId}">
                                        Edit
                                    </a>

                                    <c:if test="${user.userId ne sessionScope.userId}">

                                        <form action="deleteUser"
                                              method="post"
                                              style="display:inline;"
                                              onsubmit="return confirm('Are you sure you want to delete this user?');">

                                            <input type="hidden"
                                                   name="userId"
                                                   value="<c:out value='${user.userId}'/>">

                                            <button type="submit"
                                                    class="btn delete">
                                                Delete
                                            </button>

                                        </form>

                                    </c:if>

                                </c:when>

                                <c:otherwise>
                                    View Only
                                </c:otherwise>

                            </c:choose>

                        </td>
                    </tr>

                </c:forEach>

            </c:when>

            <c:otherwise>
                <tr>
                    <td colspan="6">No users found.</td>
                </tr>
            </c:otherwise>

        </c:choose>

    </table>

    <div class="navigation">
        <a href="dashboard.jsp">← Dashboard</a>
        |
        <a href="logout">Logout</a>
    </div>

</div>

</body>
</html>

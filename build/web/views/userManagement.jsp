<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page import="model.*"%>
<%UserDAO u = new UserDAO();%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Panel</title>
        <link rel="icon" href="./resources/img/icon.png" type="image/gif" sizes="16x16">
        <link rel="icon" href="./resources/img/icon.png" type="image/gif" sizes="18x18">
        <link rel="icon" href="./resources/img/icon.png" type="image/gif" sizes="20x20">
        <style>
            /*            body {
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            min-height: 100vh;
                        }*/

            table {
                border-collapse: collapse;
                width: 80%;
                max-width: 800px;
                margin: 0 auto;
                margin-top : 30px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            th, td {
                padding: 10px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }

            tr:nth-child(even) {
                background-color: #f8f8f8;
            }

            tr:hover {
                background-color: #e2e2e2;
            }

            .function a {
                color: #000;
                text-decoration: none;
            }

            .function a:hover {
                text-decoration: underline;
            }

            h1 {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Admin Panel</h1>

        <jsp:include page="adminnavbar.jsp" />

        <%
            String xTable = "User";
            String[] cols = (String[]) u.getColNames(xTable);
        %>

        <table border="1">
            <tr>
                <% for (String s : cols) { %>
                <th><%= s %></th>
                    <% } %>
                <th></th>
                <th></th>
            </tr>
            <% for (User user : u.getUsers()) { %>
            <tr> 
                <% for (String infor : cols) { 
                    if (infor.equals("avatar")) { 
                %>
                <td> <img style="width:100%;" src="<%=u.getUserInformation(user.getUsername(), infor)%>" alt="avatar"/> </td>
                <% } else { %>
                <td><%= u.getUserInformation(user.getUsername(), infor) %></td>
                <% } }%>

            <form action="editUser" method="get">
                <input type="hidden" name="userId" value="<%=user.getId()%>">
                <td> <input type="submit" value="Edit" /> </td>
            </form>
            <form action="removeUser" method="post">
                <input type="hidden" name="userId" value="<%=user.getId()%>">
                <td> <input type="submit" value="Remove" /> </td>
            </form>

        </tr>
        <% } %>
    </table>
</body>
</html>

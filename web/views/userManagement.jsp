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

        <link rel="icon" href="./resources/img/logo.png" type="image/gif" sizes="16x16">
        <link rel="icon" href="./resources/img/logo.png" type="image/gif" sizes="18x18">
        <link rel="icon" href="./resources/img/logo.png" type="image/gif" sizes="20x20">
        <link rel="stylesheet" href="./resources/css/fontawesome.all.min.css">
    <link rel="stylesheet" href="./resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="./resources/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="./resources/css/animate.css">
    <link rel="stylesheet" href="./resources/css/magnific-popup.css">
    <link rel="stylesheet" href="./resources/css/normalize.css">
    <link rel="stylesheet" href="./resources/css/style.css">
    <link rel="stylesheet" href="./resources/css/responsive.css">
        <style>

            table {
                                border: 4px solid gray;

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

            .search-box {
                width: 100px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="adminnavbar.jsp" />

        <%
            String xTable = "User";
            String[] cols = (String[]) u.getColNames(xTable);
        %>

        <table id="myTable" border="1">
            <tr>
                <% for (String s : cols) { %>
                <th><%= s %></th>   
                    <% } %>
            <form action="insertUser" method="get">
                <th> <input type="submit" value="Insert" /></th>
            </form>
            <th>
                <input type="text" id="searchBox" class="search-box" placeholder="Search..." onkeyup="searchTable()">
            </th>
        </tr>
        <% for (User user : u.getUsers()) { %>
        <tr> 
            <% for (String infor : cols) { 
                if (infor.equals("avatar")) { 
            %>
            <td> <img style="width:100%;" src="<%=u.getUserInformation(user.getUsername(), infor)%>" alt="avatar"/> </td>
                <% } else { %>
            <td><%= u.getUserInformation(user.getUsername(), infor) %></td>
            <% } 
            }%>

        <form action="updateUser" method="get">
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
<script>
    function searchTable() {
        var input = document.getElementById("searchBox");
        var filter = input.value.toUpperCase();
        var table = document.getElementById("myTable");
        var rows = table.getElementsByTagName("tr");

        for (var i = 0; i < rows.length; i++) {
            var idColumn = rows[i].getElementsByTagName("td")[0];
            var nameColumn = rows[i].getElementsByTagName("td")[1];
            var avatarColumn = rows[i].getElementsByTagName("td")[2];
            var usernameColumn = rows[i].getElementsByTagName("td")[3];
            var passwordColumn = rows[i].getElementsByTagName("td")[4];
            var sexColumn = rows[i].getElementsByTagName("td")[5];
            var birthdayColumn = rows[i].getElementsByTagName("td")[6];
            var emailColumn = rows[i].getElementsByTagName("td")[7];
            var addressColumn = rows[i].getElementsByTagName("td")[8];
            var isSellerColumn = rows[i].getElementsByTagName("td")[9];
            var isAdminColumn = rows[i].getElementsByTagName("td")[10];
            var insertColumn = rows[i].getElementsByTagName("td")[11];
            var removeColumn = rows[i].getElementsByTagName("td")[12];

            if (idColumn || nameColumn || usernameColumn || emailColumn || addressColumn) {
                var idText = idColumn.textContent || idColumn.innerText;
                var nameText = nameColumn.textContent || nameColumn.innerText;
                var usernameText = usernameColumn.textContent || usernameColumn.innerText;
                var emailText = emailColumn.textContent || emailColumn.innerText;
                var addressText = addressColumn.textContent || addressColumn.innerText;

                if (idText.toUpperCase().indexOf(filter) > -1 || nameText.toUpperCase().indexOf(filter) > -1
                        || usernameText.toUpperCase().indexOf(filter) > -1 || emailText.toUpperCase().indexOf(filter) > -1
                        || addressText.toUpperCase().indexOf(filter) > -1) {
                    rows[i].style.display = "";
                } else {
                    rows[i].style.display = "none";
                }
            }
        }
    }
</script>
</body>
</html>

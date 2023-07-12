<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="model.*" %>
<%@page import="database.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Panel</title>

        <link rel="icon" href="./resources/img/logo.png" type="image/gif" sizes="16x16">
        <link rel="icon" href="./resources/img/logo.png" type="image/gif" sizes="18x18">
        <link rel="icon" href="./resources/img/logo.png" type="image/gif" sizes="20x20">

        <style>
            body {
                font-family: Arial, sans-serif;
                /*background-color: #f5f5f5;*/
                margin: 0;
                padding: 20px;
            }

            h1 {
                text-align: center;
                color: #333;
            }

            h2 {
                text-align: center;
                color: #333;
            }

            form {
                max-width: 400px;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .form-group {
                display: flex;
                align-items: center;
                justify-content: space-between;
                margin-bottom: 10px;
            }

            label {
                color: #333;
                font-weight: bold;
                margin-right: 10px;
            }

            input[type="text"] {
                flex: 1;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            input[type="Date"] {
                flex: 1;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            input[type="submit"] {
                display: block;
                width: 100%;
                padding: 8px;
                margin-top: 10px;
                background-color: #4caf50;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            button {
                display: block;
                margin: 10px auto;
                padding: 8px 16px;
                background-color: #ccc;
                color: #333;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            button:hover, input[type="submit"]:hover {
                background-color: #333;
                color: #fff;
            }
            .form-selected {
                flex: 1;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
        </style>
    </head>

    <body>
        <%
            CategoryDAO c = new CategoryDAO();
            Category category = (Category) request.getAttribute("category");
            String[] cols = (String[]) c.getColNames("Category");
            String error = (String) request.getAttribute("error");
            if (category == null) return;
        %>  

        <jsp:include page="adminnavbar.jsp" />

        <h2>Update a category</h2>
        <form action="editCategory" method="POST">
            <% for (String col : cols) {
            if (col.equals("category_id")) { %>
            <div class="form-group">
                <label><%=col%></label>
                <input type="text" name="<%=col%>" value="<%=category.getCategoryID()%>" readonly />
            </div>
            <% } else { %>
            <div class="form-group">
                <label><%=col%></label>
                <input type="text" name="<%=col%>" value="<%= c.getCategoryInformation(Integer.toString(category.getCategoryID()), col) %>"/>
            </div>
            <% }
        }
            %>

            <div class="error" style="color:red;">
                <p> <%=((error == null) ? "" : error)%>
            </div>
            <input type="submit" value="Update">
        </form>  
    </body>
</html>

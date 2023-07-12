<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="database.*" %>
<%@page import="model.*" %>
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

            .product_description {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                margin-bottom: 10px;
            }

            .product_description label {
                display: block;
                padding-bottom: 10px;
            }

            .product_description textarea {
                width: 98.5%;
                overflow-y: scroll;
                resize: none;
            }

            .form-selected {
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
        </style>
    </head>

    <body>
        <%
            CategoryDAO c = new CategoryDAO();
            String[] cols = (String[]) c.getColNames("Category");
            String error = (String) request.getAttribute("error");
        %>  

        <jsp:include page="adminnavbar.jsp" />

        <h2>Insert a category</h2>
        <form action="insertCategory" method="post">
            <% for (String col : cols) { 
            if (!col.equals("category_id")) {%> 
            <div class="form-group">
                <label><%=col%></label>
                <input type="text" name="<%=col%>" value="<%=request.getAttribute(col) == null ? "" : request.getAttribute(col)%>"/>
            </div>
            <% } } %>
            <div class="error" style="color:red;">
                <p> <%=((error == null) ? "" : error)%>
            </div>
            <input type ="submit" value="Insert">
        </form>  
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page import="model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Panel</title>
        <link rel="icon" href="./resources/img/icon.png" type="image/gif" sizes="16x16">
        <link rel="icon" href="./resources/img/icon.png" type="image/gif" sizes="18x18">
        <link rel="icon" href="./resources/img/icon.png" type="image/gif" sizes="20x20">
        <style>
            table {
                border-collapse: collapse;
                width: 80%;
                max-width: 400px;
                overflow-x : auto;
                margin: 0 auto;
                margin-top : 30px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            th, td {
                padding: 10px;
                max-width: 400px;
                overflow-x: auto;
                word-wrap: break-word;
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
            ProductDAO p = new ProductDAO();
            String[] cols = (String[]) p.getColNames("Product");
        %>

        <table border="1">
            <tr>
                <% for (String s : cols) { %>
                <th><%= s %></th>
                    <% } %>
                <form action="insertProductPanel" method="get">
                    <td> <input type="submit" value="Insert" /> </td>
                </form>
                <th></th>
            </tr>
        <% for (Product product : p.getProducts()) { %>
        <tr> 
            <% for (String infor : cols) {
            if (infor.equals("image")) { %>
            <td> <img style="width: 128px;" src="<%=p.getProductInformation(Integer.toString(product.getProductID()), infor)%>" alt="image_product"/> </td>
            <% } else {%>
            <td><%= p.getProductInformation(Integer.toString(product.getProductID()), infor) %></td>

            <% } } %>

        <form action="editProduct" method="get">
            <input type="hidden" name="productId" value="<%=product.getProductID()%>">
            <td> <input type="submit" value="Edit" /> </td>
        </form>
        <form action="removeProduct" method="post">
            <input type="hidden" name="productId" value="<%=product.getProductID()%>">
            <td> <input type="submit" value="Remove" /> </td>
        </form>

    </tr>
    <% } %>
</table>
</body>
</html>

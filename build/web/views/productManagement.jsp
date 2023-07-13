<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page import="model.*"%>

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
            .search-box {
                width: 100px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="adminnavbar.jsp" />

        <%
            ProductDAO p = new ProductDAO();
            String[] cols = (String[]) p.getColNames("Product");
        %>

        <table id="myTable" border="1">
            <tr>
                <% for (String s : cols) { %>
                <th><%= s %></th>
                    <% } %>
            <form action="insertProduct" method="get">
                <th> <input type="submit" value="Insert" /> </th>
            </form>
            <th>
                <input type="text" id="searchBox" class="search-box" placeholder="Search..." onkeyup="searchTable()">
            </th>
        </tr>
        <% for (Product product : p.getProducts()) { %>
        <tr> 
            <% for (String infor : cols) {
            if (infor.equals("image")) { %>
            <td> <img style="width: 128px;" src="<%=p.getProductInformation(Integer.toString(product.getProductID()), infor)%>" alt="image_product"/> </td>
                <% } else {%>
            <td><%= p.getProductInformation(Integer.toString(product.getProductID()), infor) %></td>

            <% } } %>

        <form action="updateProduct" method="get">
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
<script>
    function searchTable() {
        var input = document.getElementById("searchBox");
        var filter = input.value.toUpperCase();
        var table = document.getElementById("myTable");
        var rows = table.getElementsByTagName("tr");

        for (var i = 0; i < rows.length; i++) {
            var product_idColumn = rows[i].getElementsByTagName("td")[0];
            var seller_idColumn = rows[i].getElementsByTagName("td")[1];
            var product_nameColumn = rows[i].getElementsByTagName("td")[2];
            var product_descriptionColumn = rows[i].getElementsByTagName("td")[3];
            var category_idColumn = rows[i].getElementsByTagName("td")[4];
            var brand_idColumn = rows[i].getElementsByTagName("td")[5];
            var birthdayColumn = rows[i].getElementsByTagName("td")[6];
            var priceColumn = rows[i].getElementsByTagName("td")[7];
            var addressColumn = rows[i].getElementsByTagName("td")[8];
            var quantityColumn = rows[i].getElementsByTagName("td")[9];
            var imageColumn = rows[i].getElementsByTagName("td")[10];
            var discountColumn = rows[i].getElementsByTagName("td")[11];
            var insertColumn = rows[i].getElementsByTagName("td")[12];
            var removeColumn = rows[i].getElementsByTagName("td")[13];

            if (product_idColumn || seller_idColumn
                    || product_nameColumn) {
                var product_idText = product_idColumn.textContent || product_idColumn.innerText;
                var seller_idText = seller_idColumn.textContent || seller_idColumn.innerText;
                var product_nameText = product_nameColumn.textContent || product_nameColumn.innerText;

                if (product_idText.toUpperCase().indexOf(filter) > -1
                        || seller_idText.toUpperCase().indexOf(filter) > -1
                        || product_nameText.toUpperCase().indexOf(filter) > -1) {
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

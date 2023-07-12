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

            input[type="file"] {
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
            ProductDAO p = new ProductDAO();
            Product product = (Product) request.getAttribute("product");
            String[] cols = (String[]) p.getColNames("Product");
            String error = (String) request.getAttribute("error");
            if (product == null) return;
        %>  

        <jsp:include page="adminnavbar.jsp" />

        <h2>Update a product</h2>
        <form action="updateProduct" method="POST" enctype="multipart/form-data">
            <% for (String col : cols) {
                if (col.equals("product_id")) { %>
            <div class="form-group">
                <label><%=col%></label>
                <input type="text" name="<%=col%>" id="<%=col%>" value="<%=product.getProductID()%>" readonly />
            </div>
            <%} else if (col.equals("product_description")) { %> 
            <div class="product_description">
                <label><%=col%></label>
                <textarea name="<%=col%>" id="<%=col%>" rows="10" cols="20" style="overflow-y:scroll;"><%= p.getProductInformation(Integer.toString(product.getProductID()), col) %></textarea>
            </div>
            <%} else if (col.equals("category_id")) { %> 
            <div class="form-group">
                <label><%=col%></label>
                <select class="form-selected" name="<%=col%>">
                    <% for (String rowData : p.getRowNames(col, "category")) { 
                        boolean isSelected = rowData.equals(p.getProductInformation(Integer.toString(product.getProductID()), col));
                    %>
                    <option value="<%=rowData%>" <%=isSelected ? "selected" : ""%>><%=rowData%></option>
                    <% } %>
                </select>            
            </div>
            <% } else if (col.equals("brand_id")) { %>
            <div class="form-group">
                <label><%=col%></label>
                <select class="form-selected" name="<%=col%>">
                    <% for (String rowData : p.getRowNames(col, "brand")) { 
                        boolean isSelected = rowData.equals(p.getProductInformation(Integer.toString(product.getProductID()), col));
                    %>
                    <option value="<%=rowData%>" <%=isSelected ? "selected" : ""%>><%=rowData%></option>
                    <% } %>
                </select>            
            </div>
            <% } else if (col.equals("image")) { %>
            <div class="form-group">
                <label><%=col%></label>
                <input type="radio" name="remain" value="true" checked/>Remain
                <input type="radio" name="remain" value="false"  />Change
                <input type="text" name="imagePath" id="imagePath" value="<%=p.getProductInformation(Integer.toString(product.getProductID()), col) %>" hidden/>
            </div>
            <div class="form-group" id="imageUploadContainer" style="display:none;">
                <label>Upload Image:</label>
                <input type="file" id="image" name="image" accept="image/*" multiple="false"/>
            </div>
            <% } else { %>
            <div class="form-group">
                <label><%=col%></label>
                <input type="text" id="<%=col%>" name="<%=col%>" value="<%= p.getProductInformation(Integer.toString(product.getProductID()), col) %>"/>
            </div> 
            <% }
        }
            %>

            <div class="error" style="color:red;">
                <p> <%=((error == null) ? "" : error)%>
            </div>
            <input type="submit" value="Update">
        </form>  
        <script>
            const remainRadio = document.querySelector('input[value="true"]');
            const changeRadio = document.querySelector('input[value="false"]');
            const imageUploadContainer = document.getElementById('imageUploadContainer');

            remainRadio.addEventListener('change', function () {
                imageUploadContainer.style.display = 'none';
            });

            changeRadio.addEventListener('change', function () {
                imageUploadContainer.style.display = 'block';
            });
        </script>
    </body>
</html>

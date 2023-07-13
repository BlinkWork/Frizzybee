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

            /* Css for the dialog box */

            /* CSS for the overlay */
            .overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                z-index: 9999;
                display: none;
            }

            /* CSS for the dialog box */
            .dialog-box {
                width: 300px;
                padding: 20px;
                background-color: #f2f2f2;
                border: 1px solid #ccc;
                border-radius: 4px;
                text-align: center;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                z-index: 10000;
                display: none;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .dialog-box text-box {
                margin-top: 0;
            }

            .dialog-box button {
                margin: 10px 5px;
                padding: 8px 16px;
                border-radius: 4px;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .dialog-box button.submit {
                background-color: #4CAF50;
                color: white;
                float: right;
            }

            .dialog-box button.cancel {
                background-color: #FFA500;
                color: white;
                float: right;
            }

            .dialog-box button:hover {
                background-color: #333;
            }

            /* Styling for the h1 and button */
            h1 {
                text-align: center;
            }

            button {
                display: block;
                margin: 20px auto;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                font-size: 16px;
                font-weight: bold;
                color: white;
                background-color: #007BFF;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>

        <jsp:include page="adminnavbar.jsp" />

        <%
            CategoryDAO c = new CategoryDAO();
            String[] cols = (String[]) c.getColNames("Category");
        %>

        <table border="1">
            <tr>
                <% for (String s : cols) { %>
                <th><%= s %></th>
                    <% } %>
            <form action="insertCategory" method="get">
                <th> <input type="submit" value="Insert" /> </th>
            </form>
            <th></th>
        </tr>
        <% for (Category category : c.getCategorys()) { %>
        <tr> 
            <% for (String infor : cols) { %>
            <td><%= c.getCategoryInformation(Integer.toString(category.getCategoryID()), infor) %></td>
            <% } %>
        <form action="editCategory" method="get">
            <input type="hidden" name="categoryId" value="<%=category.getCategoryID()%>">
            <td> <input type="submit" value="Edit" /> </td>
        </form>
        <form id="removeCategoryForm" action="removeCategory" method="post">
            <td>
                <input type="submit" value="Remove" onclick="showDialog(event, <%=c.countProduct(Integer.toString(category.getCategoryID()))%>, <%=category.getCategoryID()%>)" />
            </td>
        </form>

    </tr>
    <% } %>
</table>
<script>
    function showDialog(event, numberOfProduct, categoryId) {
        event.preventDefault(); // Prevent the default form submission

        var overlay = document.createElement("div");
        overlay.className = "overlay";
        document.body.appendChild(overlay);

        var dialogBox = document.createElement("div");
        dialogBox.className = "dialog-box";
        overlay.appendChild(dialogBox);

        var dialogTitle = document.createElement("p");
        dialogTitle.className = "text-box";
        dialogTitle.innerText = "Are you sure you want to remove this category? \n\This will remove " + numberOfProduct + " products according to the category.";
        dialogBox.appendChild(dialogTitle);

        var submitButton = document.createElement("button");
        submitButton.className = "submit";
        submitButton.innerText = "Submit";
        submitButton.addEventListener("click", function () {
            handleFormSubmit(categoryId);
        });
        dialogBox.appendChild(submitButton);

        var cancelButton = document.createElement("button");
        cancelButton.className = "cancel";
        cancelButton.innerText = "Cancel";
        cancelButton.addEventListener("click", function () {
            document.body.removeChild(overlay);
        });
        dialogBox.appendChild(cancelButton);


        overlay.style.display = "block";
        dialogBox.style.display = "block";
    }

     function handleFormSubmit(categoryId) {
        var form = document.createElement("form");
        form.method = "post";
        form.action = "removeCategory";
        form.style.display = "none";

        var input = document.createElement("input");
        input.type = "hidden";
        input.name = "categoryId";
        input.value = categoryId;

        form.appendChild(input);
        document.body.appendChild(form);

        form.submit();
    }
</script>
</body>
</html>

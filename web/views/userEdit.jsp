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

            input[type="file"] {
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
            UserDAO u = new UserDAO();
            User user = (User) request.getAttribute("user");
            String[] cols = (String[]) u.getColNames("User");
            String error = (String) request.getAttribute("error");
            if (user == null) return;
        %>  

        <jsp:include page="adminnavbar.jsp" />

        <h2>Update a user</h2>
        <form action="updateUser" method="POST"  enctype="multipart/form-data">
            <% for (String col : cols) {
    if (col.equals("id")) { %>
            <div class="form-group">
                <label><%=col%></label>
                <input type="text" name="<%=col%>" value="<%=user.getId()%>" readonly />
            </div>
            <% } else if (col.equals("birthday")) {%>
            <div class="form-group">    
                <label><%=col%></label>
                <input type="Date" name="<%=col%>" value="<%= u.getUserInformation(user.getUsername(), col) %>"/>
            </div>
            <%} else if (col.equals("isAdmin") || col.equals("isSeller")) {%>
            <div class="form-group">   
                <label><%=col%></label>

                <select class="form-selected" name="<%=col%>">
                    <option value="true" <%= "true".equals(u.getUserInformation(user.getUsername(), col)) ? "selected" : "" %>>true</option>
                    <option value="false" <%= "false".equals(u.getUserInformation(user.getUsername(), col)) ? "selected" : "" %>>false</option>
                </select>
            </div>
            <% } else if (col.equals("avatar")) { %>
            <div class="form-group">
                <label><%=col%></label>
                <input type="radio" name="remain" value="true" checked/>Remain
                <input type="radio" name="remain" value="false"  />Change
                <input type="text" name="imagePath" id="imagePath" value="<%=u.getUserInformation(user.getUsername(), col) %>" hidden/>
            </div>
            <div class="form-group" id="imageUploadContainer" style="display:none;">
                <label>Upload Image:</label>
                <input type="file" id="image" name="image" accept="image/*" multiple="false"/>
            </div>    
            <%} else { %>
            <div class="form-group">
                <label><%=col%></label>
                <input type="text" name="<%=col%>" value="<%= u.getUserInformation(user.getUsername(), col) %>"/>
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

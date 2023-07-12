<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Panel</title>
        <style>
            /* Reset default margin and padding */
            body, ul {
                margin: 0;
                padding: 0;
            }

            /* Center the heading */
            h1 {
                text-align: center;
            }

            /* Style the navigation */
            nav {
                background-color: #f0f0f0;
            }

            nav ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
            }

            nav ul li {
                display: inline-block;
                margin-right: 10px;
            }

            nav ul li a {
                text-decoration: none;
                color: #333;
                padding: 5px 10px;
            }

            nav ul li a:hover {
                background-color: #333;
                color: #fff;
            }
        </style>
    </head>
    <body>
        <nav>
            <ul>
                <li><a class="panel" href="usermanagement">User Management</a></li>
                <li><a class="panel" href="productmanagement">Product Management</a></li>
                <li><a class="panel" href="categorymanagement">Category Management</a></li>
                <li><a class="panel" href="brandmanagement">Brand Management</a></li>
                <li><a class="panel" href="./">Back to Home</a></li>
            </ul>
        </nav>
    </body>
</html>

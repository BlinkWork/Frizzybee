<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Panel</title>
        <link rel="stylesheet" href="./resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="./resources/css/fontawesome.all.min.css">
    <link rel="stylesheet" href="./resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="./resources/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="./resources/css/animate.css">
    <link rel="stylesheet" href="./resources/css/magnific-popup.css">
    <link rel="stylesheet" href="./resources/css/normalize.css">
    <link rel="stylesheet" href="./resources/css/style.css">
    <link rel="stylesheet" href="./resources/css/responsive.css">
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
            
            a.heading-link {
                text-align: center;
                display: block;
                font-size: 2em;
                font-weight: bold;
                color: #000;
                text-decoration: none;
                margin-block-start: 0.67em;
                margin-block-end: 0.67em;
                margin-inline-start: 0px;
                margin-inline-end: 0px;
            }   
            
            header {
              background-color: #e49607;
              color: #fff;
              padding: 10px 0;
            }

            header h1 {
              font-size: 2rem;
              margin: 0;
            }

            nav ul li a {
              color: black;
              text-decoration: none;
              font-weight: 500;
            }

            nav ul li a:hover {
              color: #f8f9fa;
            }

            .live-statistic {
              background-color: orange;
              border-radius: 10px;
              padding: 20px;
              margin-top: 50px;
              box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            }

            .live-statistic p {
              color: #fff;
              font-size: 1.5rem;
              margin: 0;
            }

            .live-statistic i {
              font-size: 3rem;
              color: #6c757d;
              margin-right: 10px;
            }
            
            .head-page{
              color: #fff;
              margin: 30px;
            }
        </style>
    </head>
    <body>
        <header>
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="head-page">Admin Panel</h1>
          </div>
        </div>
        
        <div class="row">
          
          <div class="col-md-12">
            <nav>
              <ul class="nav justify-content-center">
                <li class="nav-item">
                  <a class="nav-link" href="adminpanel">Admin Panel</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="usermanagement">User Management</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="productmanagement">Product Management</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="categorymanagement">Category Management</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="brandmanagement">Brand Management</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="./">Back to Home</a>
                </li>
              </ul>
            </nav>
          </div>
        </div>
      </div>
    </header>
</body>
</html>

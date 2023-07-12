<!DOCTYPE html>
<html  class="no-js" lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
         <title>FrizzeBee - Electronics Shop</title>
        <link rel="icon" href="./resources/img/logo.png" type="image/gif" sizes="16x16">
        <link rel="icon" href="./resources/img/logo.png" type="image/gif" sizes="18x18">
        <link rel="icon" href="./resources/img/logo.png" type="image/gif" sizes="20x20">

        <link rel="stylesheet" href="./resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="./resources/css/fontawesome.all.min.css">
        <link rel="stylesheet" href="./resources/css/owl.carousel.min.css">
        <link rel="stylesheet" href="./resources/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="./resources/css/animate.css">
        <link rel="stylesheet" href="./resources/css/magnific-popup.css">
        <link rel="stylesheet" href="./resources/css/normalize.css">
        <link rel="stylesheet" href="./resources/css/style.css">
        <link rel="stylesheet" href="./resources/css/responsive.css">

    </head>
    <body>

        <!-- Start BreadCrumb Area -->
        <div class="breadcrumb-area pt-100 pb-100" style="background-image: url('./resources/img/breadcrumb.jpg');">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-content">
                            <h2>Change Information</h2>
                            <ul>
                                <li><a href="index.jsp">Home</a></li>
                                <li class="active">Change Information</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End BreadCrumb Area -->

        <div class="login-register-form pt-70 pb-70">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 offset-lg-3">
                        <div class="login-register-form-full">
                            <h3>Change Information</h3>
                            <form action="updateinformation" method="post" >

                                <input type="text" class="form-control" name="name" placeholder="Your Name" value="<%=(request.getAttribute("name") != null) ? request.getAttribute("name") : ""%>">
                                <input type="text" class="form-control" name="username" placeholder="Username" value="<%=(request.getAttribute("username") != null) ? request.getAttribute("username") : ""%>">
                                <input type="email" class="form-control" name="email" placeholder="Your Email" value="<%=(request.getAttribute("email") != null) ? request.getAttribute("email") : ""%>">
                                <input type="text" class="form-control" name="address" placeholder="Your Address" value="<%=(request.getAttribute("address") != null) ? request.getAttribute("address") : ""%>">
                                <input type="Date" class="form-control" name="dob" placeholder="Date of birth" value="<%=(request.getAttribute("dob") != null) ? request.getAttribute("dob") : ""%>">
                                <select name="gender" class="form-control" >
                                    <option value="male" <%= "male".equals(request.getParameter("gender")) ? "selected" : "" %>>Male</option>
                                    <option value="female" <%= "female".equals(request.getParameter("gender")) ? "selected" : "" %>>Female</option>
                                </select>
                                <button class="button-1" type="submit">Change information</button>
                                <div class = error-box> 
                                    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                                    <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
                                    <p style="color:red;text-align: center;"><%= errorMessage %></p>
                                    <% } %>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <div class="scroll-area">
            <i class="fa fa-angle-up"></i>
        </div>


        <!-- Js File -->
        <script src="./resources/js/modernizr.min.js"></script>
        <script src="./resources/js/jquery-3.5.1.min.js"></script>
        <script src="./resources/js/popper.min.js"></script>
        <script src="./resources/js/bootstrap.min.js"></script>
        <script src="./resources/js/owl.carousel.min.js"></script>
        <script src="./resources/js/jquery.nav.min.js"></script>
        <script src="./resources/js/jquery.magnific-popup.min.js"></script>
        <script src="./resources/js/mixitup.min.js"></script>
        <script src="./resources/js/wow.min.js"></script>
        <script src="./resources/js/script.js"></script>
        <script src="./resources/js/mobile-menu.js"></script>
    </body>
</html>

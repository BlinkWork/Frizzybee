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
        <%--<%@include file="../views/components/header_component.jsp" %>--%>

        <!-- Start BreadCrumb Area -->
        <div class="breadcrumb-area pt-100 pb-100" style="background-image: url('./resources/img/breadcrumb.jpg');">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-content">
                            <h2>Register</h2>
                            <ul>
                                <li><a href="index.jsp">Home</a></li>
                                <li class="active">Register</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End BreadCrumb Area -->

        <!-- Start Login Register Form -->
        <div class="login-register-form pt-70 pb-70">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 offset-lg-3">
                        <div class="login-register-form-full">
                            <h3>Register</h3>
                            <form action="register" method="post">
                                <input type="text" class="form-control" name="fname" placeholder="First Name" value="<%=(request.getAttribute("fname") != null) ? request.getAttribute("fname") : ""%>">
                                <input type="text" class="form-control" name="lname" placeholder="Last Name" value="<%=(request.getAttribute("lname") != null) ? request.getAttribute("lname") : ""%>">
                                <input type="text" class="form-control" name="username" placeholder="Username" value="<%=(request.getAttribute("username") != null) ? request.getAttribute("username") : ""%>">
                                <input type="email" class="form-control" name="email" placeholder="Your Email" value="<%=(request.getAttribute("email") != null) ? request.getAttribute("email") : ""%>">
                                <input type="text" class="form-control" name="address" placeholder="Your Address" value="<%=(request.getAttribute("address") != null) ? request.getAttribute("address") : ""%>">
                                <input type="Date" class="form-control" name="dob" placeholder="Date of birth" value="<%=(request.getAttribute("dob") != null) ? request.getAttribute("dob") : ""%>">
                                <select name="gender" class="form-control" >
                                    <option value="male" <%= "male".equals(request.getParameter("gender")) ? "selected" : "" %>>Male</option>
                                    <option value="female" <%= "female".equals(request.getParameter("gender")) ? "selected" : "" %>>Female</option>
                                </select>
                                <input type="password" class="form-control" name="pass" placeholder="Your Password" value="<%=(request.getAttribute("pass") != null) ? request.getAttribute("pass") : ""%>">
                                <input type="password" class="form-control" name="cpass" placeholder="Confirm Password" value="<%=(request.getAttribute("cpass") != null) ? request.getAttribute("cpass") : ""%>">
                                <button class="button-1" type="submit">Sign Up</button>
                                <div class = error-box> 
                                    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                                    <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
                                    <p style="color:red;text-align: center;"><%= errorMessage %></p>
                                    <% } %>
                                </div>
                            </form>
                            <p>Already Have an Account? Please <a href="login">Login now</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Login Register Form -->

        <!-- Start Footer Area -->
        <footer class="footer">
            <!-- Footer Top -->
            <div class="footer-top pt-50">
                <div class="container">
                    <div class="row">
                        <!-- SIngle -->
                        <div class="col-lg-3 col-md-6 mb-30">
                            <div class="footer-widgets-single">
                                <h2><img src="./resources/img/white-logo.png"></h2>
                                <p> Lorem ipsum dolor sit amet, consectetuipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqut enim ad minim veniamquis </p>
                            </div>
                        </div>
                        <!-- SIngle -->
                        <div class="col-lg-3 col-md-6 mb-30">
                            <div class="footer-widgets-single">
                                <h3>My account</h3>
                                <ul>
                                    <li><a href="#">Sign In</a></li>
                                    <li><a href="#">View Cart</a></li>
                                    <li><a href="#">My Wishlist</a></li>
                                    <li><a href="#">Terms & Conditions</a></li>
                                    <li><a href="#">Contact us</a></li>
                                    <li><a href="#">Track My Order</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- SIngle -->
                        <div class="col-lg-3 col-md-6 mb-30">
                            <div class="footer-widgets-single">
                                <h3>Categories</h3>
                                <ul>
                                    <li><a href="#">Home Audio & Theater</a></li>
                                    <li><a href="#">TV & Video</a></li>
                                    <li><a href="#">Camera, Photo & Video</a></li>
                                    <li><a href="#">Cell Phones & Accessories</a></li>
                                    <li><a href="#">Headphones</a></li>
                                    <li><a href="#">Video Games</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- SIngle -->
                        <div class="col-lg-3 col-md-6 mb-30">
                            <div class="footer-widgets-single">
                                <h3>Newsletter</h3>
                                <p> Get notified of new products, limited releases, and more. </p>
                                <form action="#">
                                    <input type="email" name="email" placeholder="Your Email">
                                    <button type="submit" class="button-1">Subscribe</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Footer Bottom -->
            <div class="footer-bottom pt-30 pb-30">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="copyright-text">
                                <p> &copy; Copyright 2021 <a href="https://codepopular.com" target="_blank">CodePopular</a> All Rights Reserved. </p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <ul class="footer_payment">
                                <li><a href="#"><img src="./resources/img/visa.png" alt="visa"></a></li>
                                <li><a href="#"><img src="./resources/img/discover.png" alt="discover"></a></li>
                                <li><a href="#"><img src="./resources/img/master_card.png" alt="master_card"></a></li>
                                <li><a href="#"><img src="./resources/img/paypal.png" alt="paypal"></a></li>
                                <li><a href="#"><img src="./resources/img/amarican_express.png" alt="amarican_express"></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- End Footer Area -->



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

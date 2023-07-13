<%-- 
    Document   : index
    Created on : Jun 21, 2023, 4:48:54 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.*" %>
<%@page import = "database.*" %>
<%@page import = "java.util.*" %>
<%@page import = "controller.*" %>
<%@page import = "java.util.*" %>
<!DOCTYPE html>
<html class="no-js" lang="en">

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

        <style>
            .user-image .show {
                margin-top: 30px !important;
            }
            .user-image ul.show a{
                color: black;
            }
            .user-image ul.show a:hover{
                color: rgb(234, 136, 13);
            }

            .user-image li{
                padding: 0 10px;
            }

            .user-setting li{
            padding: 0 10px;
          }
          
          .dropdown-item{
            color: black !important;
          }
          
          .dropdown-item:hover{
            color: orange !important;
          }
          
          .hidden {
            display: none !important;
          }
            
        </style>

    </head>

    <body>
        <%
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()  + request.getContextPath();
        String username = (String) session.getAttribute("username");
        UserDAO dao = new UserDAO();
        User curUser = dao.getUserByUsername(username);
        %>
        <div id="preloader" class="preeloader">
            <div class="sk-circle">
                <div class="sk-circle1 sk-child"></div>
                <div class="sk-circle2 sk-child"></div>
                <div class="sk-circle3 sk-child"></div>
                <div class="sk-circle4 sk-child"></div>
                <div class="sk-circle5 sk-child"></div>
                <div class="sk-circle6 sk-child"></div>
                <div class="sk-circle7 sk-child"></div>
                <div class="sk-circle8 sk-child"></div>
                <div class="sk-circle9 sk-child"></div>
                <div class="sk-circle10 sk-child"></div>
                <div class="sk-circle11 sk-child"></div>
                <div class="sk-circle12 sk-child"></div>
            </div>
        </div>
        <div class="off_canvars_overlay"></div>
        <!-- Header -->
        <header class="header">
            <!-- Header Top -->
            <div class="header-top">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-5">
                            <div class="top-text">
                                <p>Welcome to Electronics Shop</p>
                            </div>
                        </div>
                        <div class="col-lg-7">
                            <div class="top-list">
                                <a href="#"><i class="fas fa-mobile-alt"></i>0834398268</a>
                                <a href="./views/wishlist.jsp"><i class="far fa-heart"></i> Wishlist</a>
                                <%if(curUser!=null){%>
                                    <div class="d-flex align-items-center justify-content-center" style="float: right" />

                             <div class="dropdown">
                              <img class="test" alt="setting" src="<%=curUser.getAvatarURL()%>" style="width:30px; border: 2px solid #cccccc5e; border-radius: 50%;">
                              <ul class="dropdown-menu user-setting" style="margin-left: -77px !important;">
                                <li><a class="dropdown-item userName" href="#"><%=curUser.getName()%></a></li>
                                <li><a class="dropdown-item" href="./cart">My Cart</a></li>
                                <li><a class="dropdown-item" href="updateinformation">Update my information</a></li>
                                <li><a class="dropdown-item" href="changepassword">Change password</a></li>
                                <li><a class="dropdown-item" href="order-management?event=my-order">My Order</a></li>
                                <%if(curUser.getRole().equals("seller") || curUser.getRole().equals("admin")){%>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="product-management?event=product-management">Product manage</a></li>
                                <li><a class="dropdown-item" href="product-management?event=send-to-add">Add product</a></li>
                                <li><a class="dropdown-item" href="order-management?event=order-management">Order management</a></li>
                                <%} 
                                if (curUser.getRole().equals("admin")) {%>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="adminpanel">Admin panel</a></li>
                                <%} %>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="logout">Logout</a></li>
                              </ul>
                            </div>
                            </div>
                            <%}else{%>
                            <a href="login"><i class="fas fa-user"></i> Login / Register</a>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header Middle -->
        <div class="header-middle pt-30 pb-30">
            <div class="container">
                <div class="row">
                    <!-- Logo -->
                    <div class="col-lg-2">
                        <div class="logo">
                          <h2><a href="./"><img src="./resources/img/logo.png" style="width: 77px"></a></h2>
                        </div>
                    </div>
                    <!-- Search Bar -->
                    <div class="col-lg-8">
                        <div class="header-search-form">
                            <form action="shop">
                                <select class="form-select" name="tag">
                                    <option selected>All Categories</option>
                                    <option value="Laptop">Laptop</option>
                                    <option value="Phone">Phone</option>
                                    <option value="Computer">Computer</option>
                                    <option value="Smart Watch">Smart Watch</option>
                                    <option value="Headphones">Headphones</option>
                                </select>
                                <input type="search" name="search" placeholder="Search keyword here...">
                                <button type="submit"><i class="fas fa-search"></i></button>
                            </form>
                        </div>
                    </div>
                    
                    <%
                        CartServlet cartTest = new CartServlet();
                        HttpSession sessions = request.getSession();
                        String userName = (String) sessions.getAttribute("username");
                        List<Cart> listCartId = null;

                        if (userName != null) {
                          UserDAO udao = new UserDAO();
                          String user_id = String.valueOf(udao.getUserByUsername(userName).getId());
                          String cartItems = cartTest.getCartSession(request, response, user_id);
                            if (cartItems != null) {
                            listCartId = cartTest.parseCarts(cartItems);
                          }
                        }
                        
                    %>
                    
                    <!-- Mini Cart -->
                    <div class="col-lg-2">
                        <div class="desktop-mini-cart">
                            <div class="mini-cart">
                                <div class="mini-cart-icon">
                                    <i class="fas fa-shopping-cart"></i>
                                    <span class="counter"> 
                                      <%
                                        if(listCartId != null){
                                          if(listCartId.size() > 0 ){
                                              out.println(listCartId.size());
                                              }
                                          
                                        }
                                      %>
                                    </span>
                                    <span class="counter-cart"><small>Your Cart</small>$10.00</span>
                                    <!-- Mini Cart Content -->
                                    <div class="minicart-content-wrapper">
                                        <ul class="cart-list-full">
                                            <!-- Single -->
                                            
                                        </ul>
                                        <h2 class="subtotal">Subtotal : <span>$220</span></h2>
                                        <div class="minicart-btn">
                                            <a class="button-1" href="cart.jsp">View Cart</a>
                                            <a class="button-2" href="#">Checkout</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header Bottom -->
        <div class="header-bottm">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="logo-2">
                          <h2><a href="./"><img src="./resources/img/logo.png" style="width: 100px"></a></h2>
                        </div>
                        <div class="canvas_open">
                            <a href="javascript:void(0)"><i class="fas fa-bars"></i></a>
                        </div>
                        <div class="mobile-mini-cart">
                            <div class="mini-cart">
                                <div class="mini-cart-icon">
                                    <i class="fas fa-shopping-cart"></i>
                                    <span class="counter"></span>
                                    <span class="counter-cart"><small>Your Cart</small>$10.00</span>
                                    <!-- Mini Cart Content -->
                                    <div class="minicart-content-wrapper">
                                        <ul class="cart-list-full">
                                            <!-- Single -->
                                            <li class="cart-list-single">
                                                <img src="./resources/img/product/1.jpg" alt="img">
                                                <h5><a href="#">simple product</a></h5>
                                                <span class="price">$120</span>
                                                <div class="close"><i class="fas fa-times"></i></div>
                                            </li>
                                            <!-- Single -->
                                            <li class="cart-list-single">
                                                <img src="./resources/img/product/2.jpg" alt="img">
                                                <h5><a href="#">simple product</a></h5>
                                                <span class="price">$120</span>
                                                <div class="close"><i class="fas fa-times"></i></div>
                                            </li>
                                        </ul>
                                        <h2 class="subtotal">Subtotal : <span>$220</span></h2>
                                        <div class="minicart-btn">
                                            <a class="button-1" href="cart.jsp">View Cart</a>
                                            <a class="button-2" href="#">Checkout</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="menu">
                            <nav>
                                <ul>
                                    <li><a href="./">Home</a></li>
                                    <li><a href="./about">About</a></li>
                                    <li><a href="./shop">Shop</a></li>
                                    <li><a href="./privacy-policy">Privacy Policy</a></li>
                                    <li><a href="./contact">Contact</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Header -->

    <div class="scroll-area">
        <i class="fa fa-angle-up"></i>
    </div>

    <!-- Start Hero Area -->
    <section class="hero-area">
        <div class="hero-area-full owl-carousel">
            <!-- Single -->
            <div class="hero-slider-single" style="background-image: url('./resources/img/redsentai.png');">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="hero-slider-single-content" style="float:right;">
                                <h2>Summer Sale <span>Up to 50% Off!</span></h2>
                                <p>
                                  Summer is here, and so are the deals!
                                </p>
                                <p>
                                  Get ready for the season with our Summer Sale and save up to 50% on select items. 
                                </p>
                                <p>
                                  From swimwear to sunglasses, we've got everything you need to make a splash this summer. 
                                </p>
                                <p>
                                  Don't miss out on these hot deals - shop now!
                                </p>
                                <a class="button-1" href="./shop">Shop Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="hero-slider-single" style="background-image: url('./resources/img/yellowsentai.png');">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="hero-slider-single-content">
                                <h2>New Arrivals <span>Shop the Latest Trends</span></h2>
                                <p>
                                   Looking for the latest fashion trends?
                                </p>
                                <p>
                                  Look no further than our New Arrivals collection!
                                </p>
                                <p>
                                  From statement pieces to essential basics, we've got everything you need to stay on-trend this season.
                                </p>
                                <p>
                                  Shop now and be the first to rock the latest looks.
                                </p>
                                <a class="button-1" href="./shop">Shop Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Single -->
            <div class="hero-slider-single" style="background-image: url('./resources/img/bluesentai.png');">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="hero-slider-single-content">
                                <h2>Free Shipping on All Orders <span>Limited Time Only</span></h2>
                                <p>
                                  Get ready to shop 'til you drop! 
                                </p>
                                <p>
                                  For a limited time only, we're offering free shipping on all orders.
                                </p>
                                <p>
                                  Whether you're stocking up on essentials or treating yourself to something special, now is the perfect time to shop.
                                </p>
                                <p>
                                  <strong>Don't wait </strong> - take advantage of this offer while it lasts!
                                </p>
                                <a class="button-1" href="./shop">Shop Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- End Hero Area -->
    <!-- Start Shipping Area -->
    <div class="shipping-area container">
        <div class="row">
            <!-- Single -->
            <!-- Single -->
            <div class="col-lg-4 col-md-6 mb-25">
                <div class="shipping-item">
                    <div class="icon">
                        <img src="./resources/img/pay-2.png" alt="img">
                    </div>
                    <div class="content">
                        <h4>Free Returns</h4>
                        <p>Returns are free within 9 days</p>
                    </div>
                </div>
            </div>
            <!-- Single -->
            <div class="col-lg-4 col-md-6 mb-25">
                <div class="shipping-item">
                    <div class="icon">
                        <img src="./resources/img/pay-3.png" alt="img">
                    </div>
                    <div class="content">
                        <h4>Support 24/7</h4>
                        <p>Contact us 24 hours a day</p>
                    </div>
                </div>
            </div>
            <!-- Single -->
            <div class="col-lg-4 col-md-6 mb-25">
                <div class="shipping-item">
                    <div class="icon">
                        <img src="./resources/img/pay-4.png" alt="img">
                    </div>
                    <div class="content">
                        <h4>100% Payment Secure</h4>
                        <p>Your payment are safe with us.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Shipping Area -->
    <!-- Start banner Area -->
    <div class="banner-area container">
        <div class="row">
            <!-- Single -->
            <div class="col-lg-4 col-md-6 mb-30">
                <div class="banner-item">
                    <a href="./shop">
                        <img src="./resources/img/banner11.jpg" alt="img">
                    </a>
                </div>
            </div>
            <!-- Single -->
            <div class="col-lg-4 col-md-6 mb-30">
                <div class="banner-item">
                    <a href="./shop">
                        <img src="./resources/img/banner12.jpg" alt="img">
                    </a>
                </div>
            </div>
            <!-- Single -->
            <div class="col-lg-4 col-md-6 mb-30">
                <div class="banner-item">
                    <a href="./shop">
                        <img src="./resources/img/banner13.jpg" alt="img">
                    </a>
                </div>
            </div>
        </div>
    </div>
    <!-- End banner Area -->
    <!-- Start Footer Area -->
    <footer class="footer">
        <!-- Footer Top -->
        <div class="footer-top pt-50">
            <div class="container">
                <div class="row">
                    <!-- SIngle -->
                    <div class="col-lg-3 col-md-6 mb-30">
                        <div class="footer-widgets-single">
                            <h2><img src="./resources/img/logo.png" style="width: 200px"></h2>
                            
                        </div>
                    </div>
                    <!-- SIngle -->
                    <div class="col-lg-5 col-md-6 mb-30">
                        <div class="footer-widgets-single">
                            <p> Introducing our advanced e-commerce web technology! Our platform offers customizable product pages, secure payment processing, and streamlined inventory management for a seamless shopping experience. Join the future of online retail today with our innovative web technology. </p>
                        </div>
                    </div>
                    <!-- SIngle -->
                    <div class="col-lg-1 col-md-6 mb-30" style="opacity: 0">

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
                            <p> &copy; Copyright 2021 <a href="https://codepopular.com" target="_blank">CodePopular</a> All Rights
                                Reserved. </p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <ul class="footer_payment" style="opacity: 0">
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
    <script src="./resources/js/index.js"></script>
    <script>
      let dropdown = document.querySelector('.dropdown');
let dropdownMenu = document.querySelector('.dropdown-menu');

dropdown.addEventListener('click', function (event) {
  event.stopPropagation();
  dropdownMenu.classList.toggle('show');
});

document.addEventListener('click', function (event) {
  if (!dropdown.contains(event.target)) {
    dropdownMenu.classList.remove('show');
  }
});
    </script>
</body>

</html>

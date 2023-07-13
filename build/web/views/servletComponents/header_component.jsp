<%--
    Document   : header_component
    Created on : Jun 21, 2023, 4:42:52 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.*" %>
<%@page import = "database.*" %>
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
                            <h2><a href="./"><img src="./resources/img/logo.png" style="width: 77px"></a></h2>
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

<%--
    Document   : header_component
    Created on : Jun 21, 2023, 4:42:52 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>BulkShop - Electronics Shop HTML Template | Home</title>
    <link rel="icon" href="./resources/img/icon.png" type="image/gif" sizes="16x16">
    <link rel="icon" href="./resources/img/icon.png" type="image/gif" sizes="18x18">
    <link rel="icon" href="./resources/img/icon.png" type="image/gif" sizes="20x20">

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
                <a href="./views/contact.jsp"><i class="fas fa-mobile-alt"></i> +02456 054546</a>
                <a href="./views/wishlist.jsp"><i class="far fa-heart"></i> Wishlist</a>
                <a href="./login"><i class="fas fa-user"></i> Login / Register</a>
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
                <h2><a href="./index.jsp"><img src="./resources/img/logo_1.png" style="width: 75px"></a></h2>
              </div>
            </div>
            <!-- Search Bar -->
            <div class="col-lg-8">
              <div class="header-search-form">
                <form action="#">
                  <select class="form-select">
                    <option selected>All Categories</option>
                    <option value="1">Mobile</option>
                    <option value="2">LifeStyle</option>
                    <option value="3">Leptop</option>
                    <option value="4">Cell Phones</option>
                    <option value="5">Game & Consoles</option>
                    <option value="6">Smart Watchs</option>
                    <option value="7">Smartphone</option>
                  </select>
                  <input type="search" name="search" placeholder="Search keyword here...">
                  <button type="submit"><i class="fas fa-search"></i></button>
                </form>
              </div>
            </div>
            <!-- MiniCart -->
            <div class="col-lg-2">
              <div class="desktop-mini-cart">
                <div class="mini-cart">
                  <div class="mini-cart-icon">
                    <i class="fas fa-shopping-cart"></i>
                    <span class="counter">02</span>
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
                <h2><a href="./index.jsp"><img src="./resources/img/logo_1.png" style="width: 75px"></a></h2>
              </div>
              <div class="canvas_open">
                <a href="javascript:void(0)"><i class="fas fa-bars"></i></a>
              </div>
              <div class="mobile-mini-cart">
                <div class="mini-cart">
                  <div class="mini-cart-icon">
                    <i class="fas fa-shopping-cart"></i>
                    <span class="counter">02</span>
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
                    <li><a href="./index.jsp">Home</a></li>
                    <li><a href="./views/about.jsp">About</a></li>
                    <li><a href="./shop">Shop</a></li>
                    <li><a href="./views/privacy-policy.jsp">Privacy Policy</a></li>
                    <li><a href="./views/faq.jsp">FAQ</a></li>
                    <li><a href="./views/contact.jsp">Contact</a></li>
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
  </body>

</html>
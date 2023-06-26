<%@page import = "model.*" %>
<%@page import = "database.*" %>
<%@page import = "java.util.*" %>

<!DOCTYPE html>
<html  class="no-js" lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>BulkShop - Electronics Shop HTML Template | Shop Left</title>
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
        <%
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()  + request.getContextPath();
        String username = (String) session.getAttribute("username");
        UserDAO userDao = new UserDAO();
        ProductDAO productDao = new ProductDAO();
        User curUser = userDao.getUserByUsername(username);
        List<Product> listProduct = (List<Product>) request.getAttribute("listProduct");
        if(curUser != null && !(curUser.getRole().equals("seller") || curUser.getRole().equals("admin"))){
        %>

    <div class="error-404-page pt-100 pb-100">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 offset-lg-2">
            <div class="error-404-full-content">
              <h4>Sorry! You are not authorized</h4>
              <p>You need permission to use this function</p>
              <a class="button-1" href="index.jsp">Go to Home</a>
            </div>
          </div>
        </div>
      </div>
    </div>
        <%}else{%>
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
                <a href="#"><i class="fas fa-mobile-alt"></i> +0834398268</a>
                <a href="./wishlist.jsp"><i class="far fa-heart"></i> Wishlist</a>
                <div class="d-flex align-items-center justify-content-center" style="float: right" />
                                <img src="<%=curUser.getAvatarURL()%>" alt="user" width="20px" style="object-fit: contain;">
					<p><%=curUser.getName()%></p>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 bg-infor ">
                    <li class="nav-item dropdown dropstart"><a
			class="dropdown-toggle" href="#" role="button"
			data-bs-toggle="dropdown" aria-expanded="false"> <img alt="c�i ??t" src="https://cdn-icons-png.flaticon.com/512/3524/3524659.png" width="15px"></a>
			<ul class="dropdown-menu">
			<li><a class="dropdown-item" href="#">My Cart</a></li>
			<li><a class="dropdown-item" href="#">Update my information</a></li>
			<li><a class="dropdown-item" href="#">Change password</a></li>
			<li><hr class="dropdown-divider"></li>
			<li><a class="dropdown-item" href="logout">Logout</a></li>
                        <li><hr class="dropdown-divider"></li>
                         <li><a class="dropdown-item" href="product-management?event=product-management">Product manage</a></li>
                    </ul></li>
		</ul></div>
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
                <h2><a href="./index.jsp"><img src="./resources/img/logo.png"></a></h2>
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
                          <img src="../resources/img/product/1.jpg" alt="img">
                          <h5><a href="#">simple product</a></h5>
                          <span class="price">$120</span>
                          <div class="close"><i class="fas fa-times"></i></div>
                        </li>
                        <!-- Single -->
                        <li class="cart-list-single">
                          <img src="../resources/img/product/2.jpg" alt="img">
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
                <h2><a href="./index.jsp"><img src="./resources/img/logo.png"></a></h2>
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
                          <img src="../resources/img/product/1.jpg" alt="img">
                          <h5><a href="#">simple product</a></h5>
                          <span class="price">$120</span>
                          <div class="close"><i class="fas fa-times"></i></div>
                        </li>
                        <!-- Single -->
                        <li class="cart-list-single">
                          <img src="../resources/img/product/2.jpg" alt="img">
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
                    <li><a href="../index.jsp">Home</a></li>
                    <li><a href="./about.jsp">About</a></li>
                    <li><a href="../shop">Shop</a></li>
                    <li><a href="./privacy-policy.jsp">Privacy Policy</a></li>
                    <li><a href="./faq.jsp">Faq</a></li>
                    <li><a href="./contact.jsp">Contact</a></li>
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
    <!-- Start Mobile Menu Area -->
    <div class="mobile-menu-area">

      <!--offcanvas menu area start-->
      <div class="off_canvars_overlay">

      </div>
      <div class="offcanvas_menu">
        <div class="offcanvas_menu_wrapper">
          <div class="canvas_close">
            <a href="javascript:void(0)"><i class="fas fa-times"></i></a>
          </div>
          <div class="logo">
                <h2><a href="./index.jsp"><img src="./resources/img/logo.png"></a></h2>
          </div>
          <div id="menu" class="text-left ">
            <ul class="offcanvas_main_menu">
              <li class="menu-item-has-children">
                <a href="index.jsp">Home</a>
              </li>
              <li class="menu-item-has-children">
                <a href="about.jsp">about Us</a>
              </li>
              <li class="menu-item-has-children">
                <a href="#">Page</a>
                <ul class="sub-menu">
                  <li><a href="cart.jsp">Cart</a></li>
                  <li><a href="wishlist.jsp"> Wishlist</a></li>
                  <li><a href="checkout.jsp">Checkout</a></li>
                  <li><a href="login">Login</a></li>
                  <li><a href="register">Register</a></li>
                  <li><a href="reset-password.jsp">Reset Password</a></li>
                  <li><a href="privacy-policy.jsp">Privacy Policy</a></li>
                  <li><a href="terms-condition.jsp">Terms & Condition</a></li>
                  <li><a href="404.jsp">404 Error</a></li>
                  <li><a href="faq.jsp">Faq</a></li>
                </ul>
              </li>
              <li class="menu-item-has-children">
                <a href="#">Shop</a>
                <ul class="sub-menu">
                  <li><a href="shop.jsp">Shop</a></li>
                  <li><a href="shop2-columns.jsp">Shop 2 Columns</a></li>
                  <li><a href="shop-grid.jsp">Shop Grid</a></li>
                  <li><a href="shop-left-sidebar.jsp">Shop Left Sidebar</a></li>
                  <li><a href="shop-list.jsp">Shop List</a></li>
                </ul>
              </li>
              <li class="menu-item-has-children">
                <a href="#">Elements</a>
                <ul class="sub-menu">
                  <li class="menu-item-has-children">
                    <a href="#">Elements</a>
                    <ul class="sub-menu">
                      <li><a href="element-infobox.jsp">Element Info Box</a></li>
                      <li><a href="element-breadcrumb.jsp">Element Breadcrum</a></li>
                      <li><a href="element-heading.jsp">Element Headding</a></li>
                      <li><a href="element-post.jsp">Element Post Element</a></li>
                      <li><a href="element-pricing.jsp">Element Pricing</a></li>
                    </ul>
                  </li>
                  <li class="menu-item-has-children">
                    <a href="#">Elements</a>
                    <ul class="sub-menu">
                      <li><a href="element-product-category.jsp">Element Product Category</a></li>
                      <li><a href="element-product-style.jsp">Element Product Style</a></li>
                      <li><a href="element-product-tab.jsp">Element Product Tab</a></li>
                      <li><a href="element-team-style.jsp">Element Team</a></li>
                      <li><a href="element-testimonial.jsp">Element Testimonial</a></li>
                    </ul>
                  </li>
                  <li class="menu-item-has-children">
                    <a href="#">Elements</a>
                    <ul class="sub-menu">
                      <li><a href="shop.jsp">Element Shop</a></li>
                      <li><a href="shop2-columns.jsp">Element Shop 2 Columns</a></li>
                      <li><a href="shop-grid.jsp">Element Shop Grid</a></li>
                      <li><a href="shop-left-sidebar.jsp">Element Shop Left Sidebar</a></li>
                      <li><a href="shop-list.jsp">Element Shop List</a></li>
                    </ul>
                  </li>
                  <li class="menu-item-has-children">
                    <a href="#">Elements</a>
                    <ul class="sub-menu">
                      <li><a href="product-details.jsp">Element Shop Single</a></li>
                      <li><a href="cart.jsp">Element Cart Page</a></li>
                      <li><a href="checkout.jsp">Element CheckOut Page</a></li>
                      <li><a href="wishlist.jsp">Element Wishlist</a></li>
                    </ul>
                  </li>
                </ul>
              </li>
              <li class="menu-item-has-children">
                <a href="#">Blog</a>
                <ul class="sub-menu">
                  <li><a href="blog.jsp">Blog</a></li>
                  <li><a href="blog-grid.jsp">Blog Grid</a></li>
                  <li><a href="single.jsp">Blog Single</a></li>
                </ul>
              </li>
              <li class="menu-item-has-children">
                <a href="contact.jsp"> Contact Us</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <!--offcanvas menu area end-->
    <!-- End Mobile Menu Area -->
    <!-- Start BreadCrumb Area -->
    <div class="breadcrumb-area pt-100 pb-100" style="background-image: url('./resources/img/breadcrumb.jpg');">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="breadcrumb-content">
              <h2>Product Management</h2>
              <ul>
                <li><a href="index.jsp">Home</a></li>
                <li class="active">Product Management</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- End BreadCrumb Area -->

    <!-- Start Shop Area -->
    <section class="shop-area pt-70 pb-70">
      <div class="container">
        <div class="row">
          <div class="col-lg-8">
            <!-- Shop Top Pagination -->
            <div class="row section-bg pt-20 pb-20 mb-30">
              <div class="col-lg-7 col-md-6 order-2 order-md-1">
                <div class="top-bar-left">
                  <div class="product-view-mode">
                    <a href="shop-list.jsp"  class="active"><i class="fa fa-list"></i></a>
                  </div>
                  <div class="product-amount">
                    <p>Showing 16 of 21 results</p>
                  </div>
                </div>
              </div>
              <div class="col-lg-5 col-md-6 order-1 order-md-2">
                <div class="top-bar-right">
                  <select class="form-select" aria-label="Default select example">
                    <option selected>Sort by popularity</option>
                    <option value="1">Sort by Name</option>
                    <option value="2">Sort by Price</option>
                    <option value="3">Sort by Ratting</option>
                  </select>
                </div>
              </div>
            </div>
            <!-- Shop -->
            <div class="col-lg-12 mb-30">
                <a href="product-management?event=send-to-add"><button type="button" class="btn btn-warning">ADD PRODUCT!</button></a>
            </div>
            <div class="row">
              <!-- Product Single -->
              <%for (Product product : listProduct) {%>
              <div class="col-lg-12 mb-30">
                <div class="product-single-list-view">
                  <div class="row">
                    <div class="col-lg-4 col-sm-5">
                      <div class="product-thumbnail-list-view">
                        <a href="product-details.jsp"><img src="<%=product.getImageURL()%>" alt="product" class="img-fluid" style="object-fit: contain; width: 250px; height: 250px;"></a>
                      </div>
                    </div>
                    <div class="col-lg-8 col-sm-7">
                      <div class="product-content-list-view">
                          <h4><a href="product-management?event=product-detail&product-id=<%=product.getProductID()%>"><%=product.getProductName()%></a></h4>
                        <div class="pricing">
                            <%if(product.getDiscount()>0){%>
                          <span>$<%=product.getPrice()-product.getPrice()*product.getDiscount()/100%> <del>$<%=product.getPrice()%></del></span>
                          <%}else{%>
                          <span>$<%=product.getPrice()%></span>
                          <%}%>
                        </div>
                        <div class="ratting">
                          <span><i class="fas fa-star"></i></span>
                          <span><i class="fas fa-star"></i></span>
                          <span><i class="fas fa-star"></i></span>
                          <span><i class="fas fa-star"></i></span>
                          <span><i class="fas fa-star"></i></span>
                        </div>
                        <p><%=product.getDescription()%></p>
                        <ul class="d-flex">
                            <li><a href="#" onclick="handleProduct('<%=product.getProductID()%>','product-edit')"><img src="./resources/img/pen.png" width="30px" height="30px" alt="alt"/></a></li>
                            <li><a href="#" onclick="handleProduct('<%=product.getProductID()%>','product-detail')"><i class="far fa-eye"></i></a></li>
                            <li><a href="#" onclick="handleProduct('<%=product.getProductID()%>','product-delete')"><img src="./resources/img/bin.png" width="30px" height="30px" alt="alt"/></a></li>
                        </ul>
                      </div>
                    </div>
                  </div>

                </div>
              </div>
              <%}%>

            </div>
            <!-- Pagination -->
            <div class="row">
              <div class="col-12 mb-30">
                <div class="page-pagination text-center">
                  <ul>
                      <%int pageNum = (productDao.getPageNumBySeller(curUser.getId())%9==0)?productDao.getPageNumBySeller(curUser.getId())/9:productDao.getPageNumBySeller(curUser.getId())/9+1;
                      int pageID = (int) request.getAttribute("pageid");
                      for(int i=1;i<=pageNum;i++){ 
                      if(i==pageID){
                      %><li><span><%=i%></span></li>
                      <%}else{%>
                      <li><a href="./product-management?event=product-management&page=<%=i%>"><%=i%></a></li><%}%>
                      <%}%>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <!-- Siderbar -->
          <div class="col-lg-4">
            <!-- Single -->
            <div class="sidebar-widgets">
              <h4 class="title">Search</h4>
              <form action="#">
                <input type="search" name="search" placeholder="Search Here.">
                <button type="submit"><i class="fas fa-search"></i></button>
              </form>
            </div>
            <!-- Single -->
            <div class="sidebar-widgets">
              <h4 class="title">Latest Products</h4>
              <div class="widgets-latest-product-full">
                <!-- Single -->
                <div class="widgets-latest-product-single mb-30">
                  <div class="thumbanil">
                    <a href="#">
                      <img src="./resources/img/product/1.jpg" alt="Products">
                    </a>
                  </div>
                  <div class="content">
                    <h4><a href="#">Homasy Portable</a></h4>
                    <div class="pricing">
                      <span>$200 <del>$210</del></span>
                    </div>
                    <div class="ratting">
                      <span><i class="fas fa-star"></i></span>
                      <span><i class="fas fa-star"></i></span>
                      <span><i class="fas fa-star"></i></span>
                      <span><i class="fas fa-star"></i></span>
                      <span><i class="fas fa-star"></i></span>
                    </div>
                  </div>
                </div>
                <!-- Single -->
                <div class="widgets-latest-product-single mb-30">
                  <div class="thumbanil">
                    <a href="#">
                      <img src="./resources/img/product/2.jpg" alt="Products">
                    </a>
                  </div>
                  <div class="content">
                    <h4><a href="#">Homasy Portable</a></h4>
                    <div class="pricing">
                      <span>$200 <del>$210</del></span>
                    </div>
                    <div class="ratting">
                      <span><i class="fas fa-star"></i></span>
                      <span><i class="fas fa-star"></i></span>
                      <span><i class="fas fa-star"></i></span>
                      <span><i class="fas fa-star"></i></span>
                      <span><i class="fas fa-star"></i></span>
                    </div>
                  </div>
                </div>
                <!-- Single -->
                <div class="widgets-latest-product-single mb-30">
                  <div class="thumbanil">
                    <a href="#">
                      <img src="./resources/img/product/3.jpg" alt="Products">
                    </a>
                  </div>
                  <div class="content">
                    <h4><a href="#">Homasy Portable</a></h4>
                    <div class="pricing">
                      <span>$200 <del>$210</del></span>
                    </div>
                    <div class="ratting">
                      <span><i class="fas fa-star"></i></span>
                      <span><i class="fas fa-star"></i></span>
                      <span><i class="fas fa-star"></i></span>
                      <span><i class="fas fa-star"></i></span>
                      <span><i class="fas fa-star"></i></span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- Single -->
            <div class="sidebar-widgets">
              <h4 class="title">Blog categories</h4>
              <ul>
                <li><a href="#">All about Digital</a></li>
                <li><a href="#">Smartphone & Tablett</a></li>
                <li><a href="#">Camera</a></li>
                <li><a href="#">Printer & ink</a></li>
                <li><a href="#">Cameras</a></li>
              </ul>
            </div>
            <!-- Single -->
            <div class="sidebar-widgets">
              <h4 class="title">Tags</h4>
              <div class="tags">
                <a href="#">camera</a>
                <a href="#">Computer</a>
                <a href="#">Mobile</a>
                <a href="#">Bag</a>
                <a href="#">shoes</a>
                <a href="#">Store</a>
                <a href="#">smartphone</a>
                <a href="#">Watch</a>
              </div>
            </div>
            <!-- Single -->
            <div class="sidebar-widgets">
              <h4 class="title">Recent Post</h4>
              <ul>
                <li><a href="#">Gallery Post with Supported Animation</a></li>
                <li><a href="#">Announcement – Standard Post without Image</a></li>
                <li><a href="#">We’re the best Designers from UK</a></li>
                <li><a href="#">A Beautiful Day – Standard Post with Image</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Shop Area -->

    <%@include file="../views/components/footer_component.jsp" %>



    <div class="scroll-area">
      <i class="fa fa-angle-up"></i>
    </div>

<%}%>
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
    <script src="./resources/js/product-manage-script.js"></script>
  </body>
</html>

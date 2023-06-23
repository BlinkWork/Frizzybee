<%@page import="database.*, java.util.List, model.*, java.text.DecimalFormat, java.io.IOException" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>BulkShop - Electronics Shop HTML Template | Shop</title>
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
    <%@include file="../views/servletComponents/header_component.jsp" %>


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
          <div class="mobile-logo">
            <h2><a href="./index.jsp"><img src="./resources/img/logo.png"></a></h2>
          </div>
          <div id="menu" class="text-left ">
            <ul class="offcanvas_main_menu">
              <li class="menu-item-has-children">
                <a href="./index.jsp">Home</a>
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
              <h2>Shop</h2>
              <ul>
                <li><a href="./index.jsp">Home</a></li>
                <li class="active">Shop</li>
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
              <div class="col-lg-7 col-md-6 order-2 order-md-1" hidden>
                <div class="top-bar-left">
                  <div class="product-view-mode">
                    <a href="shop.jsp" class="active"><i class="fa fa-th"></i></a>
                    <a href="shop-list.jsp"><i class="fa fa-list"></i></a>
                  </div>
                  <div class="product-amount">
                    <p>Showing 9 of 21 results</p>
                  </div>
                </div>
              </div>
              <div class="col-lg-5 col-md-6 order-1 order-md-2">
                <div class="top-bar-right">
                  <select id="sort-select" class="form-select" aria-label="Default select example">
                    <option value="1">Sort by Name Ascending</option>
                    <option value="2">Sort by Name Descending</option>
                    <option value="3">Sort by Price Ascending</option>
                    <option value="4">Sort by Price Descending</option>
                  </select>
                </div>
              </div>
            </div>
            <!-- Shop -->

            <div id="productList">
              <%!
              public void printHTML(Product product, JspWriter out) throws IOException {
                DecimalFormat df = new DecimalFormat("#.##");

                double price = product.getPrice() * (100 - product.getDiscount()) / 100;
                String formattedPrice = df.format(price);

                double priceBefore = product.getPrice();
                String formattedPriceBefore = df.format(priceBefore);
                out.println(
                  "<div class='col-lg-4 col-md-4 col-sm-6 mb-30'>"
                    + "<div class='product-single'>"
                      + "<div class='product-thumbnail'>"
                        + "<a href='./productDetails?id=" + product.getProductID() + "'><img src='"+ product.getImageURL() + "' alt='product' style='width:200px;'></a>"
                        + "<div class='product-thumbnail-overly'>"
                          + "<ul>"
                            + "<li><a href='cart.jsp'><i class='fas fa-shopping-cart'></i></a></li>"
                            + "<li><a href='wishlist.jsp'><i class='far fa-heart'></i></a></li>"
                            + "<li><a href='./productDetails?id=" + product.getProductID() + "'><i class='far fa-eye'></i></a></li>"
                          + "</ul>"
                        + "</div>"
                      + "</div>"
                      + "<div class='product-content'>"
                        + "<h4><a href='./productDetails?id=" + product.getProductID() + "'>" + product.getProductName() +"</a></h4>"
                        + "<div class='pricing'>"
                          + "<span class='priceDiscount' style='display: flex; flex-direction: column;'>" + formattedPrice + " <del>" + formattedPriceBefore + "</del></span>"
                        + "</div>"
                      + "</div>"
                    + "</div>"
                  + "</div>");
              }
              %>

              <%
                  List<Product> listProduct = (List<Product>) request.getAttribute("listProduct");
                  if(listProduct != null){
                    for(int i = 0; i < listProduct.size(); i+=3){
                      out.println("<div class='row'>");
                      printHTML(listProduct.get(i), out);
                      if(i + 1 < listProduct.size()){
                        printHTML(listProduct.get(i+1), out);
                      }
                      if(i + 2 < listProduct.size()){
                        printHTML(listProduct.get(i+2), out);
                      }
                      out.println("</div>");
                    }
                  }
              %>
            </div>

            <!-- Pagination -->
            <div class="row">
              <div class="col-12 mb-30">
                <div class="page-pagination text-center">
                  <ul>
                    <!--<li class="page-item"><a href="#"><i class="fa fa-angle-left"></i></a></li>-->
                    <li class="page-item"><a style="cursor: pointer">1</a></li>
                    <li class="page-item"><a style="cursor: pointer">2</a></li>
                    <li class="page-item"><a style="cursor: pointer">3</a></li>
                    <li class="page-item"><a style="cursor: pointer">4</a></li>
                    <li class="page-item"><a style="cursor: pointer">5</a></li>
                    <li class="page-item"><a style="cursor: pointer">6</a></li>
                    <li class="page-item"><a style="cursor: pointer">7</a></li>
                    <!--<li class="page-item"><a href="#"><i class="fa fa-angle-right"></i></a></li>-->
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
              <form action="searching">
                <input type="search" name="search" placeholder="Search Here...">
                <button type="submit"><i class="fas fa-search"></i></button>
              </form>
            </div>
            <!-- Single -->
            <div class="sidebar-widgets">
              <h4 class="title">Latest Products</h4>
              <div class="widgets-latest-product-full">
                <!-- Single -->
                <%
                  DecimalFormat df = new DecimalFormat("#.##");
           
                  List<Product> latestProducts = (List<Product>) request.getAttribute("latestProducts");
                  if(latestProducts != null){
                    for(Product product : latestProducts){
                      double price = product.getPrice() * (100 - product.getDiscount()) / 100;
                      String formattedPrice = df.format(price);

                      double priceBefore = product.getPrice();
                      String formattedPriceBefore = df.format(priceBefore);
                      out.println(
                        "<div class='widgets-latest-product-single mb-30'>"
                        + "<div class='thumbanil'>"
                        + " <a href='./productDetails?id=" + product.getProductID() + "'>"
                        + "   <img src='"+ product.getImageURL() + "' alt='Products'>"
                        + " </a>"
                        + "</div>"
                        + "<div class='content'>"
                        + " <h4><a href='./productDetails?id=" + product.getProductID() + "'>" + product.getProductName() + "</a></h4>"
                        + "<div class='pricing'>"
                        + " <span>" + formattedPrice + " <del>" + formattedPriceBefore + "</del></span>"
                        + "</div>"
                        + "<div class='ratting'>"
                        + " <span><i class='fas fa-star'></i></span>"
                        + " <span><i class='fas fa-star'></i></span>"
                        + " <span><i class='fas fa-star'></i></span>"
                        + " <span><i class='fas fa-star'></i></span>"
                        + " <span><i class='fas fa-star'></i></span>"
                        + "</div>"
                        + "</div>"
                        + "</div>"
                      );
                    }
                  }
                %>
              </div>
            </div>
            <!-- Single -->
            <div class="sidebar-widgets">
              <h4 class="title">Tags</h4>
              <div class="tags">
                <a href="#">Laptop</a>
                <a href="#">Smart Phone</a>
                <a href="#">Tablet</a>

              </div>
            </div>           
          </div>
        </div>
      </div>
    </section>
    <!-- End Shop Area -->



    <%@include file="../views/servletComponents/footer_component.jsp" %>



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
    <script src="./resources/js/shop.js"></script>

    <script>
      let priceArray = document.querySelectorAll('.priceDiscount');
      priceArray.forEach(function (item)
      {
        let dualString = item.textContent.split(' ');
        if (dualString[0] === dualString[1]) {
          item.children[0].remove();
        }
      });


    </script>

  </body>

</html>

<%@page import="database.*, java.util.List, model.*, java.text.DecimalFormat, java.io.IOException" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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


  </head>

  <body>
    <%@include file="../views/servletComponents/header_component.jsp" %>

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
              <div class="col-lg-7 col-md-6 order-2 order-md-1">
                <div class="top-bar-left">
                  <div class="product-view-mode">
                    <a href="shop.jsp" class="active"><i class="fa fa-th"></i></a>
                  </div>
                  <%
                    int recordNumbers = (Integer) request.getAttribute("recordNumbers");
                    int recordShow = recordNumbers;
                    if(recordNumbers > 9){
                      recordShow = 9;
                    }
                  %>
                  
                  <div class="product-amount">
                    <p>Showing <%=recordShow%> of <%=recordNumbers%> results</p>
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
                <div class="bottom-bar-right">
                  <button type="submit" class="btn button-2" data-bs-toggle="modal" data-bs-target="#myModal"
                          id="clearSelection" style="padding: 5px 10px; float: right; margin-top: 5px;">
                   Clear Selection
                  </button>
                </div> 
                
              </div>
              
            </div>
            
            <!-- Modal -->
              <div class="modal fade" id="modal--warning--login" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <%
                     
                      
                      if(userName == null || userName.trim().isEmpty()){
                        out.println(""
                        + "<div class='modal-content'>"
                        + "<div class='modal-header'>"
                        + "<h5 class='modal-title' id='myModalLabel'>Warning</h5>"
                        + "<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Close'></button>"
                        + "</div>"
                        + "<div class='modal-body warning-login'>"
                        + "You must login before add something to cart !!!"
                        + "</div>"
                        + "<div class='modal-footer'>"
                        + "<button type='button' id='login--function' class='btn btn-warning warning-login'>Login</button>"
                        + "</div>"
                        + "</div>"
                        + "");
                      }
                      else{
                        out.println(""
                        + "<div class='modal-content'>"
                            + "<div class='modal-header'>"
                            + "<h5 class='modal-title' id='myModalLabel'>Add to cart successfully</h5>"
                            + "<button type='button' class='btn-close' data-bs-dismiss='modal' aria-label='Close'></button>"
                            + "</div>"
                            + "<div class='modal-body'>"
                            + "Your item has been added to the cart."
                            + "</div>"
                            + "<div class='modal-footer'>"
                            + "<button type='button' data-bs-dismiss='modal' class='btn btn-primary'>Continue to Buy</button>"
                            + "<button type='button' id='view--cart' class='btn btn-warning' style='color: white;'>View Cart</button>"
                            + "</div>"
                            + "</div>"
                        + "");
                      }
                  %>
                  
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
                            + "<li><a href='' class='addCart' id='cart_" + product.getProductID() + "'><i class='fas fa-shopping-cart'></i></a></li>"
                            + "<li><a href='wishlist.jsp'><i class='far fa-heart'></i></a></li>"
                            + "<li><a href='./productDetails?id=" + product.getProductID() + "'><i class='far fa-eye'></i></a></li>"
                          + "</ul>"
                        + "</div>"
                      + "</div>"
                      + "<div class='product-content'>"
                        + "<h4><a href='./productDetails?id=" + product.getProductID() + "'>" + product.getProductName() +"</a></h4>"
                        + "<div class='pricing'>"
                          + "<span class='priceDiscount' style='display: flex; flex-direction: column;'>" + formattedPrice + "$<del>" + formattedPriceBefore + "$</del></span>"
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
                    <%
                      if(listProduct != null){
                        if(listProduct.size() > 0){
                          int number = (Integer)request.getAttribute("pageNumbers");
                          for(int i = 1; i <= number ; i++){
                            if(i == 1){
                              out.println(
                                "<li class='page-item'><a href=''> <span>" + i + "</span></a></li>"
                              );
                            }
                            else{
                              out.println(
                              "<li class='page-item'><a href=''>" + i + "</a></li>"
                              );
                            }
                          }
                        }
                      }
                    %>
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
              <form id="form-searching" action="shop">
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
                        + " <span>" + formattedPrice + "$<del>" + formattedPriceBefore + "$</del></span>"
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
              <div class="tags category-tags">
                <a href="">Laptop</a>
                <a href="">Phone</a>
                <a href="">Computer</a>
                <a href="">Smart Watch</a>
                <a href="">Headphones</a>
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
    <script src="./resources/js/cart.js"></script>

    <script>
      let priceArray = document.querySelectorAll('.pricing');
      priceArray.forEach(function (item)
      {
        let dualString = item.innerHTML.split('<del>');
        if (dualString[0] === item.querySelector("del").textContent) {
          item.children[0].remove();
        }
      });
      let pricing = document.querySelectorAll('.pricing span');
      pricing.forEach(function (item)
      {
        let dualString = item.innerHTML.split('<del>');
        if (dualString[0] === item.querySelector("del").textContent) {
          item.children[0].remove();
        }
      });
    </script>

  </body>

</html>

<%@page import = "model.*" %>
<%@page import = "database.*" %>
<%@page import = "java.util.*" %>
<%@page import = "java.text.DecimalFormat" %>

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
    
         <%@include file="../views/servletComponents/header_component.jsp" %>

        <%
            ProductDAO productDao = new ProductDAO();
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
        <%}%>
        
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
                 <a href="product-management?event=send-to-add"><button type="button" class="btn btn-warning">ADD PRODUCT!</button>
                </a>
                  
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
    <script src="./resources/js/product-manage-script.js"></script>
    <script>
      let pricing = document.querySelectorAll('.pricing span');
      pricing.forEach(function (item)
      {
 
        let dualString = item.innerHTML.split('<del>');
        
        if (dualString[0] === item.querySelector("del").textContent) {
          item.querySelector("del").remove();
        }
      });
    </script>
  </body>
</html>

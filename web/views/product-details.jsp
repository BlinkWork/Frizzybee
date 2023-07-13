<%@page import="database.*, java.util.List, model.*, java.text.DecimalFormat, java.io.IOException" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="./resources/css/jquery.rateyo.css">
   
    <style>
      .hidden {
        display: none !important;
      }
    </style>
    
  </head>
  <body>
    <%@include file="../views/servletComponents/header_component.jsp" %>


    
    <!-- Start BreadCrumb Area -->
    <div class="breadcrumb-area pt-100 pb-100" style="background-image: url('./resources/img/breadcrumb.jpg');">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="breadcrumb-content">
              <h2>Product Details</h2>
              <ul>
                <li><a href="index.jsp">Home</a></li>
                <li class="active">Product Details</li>
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
          <%
            Product product = (Product)request.getAttribute("productDetail");
          %>
        <!-- Product Details -->
        <div class="row">
          <div class="col-md-5 col-lg-6">
            <div class="modal_tab">
              <div class="tab-content product-details-large">
                <div class="tab-pane fade show active" id="detailstab1" role="tabpanel" >
                  <div class="modal_tab_img">
                    <a href="#"><img src="<%=product.getImageURL()%>" alt="img"></a>
                  </div>
                </div>
              </div>

            </div>
          </div>

         
          <%
            DecimalFormat df = new DecimalFormat("#.##");

            double price = product.getPrice() * (100 - product.getDiscount()) / 100;
            String formattedPrice = df.format(price);

            double priceBefore = product.getPrice();
            String formattedPriceBefore = df.format(priceBefore);
 
            double avgRate = (Double) request.getAttribute("avgRate");
 
            String rateText = "";
            int i = 1;
            for (; i <= avgRate; i++) {
                rateText += "<span><i class='fas fa-star'></i></span>";
            }
            for (; i <= 5; i++) {
                rateText += "<i class='fas fa-star'></i>";
            }

          %>          
          <div class="col-md-7 col-lg-6">
            <div class="product-details-img-full">
              <h2><%=product.getProductName()%></h2>

              <div class="ratting">
                <%
                    out.println(rateText);
                %>
              </div>

              <div class="pricing" style="margin: 20px 0;">
                <span style="font-size: 40px"><%=formattedPrice%>$<del><%=formattedPriceBefore%>$</del></span>
              </div>
              
              
              <!-- Modal -->
              <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
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
                            + "<button type='button' id='back--shop' class='btn btn-primary'>Continue to Buy</button>"
                            + "<button type='button' id='view--cart' class='btn btn-warning' style='color: white;'>View Cart</button>"
                            + "</div>"
                            + "</div>"
                        + "");
                      }
                  %>
                </div>
              </div>

              
              <div class="modal_add_to_cart">
                <form action="./cart" id="cart-view">
                  <span class="quantity">
                    <input type='number' min='1' max='<%=product.getQuantity()%>' step='1' name='quantity' value='1'>
                  </span>
                  <button type="submit" class="btn button-1 addToCartBtn" data-bs-toggle="modal" data-bs-target="#myModal">
                    Add to cart
                  </button>
                  <button type="submit" class="btn button-1 addToCartBtn">
                    Add to cart not notify
                  </button>
                </form>
              </div>
              <h3>Share This Product</h3>
              <ul class="share-product">
                <li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
                <li><a href="#"><i class="fab fa-instagram"></i></a></li>
                <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                <li><a href="#"><i class="fab fa-linkedin"></i></a></li>
                <li><a href="#"><i class="fab fa-pinterest"></i></a></li>
              </ul>
            </div>
          </div>
        </div>
        <div class="row mt-50 section-bg">
          <div class="shop-details-full-content">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
              <li class="nav-item" role="presentation">
                <button class="nav-link active" id="Description-tab" data-bs-toggle="tab" data-bs-target="#Description" type="button" role="tab" aria-controls="Description" aria-selected="true">Description</button>
              </li>
              <li class="nav-item" role="presentation">
                <button class="nav-link" id="information-tab" data-bs-toggle="tab" data-bs-target="#information" type="button" role="tab" aria-controls="information" aria-selected="false" hidden>Additional Information</button>
              </li>
              <li class="nav-item" role="presentation">
                <button class="nav-link" id="Review-tab" data-bs-toggle="tab" data-bs-target="#Review" type="button" role="tab" aria-controls="Review" aria-selected="false">Review</button>
              </li>
            </ul>
            <div class="tab-content" id="myTabContent">
              <div class="tab-pane fade show active" id="Description" role="tabpanel" aria-labelledby="Description-tab">
                <p> <%=product.getDescription()%> </p>

              </div>
              <div class="tab-pane fade" id="information" role="tabpanel" aria-labelledby="information-tab" hidden>
                <table class="table table-bordered">
                  <tbody>
                    <tr>
                      <td>Capacity</td>
                      <td>5 Kg</td>
                    </tr>
                    <tr>
                      <td>Color</td>
                      <td>Black, Brown, Red,</td>
                    </tr>
                    <tr>
                      <td>Water Resistant</td>
                      <td>Yes</td>
                    </tr>
                    <tr>
                      <td>Material</td>
                      <td>Artificial Leather</td>
                    </tr>
                  </tbody>
                </table>
              </div>
                
                
              <div class="tab-pane fade" id="Review" role="tabpanel" aria-labelledby="Review-tab">
                <div class="product-review">
                  <div class="product-review-form">
                    <h3>Add a review</h3>
                   
                    <div id="rateUser"></div>
                    <div id="rating--score" hidden=" "></div>
                    <form action="#">
                      <textarea id="text--comment" name="review-message" class="form-control" placeholder="Your Review"></textarea>
                      <button id="submit--review" type="submit">Submit Review</button>
                    </form>
                  </div>
                  
                  <div class="product-review-list">
                    <ul>
                      <!-- Single -->
                      <li class="review-single">
                        <img src="./resources/img/avata-admin.jpg" alt="avatar">
                        <div class="review-info">
                          <h5>Alea Brooks</h5>
                          <small> Jun 01, 2021 </small>
                        </div>
                        <div class="ratting">
                          <span><i class="fas fa-star"></i></span>
                          <span><i class="fas fa-star"></i></span>
                          <span><i class="fas fa-star"></i></span>
                          <span><i class="fas fa-star"></i></span>
                          <span><i class="fas fa-star"></i></span>
                        </div>
                        <div class="revie-con">
                          <p>Lorem Ipsumin gravida nibh vel velit auctor aliquet. Aenean sollicitudin, lorem quis bibendum auctor, nisi elit consequat ipsum, nec sagittis sem nibh id elit. Duis sed odio sit amet nibh vulputate</p>
                        </div>
                      </li>
                      <!-- Single -->
 
                    </ul>
                    <div id="cmt--nav" style="display: flex; justify-content: space-evenly;">
                      <button class="btn button-2" id="view--more">
                        View More+
                      </button>
                      <button class="btn button-2" id="hide--comment" style="display: none;">
                        Hide Comment
                      </button>
                    </div>
                  </div>
                  <!-- Form -->


                </div>
              </div>
            </div>

          </div>
        </div>
      </div>
    </section>
    <!-- End Shop Area -->

    <%@include file="../views/servletComponents/footer_component.jsp" %>



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
    <script src="./resources/js/cart.js"></script>
    <script src="./resources/js/review.js"></script>
    <script src="./resources/js/jquery.rateyo.js"></script>
    <script>
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

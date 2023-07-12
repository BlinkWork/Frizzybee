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
              <h2>Cart</h2>
              <ul>
                <li><a href="index.jsp">Home</a></li>
                <li class="active">Cart Page</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- End BreadCrumb Area -->

    <!-- Start Cart page -->
    <section class="cart-page pt-70 pb-70">
      <div class="container">
        <div class="row">
          <div class="col-12">
            <div class="cart-table table-responsive">
              <table class="table table-bordered align-middle text-center">
                <thead>
                  <tr>
                    <th class="pro-thumbnail">Thumbnail</th>
                    <th class="pro-title">Product</th>
                    <th class="pro-price">Price</th>
                    <th class="pro-quantity">Quantity</th>
                    <th class="pro-subtotal">Total</th>
                    <th class="pro-remove">Remove</th>
                  </tr>
                </thead>
                <tbody id="tbody--cart" class="cart-list-tbody">
                  <% String cartTable=(String) request.getAttribute("dataList"); if(request.getAttribute("dataList")
                    !=null){ if(cartTable.equals("not found")==false && cartTable !=null){ out.println(cartTable); } }
                    %>

                </tbody>
              </table>
            </div>
            <div class="cart-update-option d-block d-md-flex justify-content-between">
              <div class="apply-coupon-wrapper">
                <form action="#" method="post" class=" d-block d-md-flex">
                  <input type="text" placeholder="Enter Your Coupon Code" required="">
                  <button class="button-1">Apply Coupon</button>
                </form>
              </div>
              <div class="cart-update">
                <a href="#" class="button-1">Update Cart</a>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-5 ml-auto">
            <!-- Cart Calculation Area -->
            <div class="cart-calculator-wrapper section-bg mt-30">
              <div class="cart-calculate-items">
                <h3>Cart Totals</h3>
                <div class="table-responsive">
                  <table class="table">
                    <tbody>
                      <tr>
                        <td>Sub Total</td>
                        <td id="subTotal">$230</td>
                      </tr>
                      <tr>
                        <td>Shipping</td>
                        <td id="shipping">$10</td>
                      </tr>
                      <tr class="total">
                        <td>Total</td>
                        <td class="total-amount">$300</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
              <a href="./checkout" class="button-1">Proceed To Checkout</a>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Cart page -->

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


</body>

</html>
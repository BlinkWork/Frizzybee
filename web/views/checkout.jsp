<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>BulkShop - Electronics Shop HTML Template | Checkout</title>
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
  <style>
    .overlay {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.5);
      z-index: 1;
    }
  </style>
</head>

<body>
  <%@page import="java.text.DecimalFormat" %>
    <%@include file="../views/servletComponents/header_component.jsp" %>


      <!-- Start BreadCrumb Area -->
      <div class="breadcrumb-area pt-100 pb-100" style="background-image: url('./resources/img/breadcrumb.jpg');">
        <div class="container">
          <div class="row">
            <div class="col-lg-12">
              <div class="breadcrumb-content">
                <h2>Checkout</h2>
                <ul>
                  <li><a href="index.jsp">Home</a></li>
                  <li class="active">Checkout</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- End BreadCrumb Area -->

      <!-- Modal -->


      <!-- Start Chekout Page -->
      <section class="checkout-page-wrapper pt-70 pb-70" style="position: relative;">
        <div class="container">
          <div class="row">
            <div class="col-lg-6">
              <div class="checkout-billing-details-wrap">
                <h2>Billing Details</h2>
                <div class="billing-form-wrap">
                  <form action="#">
                    <div class="row">
                      <div class="col-md-6">
                        <div class="single-input-item">
                          <label for="f_name" class="required">First Name</label>
                          <input type="text" id="f_name" placeholder="First Name" required="required">
                        </div>
                      </div>

                      <div class="col-md-6">
                        <div class="single-input-item">
                          <label for="l_name" class="required">Last Name</label>
                          <input type="text" id="l_name" placeholder="Last Name" required="required">
                        </div>
                      </div>
                    </div>
                    <div class="single-input-item">
                      <label for="l_eamil" class="required">Email Address</label>
                      <input type="email" id="l_eamil" placeholder="Email Address" required="required">
                    </div>
                    <div class="single-input-item">
                      <label for="company_name" class="required"> Company Name </label>
                      <input type="text" id="company_name" placeholder="Company Name ">
                    </div>
                    <div class="single-input-item">
                      <label for="Country" class="required"> Country </label>
                      <select class="form-select" id="Country">
                        <option selected>Bangladesh</option>
                        <option value="1">India</option>
                        <option value="2">Pakistan</option>
                        <option value="3">London</option>
                      </select>
                    </div>
                    <div class="single-input-item">
                      <label for="Street_address" class="required"> Street address Line 1</label>
                      <input type="text" id="Street_address" placeholder="Street address Line 1">
                    </div>
                    <div class="single-input-item">
                      <label for="Street_address_2" class="required"> Street address Line 2</label>
                      <input type="text" id="Street_address_2" placeholder="Street address Line 2">
                    </div>
                    <div class="single-input-item">
                      <label for="city" class="required"> Town / City </label>
                      <input type="text" id="city" placeholder=" Town / City ">
                    </div>
                    <div class="single-input-item">
                      <label for="Divition" class="required">State / Divition </label>
                      <input type="text" id="Divition" placeholder=" State / Divition ">
                    </div>
                    <div class="single-input-item">
                      <label for="postcode" class="required">Postcode / ZIP</label>
                      <input type="text" id="postcode" placeholder=" Postcode / ZIP ">
                    </div>
                    <div class="single-input-item">
                      <label for="phone" class="required">Phone</label>
                      <input type="text" id="phone" placeholder="Phone">
                    </div>

                    <div class="single-input-item">
                      <label for="order_note" class="required">Order Note</label>
                      <textarea name="order_note" id="order_note" placeholder="Order Note"></textarea>
                    </div>
                  </form>
                </div>
              </div>
            </div>
            <div class="col-lg-6">
              <div class="order-summary-details">
                <h2>Your Order Summary</h2>
                <div class="order-summary-content">
                  <div class="order-summary-table table-responsive text-center">
                    <table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>Products</th>
                          <th>Total</th>
                        </tr>
                      </thead>
                      <tbody id="tbody-product--cart">
                        <% double totalPrice=0; String data=(String) request.getAttribute("dataCart"); if(data !=null){
                          String[] items=data.split("@"); for(String item : items){ String[] attributes=item.split("_");
                          out.println("" + "<tr>" + "<td><a href='#'>" + attributes[0] + " <strong> X " + attributes[1]
                          + "</strong></a>" + "</td>" + "<td>" + attributes[2] + "</td>" + "</tr>" + "" ); String[]
                          subtotalArr=attributes[2].substring(1).split(","); String subtotal=subtotalArr[0] + "." +
                          subtotalArr[1]; totalPrice +=Double.parseDouble(subtotal); } } DecimalFormat df=new
                          DecimalFormat("#.##"); String subPriceAmount=df.format(totalPrice); %>
                      </tbody>
                      <tfoot>
                        <tr>
                          <td>Sub Total</td>
                          <td id="subPrice"><strong>$<%=subPriceAmount%></strong></td>
                        </tr>
                        <tr>
                          <td>Shipping</td>
                          <td class="d-flex justify-content-left">
                            <ul class="shipping-type">
                              <li>
                                <div class="custom-control custom-radio">
                                  <input type="radio" id="expressShipping" name="shipping" class="custom-control-input"
                                    checked="">
                                  <label class="custom-control-label" for="flatrate">Express: $20.00</label>
                                </div>
                              </li>
                              <li>
                                <div class="custom-control custom-radio">
                                  <input type="radio" id="normalShipping" name="shipping" class="custom-control-input">
                                  <label class="custom-control-label" for="freeshipping">Normal: $10.00</label>
                                </div>
                              </li>
                            </ul>
                          </td>
                        </tr>
                        <tr>
                          <td>Total Amount</td>
                          <td id="totalAmount"><strong>$470</strong></td>
                        </tr>
                      </tfoot>
                    </table>
                    <div class="form-group payment">
                      <h4 class="deal-title">Payment Method</h4>
                      <ul>
                        <li>
                          <div class="custom-control custom-radio">
                            <input type="radio" id="bankTransfer" name="payment" class="custom-control-input"
                              checked="">
                            <label class="custom-control-label" for="bankTransfer">Cash on Delivery</label>
                          </div>
                        </li>
                        <li>
                          <div class="custom-control custom-radio">
                            <input type="radio" id="delivery" name="payment" class="custom-control-input">
                            <label class="custom-control-label" for="delivery">Direct Bank Transder</label>
                          </div>
                        </li>
                      </ul>
                      <h4 class="mb-4">Transfer Information</h4>
                      <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">#</th>
                            <th scope="col">Recipient's Name</th>
                            <th scope="col">Bank Name</th>
                            <th scope="col">Account Number</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <th scope="row">1</th>
                            <td>Nguyen Van Dat</td>
                            <td>MBBank</td>
                            <td>123456789</td>
                          </tr>
                          <tr>
                            <th scope="row">2</th>
                            <td>Le Minh Thang</td>
                            <td>MBBank</td>
                            <td>987654321</td>
                          </tr>
                          <tr>
                            <th scope="row">3</th>
                            <td>Bui Chung Hieu</td>
                            <td>MBBank</td>
                            <td>987654321</td>
                          </tr>
                        </tbody>
                      </table>
                      <span class="grand-total">Grand Total : <span>$100.00</span></span>
                      <button type="button" class="btn button-1 proceedOrder" data-bs-toggle="modal"
                        data-bs-target="#exampleModal">
                        Place Order Now
                      </button>

                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- End Chekout Page -->

      <div id="preloader" class="preeloader loadPlaced" style="background: transparent; display: none;">
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


      <div id="modalTemp" style="z-index: 20; display: none;">
        <div class="modal-dialog modal-dialog-centered modal-lg"
          style="display: block; position: absolute; top: 70rem; right: 0; left:  0; padding-bottom: 50px; z-index: 20;">
          <div class="modal-content">
            <div class="modal-body text-center" style="padding-bottom: 50px; z-index: 20;">
              <svg xmlns=" http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                stroke="currentColor" class="w-6 h-6" style="width: 200px; stroke: green;">
                <path stroke-linecap="round" stroke-linejoin="round"
                  d="M9 12.75L11.25 15 15 9.75M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>

              <h3 class="mt-3" style="color: #ff9c4a;">Order placed successfully!</h3>
              <div class="modal-footer">
                Waiting for second...
              </div>

            </div>
          </div>

        </div>
      </div>
      <div class="overlay" hidden></div>


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
        <script src="./resources/js/checkout.js"></script>

</body>

</html>
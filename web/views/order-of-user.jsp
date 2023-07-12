<%@page import = "model.*" %>
<%@page import = "database.*" %>
<%@page import = "java.util.*" %>

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
        <link rel="stylesheet" href="./resources/css/tabs.css">
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
                        <h2>My Order</h2>
                        <ul>
                            <li><a href="index.jsp">Home</a></li>
                            <li class="active">My Order</li>
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
                <div class="col-lg-12">
                    <!-- Shop Top Pagination -->
                    
                    <!-- Shop -->
                    
                    <div class="row" id="order-list">
                        <!-- Product Single -->
                        <%
          OrderDAO odao = new OrderDAO();
          OrderItemDAO oidao = new OrderItemDAO();
          UserDAO userDAO = new UserDAO();
          ProductDAO pdao = new ProductDAO();
          List<Order> listOrder = odao.getOrdersByUserID(curUser.getId()+"");
          for (Order order : listOrder) {%>
                        <div class="col-md-12 mb-30">
                            <div class="product-single-list-view fs-6">
                                <div class="row" style="background-color: rgb(245, 246, 250);border: 1px solid rgb(213, 213, 213);">
                                    <div class="col-md-12 row fw-medium">
                                        <div class="col-md-4"><p class="ps-4">Product</p></div>
                                        <div class="col-md-1"><p>Price</p></div>
                                        <div class="col-md-2"><p>Payment method</p></div>
                                        <div class="col-md-2"><p>Order date</p></div>
                                        <div class="col-md-2"><p>Delivery date</p></div>
                                        <div class="col-md-1"><p>Status</p></div>
                                    </div>
                                </div>
                                <div class="row">
                                         <%
                                         List<OrderItem> listOrderItem = oidao.getOrderItemsByOrderID(order.getOrderID()+"");
                                         double totalPrice = 0;
                                         for (OrderItem orderItem : listOrderItem) {
                                         Product product = orderItem.getProduct();
                                         double totalPriceOrderItem = orderItem.getQuantity() * (product.getPrice()-product.getDiscount()*product.getPrice()/100) ;
                                         totalPrice+=totalPriceOrderItem;
                                         %>
                                         
                                       <div  class="col-md-12 row fw-normal align-items-center align-items-stretch" style="background-color: rgb(255, 255, 255); " >
                                        <div class="col-md-4 row border-end">
                                            <div class="col-md-10 d-flex ps-4" >
                                                <img class="mb-1" src="<%=product.getImageURL()%>" alt="alt" width="50px"/>
                                                <div class="align-self-center"><p  class="overflow-hidden text-"><%=product.getProductName()%></p></div>
                                            </div>
                                            <div class="col-md-2 text-end align-self-center"><p  class="overflow-hidden">x<%=orderItem.getQuantity()%></p></div>
                                        </div>
                                        <div class="col-md-1 text-center d-flex flex-column justify-content-center border-end"><p class="overflow-hidden">$<%=orderItem.getQuantity()*product.getPrice()%></p></div>
                                        <div class="col-md-2 text-center d-flex flex-column justify-content-center border-end"><p class="overflow-hidden"><%=order.getPaymentMethod()%></p></div>
                                        <div class="col-md-2 text-center d-flex flex-column justify-content-center border-end"><p class="overflow-hidden"><%=order.getOrderDate()%></p></div>
                                        <div class="col-md-2 text-center d-flex flex-column justify-content-center border-end"><p class="overflow-hidden received-date<%=order.getOrderID()%>"><%if(order.getReceivedDate()!=null){%><%=order.getReceivedDate().toString()%><%}%></p></div>
                                        <div class="col-md-1 text-center d-flex flex-column justify-content-center"><p  class="status<%=order.getOrderID()%>"><%=order.getStatus()%></p></div>
                                    </div> 
                                    <%}%>
                                
                            </div>
                            <div class="row" style="background-color: rgb(245, 246, 250); border: 1px solid rgb(213, 213, 213);">
                                <div class="col-md-12 row fw-normal">
                                    <div class="col-md-4 p-3 text-center">Total:</div>
                                    <div class="col-md-1 p-3 ps-0 text-center">$<%=((int)(totalPrice * 100)) / 100.0%></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%}%>

                </div>
                <!-- Pagination -->
                <div class="row">
                  <%
                    if(listOrder.size() > 0){%>
                     <div class="col-12 mb-30">
                       <div class="page-pagination text-center">
                           <ul>
                               
                               <li><a style="background: rgb(234, 136, 13); color: rgb(255, 255, 255);" class="pagination-link" href="#" data-page-num="1">1</a></li>
                               
                           </ul>
                       </div>
                     </div>
                  <%}%>
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

</body>
</html>

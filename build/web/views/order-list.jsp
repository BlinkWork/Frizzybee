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
                <div class="col-lg-12">
                    <!-- Shop Top Pagination -->
                    <div class="row section-bg pt-20 pb-20 mb-30">
                        <div class="col-lg-7 col-md-6 order-2 order-md-1">
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroup-sizing-sm">Client's name: </span>
                                </div>
                                <input id="search-username" type="text" class="form-control" placeholder="Search...">
                            </div>
                        </div>
                        <div class="col-lg-5 col-md-6 order-1 order-md-2">
                            <div class="top-bar-right">
                                <select id="sort-by" class="form-select" aria-label="Default select example">
                                    <option selected value="DESC">Latest</option>
                                    <option value="ASC">Oldest</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!-- Shop -->
                    <div class="col-md-12">
                        <nav>
                            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                <a class="nav-item nav-link active" id="allOrder" onclick="allOrder(0)" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">All</a>
                                <a class="nav-item nav-link" id="wait-confirm" onclick="waitConfirm(0)" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Waiting for confirmation</a>
                                <a class="nav-item nav-link" id="wait-delivery" onclick="waitDelivery(0)" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">Waiting for delivery</a>
                                <a class="nav-item nav-link" id="delivered" onclick="delivered(0)" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">Delivered</a>
                            </div>
                        </nav>
                    </div>
                    <div class="row" id="order-list">
                        <!-- Product Single -->
                        <%
          OrderDAO odao = new OrderDAO();
          OrderItemDAO oidao = new OrderItemDAO();
          UserDAO userDAO = new UserDAO();
          ProductDAO pdao = new ProductDAO();
          List<Order> listOrder = odao.getOrders();
          for (Order order : listOrder) {%>
                        <div class="col-md-12 mb-30">
                            <div class="product-single-list-view fs-6">
                                <div class="row" style="background-color: rgb(245, 246, 250);border: 1px solid rgb(213, 213, 213);">
                                    <div class="col-md-11 row fw-medium">
                                        <div class="col-md-4"><p class="ps-4">Product</p></div>
                                        <div class="col-md-1"><p>Price</p></div>
                                        <div class="col-md-2"><p>Payment method</p></div>
                                        <div class="col-md-2"><p>Order date</p></div>
                                        <div class="col-md-2"><p>Delivery date</p></div>
                                        <div class="col-md-1"><p>Status</p></div>
                                    </div>
                                    <div class="col-md-1 text-center""><p>Action</p></div>
                                </div>
                                <div class="row fw-light" style="background-color: rgb(245, 246, 250); border: 1px solid rgb(213, 213, 213); border-top: 0px;">
                                    <div class="col-md-6 d-flex"><img class="ms-2" src="./resources/img/user-icon.png" alt="user" width="20px" style="object-fit: contain;"><p class="p-2"><%=order.getUser().getName()%></p></div>
                                    <div class="col-md-6 text-end"><p class="p-2 pe-4">ID Order: <%=order.getOrderID()%></p></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-11">
                                         <%
                                         List<OrderItem> listOrderItem = oidao.getOrderItemsByOrderID(order.getOrderID()+"");
                                         for (OrderItem orderItem : listOrderItem) {
                                         Product product = orderItem.getProduct();
                                         %>
                                         
                                       <div  class="col-md-11 row fw-normal align-items-center align-items-stretch" style="background-color: rgb(255, 255, 255); " >
                                        <div class="col-md-4 row border-end">
                                            <div class="col-md-10 d-flex ps-4" >
                                                <img src="<%=product.getImageURL()%>" alt="alt" width="50px"/>
                                                <div class="align-self-center"><p  class="overflow-hidden text-"><%=product.getProductName()%></p></div>
                                            </div>
                                            <div class="col-md-2 text-end align-self-center"><p  class="overflow-hidden">x<%=orderItem.getQuantity()%></p></div>
                                        </div>
                                        <div class="col-md-1 text-center d-flex flex-column justify-content-center border-end"><p class="overflow-hidden">$<%=orderItem.getQuantity()*product.getPrice()%></p></div>
                                        <div class="col-md-2 text-center d-flex flex-column justify-content-center border-end"><p class="overflow-hidden"><%=order.getPaymentMethod()%></p></div>
                                        <div class="col-md-2 text-center d-flex flex-column justify-content-center border-end"><p class="overflow-hidden"><%=order.getOrderDate()%></p></div>
                                        <div class="col-md-2 text-center d-flex flex-column justify-content-center border-end"><p class="overflow-hidden received-date<%=order.getOrderID()%>"><%if(order.getReceivedDate()!=null){%><%=order.getReceivedDate().toString()%><%}%></p></div>
                                        <div class="col-md-1 text-center d-flex flex-column justify-content-center"><p  class="overflow-hidden" class="status<%=order.getOrderID()%>"><%=order.getStatus()%></p></div>
                                    </div> 
                                    <%}%>
                                </div> 
                                <div class="col-md-1 text-center d-flex flex-column justify-content-center border-start">
                                    <%if(order.getStatus().equals("unconfimred")){%>
                                    <p class="order-action" id="<%=order.getOrderID()%>" onclick="comfirm(<%=order.getOrderID()%>)">Comfirm?</p>
                                    <%}else if(order.getStatus().equals("confirm")){%>
                                    <p class="order-action" id="<%=order.getOrderID()%>" onclick="delivery(<%=order.getOrderID()%>)">Delivery?</p>
                                    <%}else{%>
                                    <p style="color: silver">Delivered</p>
                                    <%}%>
                                </div>
                            </div>
                            <div class="row" style="background-color: rgb(245, 246, 250); border: 1px solid rgb(213, 213, 213);">
                                <div class="col-md-11 row fw-normal">
                                    <div class="col-md-4 p-3 text-center">Total:</div>
                                    <div class="col-md-1 p-3 ps-0 text-center">Price</div>
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
                               <%int pageNum = (listOrder.size()%5==0)?listOrder.size()/5:listOrder.size()/5+1;
                               int pageID = (int) request.getAttribute("pageid");
                               %>
                               <li><a style="background: rgb(234, 136, 13); color: rgb(255, 255, 255);" class="pagination-link" href="#" data-page-num="1">1</a></li>
                               <%

                               for(int i=2;i<=pageNum;i++){
                               %>
                               <li><a  class="pagination-link" href="#" data-page-num="<%=i%>"><%=i%></a></li>
                               <%}%>
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
<script src="./resources/js/order-list-script.js"></script>
</body>
</html>

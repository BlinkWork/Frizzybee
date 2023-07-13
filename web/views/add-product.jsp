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

    </head>
    <body>
        <%@include file="../views/servletComponents/header_component.jsp" %>
        <!-- Start BreadCrumb Area -->
        <div class="breadcrumb-area pt-100 pb-100" style="background-image: url('./resources/img/breadcrumb.jpg');">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-content">
                            <h2>Product Edit</h2>
                            <ul>
                                <li><a href="./">Home</a></li>
                                <li class="active">Product Edit</li>
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
                boolean validate = (request.getAttribute("validate") + "").equals("null") ? true : false;
                    String productName = (request.getAttribute("productName") + "").equals("null") ? "" : (request.getAttribute("productName") + "");
                    String description = (request.getAttribute("description") + "").equals("null") ? "" : (request.getAttribute("description") + "");
                    String categoryName = (request.getAttribute("category") + "").equals("null") ? "" : (request.getAttribute("category") + "");
                    String brand = (request.getAttribute("brand") + "").equals("null") ? "" : (request.getAttribute("brand") + "");
                    String imageURL = (request.getAttribute("imageURL") + "").equals("null") ? "./resources/img/product-default.jpg" : (request.getAttribute("imageURL") + "");
                
                    String productNameErr=  (request.getAttribute("productNameErr")+ "").equals("null") ? "" : request.getAttribute("productNameErr")+ "";
                    String  descriptionErr=  (request.getAttribute("descriptionErr")+ "").equals("null") ? "" : request.getAttribute("descriptionErr")+ "";
                    String categoryErr =  (request.getAttribute("categoryErr")+ "").equals("null") ? "" : request.getAttribute("categoryErr")+ "";
                    String  brandErr=  (request.getAttribute("brandErr" )+ "").equals("null") ? "" : request.getAttribute("brandErr" )+ "";
                    String priceErr =  (request.getAttribute("priceErr" )+ "").equals("null") ? "" : request.getAttribute("priceErr" )+ "";
                    String quantityErr =  (request.getAttribute("quantityErr" )+ "").equals("null") ? "" : request.getAttribute("quantityErr" )+ "";
                    String discountErr =  (request.getAttribute("discountErr")+ "").equals("null") ? "" : request.getAttribute("discountErr")+ "";
                %>
                <!-- Product Details -->
                <form action="product-management" method="post" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-md-5 col-lg-6">
                            <div class="modal_tab_img">
                                <a href="#"><img src="<%=imageURL%>" alt="img"></a>
                            </div>
                            <div class="form-group mt-3">
                                <label for="productImage">Product Image:</label>
                                <input type="file" class="form-control-file" id="productImage" name="productImage">
                            </div>
                            <h5 class="d-flex justify-content-center mt-3">- Or -</h5>
                            <div class="form-group mt-3">
                                <label for="productURL">Product Image:</label>
                                <input type="text" class="form-control" id="productURL" name="productURL">
                            </div>
                            
                        </div>
                        <div class="col-md-7 col-lg-6">
                            <div class="form-group mt-1">
                                <label for="productName">Product Name:</label>
                                <input type="text" class="form-control" id="productName" name="productName" value="<%=productName%>" placeholder="Product Name">
                                <p class="text-danger"><%=productNameErr%></p>
                            </div>
                            <div class="form-group mt-1">
                                <label for="description">Description:</label>
                                <textarea class="form-control" id="description" name="description" rows="3" placeholder="Description"><%=description%></textarea>
                                <p class="text-danger"><%=descriptionErr%></p>
                            </div>
                            <div class="form-group mt-1">
                                <label for="category">Category:</label>
                                <select class="form-control" id="category" name="category">
                                    <%
                                    CategoryDAO ctdao = new CategoryDAO();
                                    List<Category> t = ctdao.getCategorys();
                                    for (Category category : t) {
                                    if(category.getCategoryName().equals(categoryName)){
                                    %>
                                    <option value="<%=category.getCategoryName()%>" selected><%=category.getCategoryName()%></option>
                                    <%}else{%>
                                        <option value="<%=category.getCategoryName()%>"><%=category.getCategoryName()%></option>
                                        <%}%>
                                    <%}%>
                                </select>
                                <p class="text-danger"><%=categoryErr%></p>
                            </div>
                            <div class="form-group mt-1">
                                <label for="brand">Brand:</label>
                                <input type="text" class="form-control" id="brand" name="brand" placeholder="Brand" value="<%=brand%>">
                                <p class="text-danger"><%=brandErr%></p>
                            </div>
                            <div class="form-group mt-1">
                                <label for="price">Price:</label>
                                <input type="number" class="form-control" id="price" name="price" placeholder="Price">
                                <p class="text-danger"><%=priceErr%></p>
                            </div>
                            <div class="form-group mt-1">
                                <label for="quantity">Quantity:</label>
                                <input type="number" class="form-control" id="quantity" name="quantity" placeholder="Quantity">
                                <p class="text-danger"><%=quantityErr%></p>
                            </div>
                            <div class="form-group mt-1">
                                <label for="discount">Discount:</label>
                                <input type="number" class="form-control" id="discount" name="discount" placeholder="Discount">
                                <p class="text-danger"><%=discountErr%></p>
                            </div>
                            <input type="hidden" name="event" value="create-product">
                            <button type="submit" class="btn btn-primary mt-1">Submit</button>
                        </div>
                    </div> 
                </form>
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

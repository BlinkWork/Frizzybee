<%@page import="database.*, java.util.List, model.*, java.text.DecimalFormat, java.io.IOException" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  class="no-js" lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>BulkShop - Electronics Shop HTML Template | Product Details</title>
        
        <link rel="icon" href="./resources/img/icon.png" type="image/gif" sizes="16x16">
        <link rel="icon" href="./resources/img/icon.png" type="image/gif" sizes="18x18">
        <link rel="icon" href="./resources/img/icon.png" type="image/gif" sizes="20x20">
 <%
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()  + request.getContextPath();
        %>
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
                        <h2><a href="index.jsp"><img src="./resources/img/logo.png"></a></h2>
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
                            <h2>Product Edit</h2>
                            <ul>
                                <li><a href="index.jsp">Home</a></li>
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
                                    <div class="product-review-list">
                                        <h3>2 Review For Blue Dress For Woman</h3>
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
                                        </ul>
                                    </div>
                                    <!-- Form -->
                                    <div class="product-review-form">
                                        <h3>Add a review</h3>
                                        <div class="ratting">
                                            <span><i class="fas fa-star"></i></span>
                                            <span><i class="fas fa-star"></i></span>
                                            <span><i class="fas fa-star"></i></span>
                                            <span><i class="fas fa-star"></i></span>
                                            <span><i class="fas fa-star"></i></span>
                                        </div>
                                        <form action="#">
                                            <textarea name="review-message" class="form-control" placeholder="Your Review"></textarea>
                                            <input type="text" name="name" class="form-control" placeholder="Your Name">
                                            <input type="email" name="email" class="form-control" placeholder="Your Email">
                                            <button type="submit">Submit Review</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </section>
        <!-- End Shop Area -->
        <!-- Start Our Latest Product -->
        <section class="latest-product pt-70 pb-70 section-bg">
            <div class="container">
                <!-- Section Title -->
                <div class="row">
                    <div class="col-12">
                        <div class="section-headding-1 mb-50">
                            <h2><span>Releted Products</span></h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="latest-product-full owl-carousel">
                            <!-- Single -->
                            <div class="product-single">
                                <div class="product-thumbnail">
                                    <a href="product-details.jsp"><img src="./resources/img/product/8.jpg" alt="product"></a>
                                    <div class="product-thumbnail-overly">
                                        <ul>
                                            <li><a href="cart.jsp"><i class="fas fa-shopping-cart"></i></a></li>
                                            <li><a href="wishlist.jsp"><i class="far fa-heart"></i></a></li>
                                            <li><a href="#"><i class="far fa-eye"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="product-content">
                                    <h4><a href="product-details.jsp">Funda Para Ebook 7" 128GB full HD</a></h4>
                                    <div class="pricing">
                                        <span>$200 <del>$210</del></span>
                                    </div>
                                </div>
                            </div>
                            <div class="product-single">
                                <div class="product-thumbnail">
                                    <a href="product-details.jsp"><img src="./resources/img/product/1.jpg" alt="product"></a>
                                    <div class="product-thumbnail-overly">
                                        <ul>
                                            <li><a href="cart.jsp"><i class="fas fa-shopping-cart"></i></a></li>
                                            <li><a href="wishlist.jsp"><i class="far fa-heart"></i></a></li>
                                            <li><a href="#"><i class="far fa-eye"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="product-content">
                                    <h4><a href="product-details.jsp">Funda Para Ebook 7" 128GB full HD</a></h4>
                                    <div class="pricing">
                                        <span>$200 <del>$210</del></span>
                                    </div>
                                </div>
                            </div>
                            <div class="product-single">
                                <div class="product-thumbnail">
                                    <a href="product-details.jsp"><img src="./resources/img/product/3.jpg" alt="product"></a>
                                    <div class="product-thumbnail-overly">
                                        <ul>
                                            <li><a href="cart.jsp"><i class="fas fa-shopping-cart"></i></a></li>
                                            <li><a href="wishlist.jsp"><i class="far fa-heart"></i></a></li>
                                            <li><a href="#"><i class="far fa-eye"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="product-content">
                                    <h4><a href="#">Funda Para Ebook 7" 128GB full HD</a></h4>
                                    <div class="pricing">
                                        <span>$200 <del>$210</del></span>
                                    </div>
                                </div>
                            </div>
                            <div class="product-single">
                                <div class="product-thumbnail">
                                    <a href="product-details.jsp"><img src="./resources/img/product/4.jpg" alt="product"></a>
                                    <div class="product-thumbnail-overly">
                                        <ul>
                                            <li><a href="cart.jsp"><i class="fas fa-shopping-cart"></i></a></li>
                                            <li><a href="wishlist.jsp"><i class="far fa-heart"></i></a></li>
                                            <li><a href="#"><i class="far fa-eye"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="product-content">
                                    <h4><a href="#">Funda Para Ebook 7" 128GB full HD</a></h4>
                                    <div class="pricing">
                                        <span>$200 <del>$210</del></span>
                                    </div>
                                </div>
                            </div>
                            <div class="product-single">
                                <div class="product-thumbnail">
                                    <a href="product-details.jsp"><img src="./resources/img/product/5.jpg" alt="product"></a>
                                    <div class="product-thumbnail-overly">
                                        <ul>
                                            <li><a href="cart.jsp"><i class="fas fa-shopping-cart"></i></a></li>
                                            <li><a href="wishlist.jsp"><i class="far fa-heart"></i></a></li>
                                            <li><a href="#"><i class="far fa-eye"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="product-content">
                                    <h4><a href="#">Funda Para Ebook 7" 128GB full HD</a></h4>
                                    <div class="pricing">
                                        <span>$200 <del>$210</del></span>
                                    </div>
                                </div>
                            </div>
                            <div class="product-single">
                                <div class="product-thumbnail">
                                    <a href="product-details.jsp"><img src="./resources/img/product/6.jpg" alt="product"></a>
                                    <div class="product-thumbnail-overly">
                                        <ul>
                                            <li><a href="cart.jsp"><i class="fas fa-shopping-cart"></i></a></li>
                                            <li><a href="wishlist.jsp"><i class="far fa-heart"></i></a></li>
                                            <li><a href="#"><i class="far fa-eye"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="product-content">
                                    <h4><a href="#">Funda Para Ebook 7" 128GB full HD</a></h4>
                                    <div class="pricing">
                                        <span>$200 <del>$210</del></span>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Our Latest Product -->

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
    </body>
</html>
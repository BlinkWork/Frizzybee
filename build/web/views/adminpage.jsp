<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="database.*"%>
<%@page import="model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <title>Admin Panel</title>
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
        <jsp:include page="adminnavbar.jsp" />
        <div class="live-statistic"> 
            <p> Number of users : <%=request.getAttribute("count")%> 
            </p>
        </div>
            
         <%
             
             int countProduct = 0;
            ProductDAO p = new ProductDAO();
            for (Product product : p.getProducts()) {
              countProduct++;
            }
            
             int countCategory = 0;
            CategoryDAO c = new CategoryDAO();
            for (Category category : c.getCategorys()){
              countCategory++;
            }
            
             int countBrand = 0;
            BrandDAO b = new BrandDAO();
            for (Brand brand : b.getBrands()) {
              countBrand++;
            }
            
         %>  
         <div class="live-statistic"> 
            <p> Number of products: <%=countProduct%> 
            </p>
        </div>
         <div class="live-statistic"> 
            <p> Number of categories : <%=countCategory%> 
            </p>
        </div>
         <div class="live-statistic"> 
            <p> Number of brands : <%=countBrand%> 
            </p>
        </div>

            
    </body>
</html>

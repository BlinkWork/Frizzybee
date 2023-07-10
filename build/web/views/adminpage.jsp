<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="database.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <title>Admin Panel</title>
        <link rel="icon" href="./resources/img/logo.png" type="image/gif" sizes="16x16">
        <link rel="icon" href="./resources/img/logo.png" type="image/gif" sizes="18x18">
        <link rel="icon" href="./resources/img/logo.png" type="image/gif" sizes="20x20">
    </head>
    <body>
        <h1>Admin Panel</h1>

        <jsp:include page="adminnavbar.jsp" />
        <div class="live-statistic"> 
            <p> Number of users : <%=request.getAttribute("count")%> 
            </p>
        </div>
    </body>
</html>

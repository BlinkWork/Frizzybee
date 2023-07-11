/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.CategoryDAO;
import database.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author USER
 */
@WebServlet(name = "shop", urlPatterns = {"/shop"})
public class ShowListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = "select * from [dbo].[Product]";
        if (getScope(request, response).trim().isEmpty() == false) {
            query = query + " where " + getScope(request, response);
        }
        ProductDAO dao = new ProductDAO();
        paging(dao, request, response, query);
        getLatestProducts(dao, request, response);
        getPageNumber(getScope(request, response), dao, request, response);
        RequestDispatcher rs = request.getRequestDispatcher("./views/shop.jsp");
        rs.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();

        String action = request.getParameter("action");

        if (action.equals("show")) {
            String query = "select * from [dbo].[Product]";
            if (getScope(request, response).trim().isEmpty() == false) {
                query = query + " where " + getScope(request, response);
            }
            paging(dao, request, response, query);
            renderDataList(request, response);
        }
        if (action.equals("pageNumber")) {
            getPageNumber(getScope(request, response), dao, request, response);
            renderPageNumber(request, response);
        }
    }

    public void paging(ProductDAO dao, HttpServletRequest request, HttpServletResponse response, String query) throws ServletException, IOException {
        String spageid = request.getParameter("page");
        int pageid;
        if (spageid == null) {
            pageid = 1;
        } else {
            pageid = Integer.parseInt(spageid);
        }

        int total = 9;
        if (pageid == 1) {
            pageid = pageid - 1;
        } else {
            pageid = pageid - 1;
            pageid = pageid * total + 1;
        }

        String selection = request.getParameter("sortOption");
        if (selection == null) {
            selection = "1";
        }

        String kind = "";
        String order = "";

        switch (selection) {
            case "1":
                kind = "product_name";
                order = "ASC";
                break;
            case "2":
                kind = "product_name";
                order = "DESC";
                break;
            case "3":
                kind = "price";
                order = "ASC";
                break;
            case "4":
                kind = "price";
                order = "DESC";
                break;
            default:
                throw new AssertionError();
        }
        List<Product> listProduct = dao.getProductsByPage(pageid, total, query, kind, order);
        request.setAttribute("listProduct", listProduct);
    }

    public void getLatestProducts(ProductDAO dao, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> latestProducts = dao.getNLatestProducts(3);
        request.setAttribute("latestProducts", latestProducts);
    }

    public void getPageNumber(String scope, ProductDAO dao, HttpServletRequest request, HttpServletResponse response) {
        int numberRecord = dao.getRecordNum(getScope(request, response)) - 1;

        request.setAttribute("recordNumbers", numberRecord);
        int number = 0;
        if (numberRecord > 0) {
            number = numberRecord % 9 == 0 ? numberRecord / 9 : numberRecord / 9 + 1;
        }
        request.setAttribute("pageNumbers", number);
    }

    private String getScope(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("search");
        String categoryText = request.getParameter("tag");
        if (categoryText != null) {
            if (categoryText.equals("All Categories")) {
                categoryText = null;
            }
        }

        String scope = "";
        if ((searchText != null && searchText.trim().isEmpty() == false) || (categoryText != null && categoryText.trim().isEmpty() == false)) {
            String scope1 = "";
            String scope2 = "";
            if (searchText != null && searchText.trim().isEmpty() == false) {
                scope1 = "product_name like '%" + searchText + "%'";
            }
            if (categoryText != null && categoryText.trim().isEmpty() == false) {
                CategoryDAO ctdao = new CategoryDAO();
                Category ct = ctdao.getCategoryByName(categoryText);
                scope2 = "category_id = " + ct.getCategoryID();
            }
            if ((searchText != null && searchText.trim().isEmpty() == false) && (categoryText != null && categoryText.trim().isEmpty() == false)) {
                scope = scope1 + " and " + scope2;

            } else {
                if (searchText != null && searchText.trim().isEmpty() == false) {
                    scope = scope1;
                }
                if ((categoryText != null && categoryText.trim().isEmpty() == false)) {
                    scope = scope2;
                }
            }
        }
        return scope;
    }

    private String printHTML(Product product) {
        DecimalFormat df = new DecimalFormat("#.##");

        double price = product.getPrice() * (100 - product.getDiscount()) / 100;
        String formattedPrice = df.format(price);

        double priceBefore = product.getPrice();
        String formattedPriceBefore = df.format(priceBefore);
        String data_item
                = "<div class='col-lg-4 col-md-4 col-sm-6 mb-30'>"
                + "<div class='product-single'>"
                + "<div class='product-thumbnail'>"
                + "<a href='./productDetails?id=" + product.getProductID() + "'><img src='" + product.getImageURL() + "' alt='product' style='width:200px;'></a>"
                + "<div class='product-thumbnail-overly'>"
                + "<ul>"
                + "<li><a href='' class='addCart' id='cart_" + product.getProductID() + "'><i class='fas fa-shopping-cart'></i></a></li>"
                + "<li><a href='wishlist.jsp'><i class='far fa-heart'></i></a></li>"
                + "<li><a href='./productDetails?id=" + product.getProductID() + "' ><i class='far fa-eye'></i></a></li>"
                + "</ul>"
                + "</div>"
                + "</div>"
                + "<div class='product-content'>"
                + "<h4><a href='./productDetails?id=" + product.getProductID() + "'>" + product.getProductName() + "</a></h4>"
                + "<div class='pricing'>"
                + "<span class='priceDiscount' style='display: flex; flex-direction: column;'>" + formattedPrice + "$<del>" + formattedPriceBefore + "$</del></span>"
                + "</div>"
                + "</div>"
                + "</div>"
                + "</div>";
        return data_item;
    }

    private void renderDataList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Product> listProduct = (List<Product>) request.getAttribute("listProduct");
        String data = "";
        if (listProduct != null) {
            for (int i = 0; i < listProduct.size(); i += 3) {
                data += "<div class='row'>";
                data += printHTML(listProduct.get(i));
                if (i + 1 < listProduct.size()) {
                    data += printHTML(listProduct.get(i + 1));
                }
                if (i + 2 < listProduct.size()) {
                    data += printHTML(listProduct.get(i + 2));
                }
                data += "</div>";

            }
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(data);
    }

    private void renderPageNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int number = (Integer) request.getAttribute("pageNumbers");
        int page = Integer.parseInt(request.getParameter("page"));
        String data = "";
        for (int i = 1; i <= number; i++) {
            if (i == page) {
                data += "<li class='page-item'><a href=''><span>" + i + "</span></a></li>";
            } else {
                data += "<li class='page-item'><a href=''>" + i + "</a></li>";

            }
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(data);
    }

}

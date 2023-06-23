///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller;
//
//import database.CategoryDAO;
//import database.ProductDAO;
//import jakarta.servlet.RequestDispatcher;
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.util.List;
//import model.Category;
//import model.Product;
//
///**
// *
// * @author USER
// */
//@WebServlet(name = "shop", urlPatterns = {"/shop"})
//public class ShowListServlet extends HttpServlet {
//
//  @Override
//  protected void doGet(HttpServletRequest request, HttpServletResponse response)
//          throws ServletException, IOException {
//
//    String query = getQuery(request, response);
//
//    ProductDAO dao = new ProductDAO();
//    paging(dao, request, response, query);
//    getLatestProducts(dao, request, response);
//    getPageNumber(dao, request, response);
//    RequestDispatcher rs = request.getRequestDispatcher("./views/shop.jsp");
//    rs.forward(request, response);
//  }
//
//  @Override
//  public String getServletInfo() {
//    return "Short description";
//  }
//
//  private void paging(ProductDAO dao, HttpServletRequest request, HttpServletResponse response, String query) throws ServletException, IOException {
//    String spageid = request.getParameter("page");
//    int pageid;
//    if (spageid == null) {
//      pageid = 1;
//    } else {
//      pageid = Integer.parseInt(spageid);
//    }
//
//    int total = 9;
//    if (pageid == 1) {
//      pageid = pageid - 1;
//    } else {
//      pageid = pageid - 1;
//      pageid = pageid * total + 1;
//    }
//
//    String selection = request.getParameter("sortOption");
//    if (selection == null) {
//      selection = "1";
//    }
//
//    String kind = "";
//    String order = "";
//
//    switch (selection) {
//      case "1":
//        kind = "product_name";
//        order = "ASC";
//        break;
//      case "2":
//        kind = "product_name";
//        order = "DESC";
//        break;
//      case "3":
//        kind = "price";
//        order = "ASC";
//        break;
//      case "4":
//        kind = "price";
//        order = "DESC";
//        break;
//      default:
//        throw new AssertionError();
//    }
//    List<Product> listProduct = dao.getProductsByPageDemo(pageid, total, query, kind, order);
//    request.setAttribute("listProduct", listProduct);
//  }
//
//  private void getLatestProducts(ProductDAO dao, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    List<Product> latestProducts = dao.getLatest3Products();
//    request.setAttribute("latestProducts", latestProducts);
//  }
//
//  private void getPageNumber(ProductDAO dao, HttpServletRequest request, HttpServletResponse response) {
//    int number = dao.getRecordNum() / 9 + 1;
//    request.setAttribute("pageNumbers", number);
//  }
//
//  private String getQuery(HttpServletRequest request, HttpServletResponse response) {
//    String searchText = request.getParameter("search");
//    String categoryText = request.getParameter("tag");
//
//    String query = "select * from [dbo].[Product]";
//    if (searchText != null || categoryText != null) {
//      String scope1 = "";
//      String scope2 = "";
//      if (searchText != null) {
//        scope1 = "product_name like '%" + searchText + "%'";
//      }
//      if (categoryText != null) {
//        CategoryDAO ctdao = new CategoryDAO();
//        Category ct = ctdao.getCategoryByName(categoryText);
//        scope2 = "category_id = " + ct.getCategoryID();
//      }
//      if (searchText != null && categoryText != null) {
//        query = query + " where " + scope1 + " and " + scope2;
//      } else {
//        if (searchText != null) {
//          query = query + " where " + scope1;
//        }
//        if (categoryText != null) {
//          query = query + " where " + scope2;
//        }
//      }
//    }
//    return query;
//  }
//}
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
    getPageNumber(getScope(request, response),dao, request, response);
    RequestDispatcher rs = request.getRequestDispatcher("./views/shop.jsp");
    rs.forward(request, response);
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }

  private void paging(ProductDAO dao, HttpServletRequest request, HttpServletResponse response, String query) throws ServletException, IOException {
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

  private void getLatestProducts(ProductDAO dao, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Product> latestProducts = dao.getLatest3Products();
    request.setAttribute("latestProducts", latestProducts);
  }

  private void getPageNumber(String scope, ProductDAO dao, HttpServletRequest request, HttpServletResponse response) {
    int numberRecord = dao.getRecordNum(getScope(request, response));
    request.setAttribute("recordNumbers", numberRecord);
    int number = numberRecord / 9 + 1;
    request.setAttribute("pageNumbers", number);
  }

  private String getScope(HttpServletRequest request, HttpServletResponse response) {
    String searchText = request.getParameter("search");
    String categoryText = request.getParameter("tag");

    String scope = "";
    if (searchText != null || categoryText != null) {
      String scope1 = "";
      String scope2 = "";
      if (searchText != null) {
        scope1 = "product_name like '%" + searchText + "%'";
      }
      if (categoryText != null) {
        CategoryDAO ctdao = new CategoryDAO();
        Category ct = ctdao.getCategoryByName(categoryText);
        scope2 = "category_id = " + ct.getCategoryID();
      }
      if (searchText != null && categoryText != null) {
        scope = scope1 + " and " + scope2;
      } else {
        if (searchText != null) {
          scope = scope1;
        }
        if (categoryText != null) {
          scope = scope2;
        }
      }
    }
    return scope;
  }
}

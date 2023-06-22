/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
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

    ProductDAO dao = new ProductDAO();
    paging(dao, request, response);

  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private void paging(ProductDAO dao, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String spageid = request.getParameter("page");
    int pageid;
    if (spageid == null) {
      pageid = 1;
    } else {
      pageid = Integer.parseInt(spageid);
    }

    int total = 9;
    if (pageid == 1) {
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

    List<Product> listProduct = dao.getProductsByPage(pageid, total, kind, order);
    request.setAttribute("listProduct", listProduct);
    RequestDispatcher rs = request.getRequestDispatcher("./views/shop.jsp");
    rs.forward(request, response);
  }
}

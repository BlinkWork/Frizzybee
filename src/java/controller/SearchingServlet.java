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
@WebServlet(name = "SearchingServlet", urlPatterns = {"/searching"})
public class SearchingServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    ProductDAO dao = new ProductDAO();
    ShowListServlet sl = new ShowListServlet();
    
    String searchText = request.getParameter("search");
    if (searchText.trim().isEmpty() == false) {
      List<Product> listProduct = dao.getProductsByName(searchText);
      request.setAttribute("listProduct", listProduct);
      sl.getLatestProducts(dao, request, response);
      RequestDispatcher rs = request.getRequestDispatcher("./views/shop.jsp");
      rs.forward(request, response);
    } else {
      sl.doGet(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import static controller.CartServlet.encodeCarts;
import static controller.CartServlet.parseCarts;
import database.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import model.Cart;
import model.Product;

/**
 *
 * @author USER
 */
@WebServlet(name = "DetailServlet", urlPatterns = {"/productDetails"})
public class DetailServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String id = request.getParameter("id");
    ProductDAO dao = new ProductDAO();
    Product product = dao.getProductByID(id);
    if (product != null) {
      request.setAttribute("productDetail", product);
      RequestDispatcher rd = request.getRequestDispatcher("./views/product-details.jsp");
      rd.forward(request, response);
    }

  }

  private String getCartCookie(HttpServletRequest request, HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();
    String cartItems = null;
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("cartItems")) {
          cartItems = cookie.getValue();
          break;
        }
      }
    }
    return cartItems;
  }
}

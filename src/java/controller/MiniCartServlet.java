/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import static controller.CartServlet.parseCarts;
import database.CartDAO;
import database.ProductDAO;
import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;
import model.Cart;
import model.Product;

/**
 *
 * @author USER
 */
@WebServlet(name = "MiniCartServlet", urlPatterns = {"/miniCart"})
public class MiniCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        if (userName != null) {
            UserDAO udao = new UserDAO();
            String user_id = String.valueOf(udao.getUserByUsername(userName).getId());
            String miniCartData = returnMiniCart(request, response, user_id);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(miniCartData);
        }

    }

    private String returnMiniCart(HttpServletRequest request, HttpServletResponse response, String user_id) {
        ProductDAO dao = new ProductDAO();
        String cartItems = getCartSession(request, response, user_id);
        String data = "<i class=\"fas fa-shopping-cart\"></i>\n"
                + "<span class=\"counter\">0></span>\n"
                + "<span class=\"counter-cart\"><small>Your Cart</small>$0</span>\n"
                + "<!-- Mini Cart Content -->\n"
                + "<div class=\"minicart-content-wrapper\">\n"
                + "<ul class=\"cart-list-full\">\n"
                + "<!-- Single -->";

        if (cartItems != null) {
            List<Cart> listCartId = parseCarts(cartItems);
            double totalPrice = 0;
            DecimalFormat df = new DecimalFormat("#.##");
            for (Cart cartItem : listCartId) {
                Product p = dao.getProductByID(cartItem.getProduct_id());

                if (p != null) {
                    double price = p.getPrice() * (100 - p.getDiscount()) / 100;
                    String formattedPrice = df.format(price);
                    double amount = price * cartItem.getQuantity();
                    String formattedAmount = df.format(amount);
                    if (cartItem.getQuantity() > p.getQuantity()) {
                        cartItem.setQuantity(p.getQuantity());
                    }
                    totalPrice += amount;
                }
            }
            String sizeCart = String.valueOf(listCartId.size());
            if (listCartId.size() > 10) {
                sizeCart = "10+";
            }
            data = "<i class=\"fas fa-shopping-cart\"></i>\n"
                    + "<span class=\"counter\">" + sizeCart + "</span>\n"
                    + "<span class=\"counter-cart\"><small>Your Cart</small>$" + df.format(totalPrice) + "</span>\n"
                    + "<!-- Mini Cart Content -->\n"
                    + "<div class=\"minicart-content-wrapper\">\n"
                    + "<ul class=\"cart-list-full\">\n"
                    + "<!-- Single -->";

            List<Cart> listItemCarts;
            int size = listCartId.size();
            int startIndex = size - 3;
            if (startIndex < 0) {
                startIndex = 0;
            }
            listItemCarts = listCartId.subList(startIndex, size);

            for (int i = listItemCarts.size() - 1; i >= 0; i--) {
                Cart cartItem = listItemCarts.get(i);
                if (cartItem != null) {
                    Product p = dao.getProductByID(cartItem.getProduct_id());
                    if (p != null) {
                        double price = p.getPrice() * (100 - p.getDiscount()) / 100;
                        String formattedPrice = df.format(price);
                        double amount = price * cartItem.getQuantity();
                        String formattedAmount = df.format(amount);
                        if (cartItem.getQuantity() > p.getQuantity()) {
                            cartItem.setQuantity(p.getQuantity());
                        }
                        String dataItem = "<li class='cart-list-single'>\n"
                                + "<img src='" + p.getImageURL() + "' alt='img'>\n"
                                + "<h5><a href='./productDetails?id=" + p.getProductID() + "'>" + p.getProductName() + "</a></h5>\n"
                                + "<span class='price'>$ " + p.getPrice() + " X " + cartItem.getQuantity() + "</span>\n"
                                + "<div class='close'><a href=\"#\" class=\"removeProduct productId_" + cartItem.getProduct_id() + "\" style=\"color: black\"><i class=\"fas fa-times\"></i></div>\n"
                                + "</li>";
                        data += dataItem + "\n";
                    }
                }
            }

            if (listCartId.size() > 3) {
                data += "<li class='cart-list-single'>\n"
                        + "...............view more</li>";
            }
            if (listCartId.size() == 0) {
                data += "<li class='cart-list-single'>\n"
                        + "..........nothing here</li>";
                data += "</ul>\n"
                        + "<h2 class=\"subtotal\">Subtotal : <span>" + df.format(totalPrice) + "</span></h2>\n"
                        + "<div class=\"minicart-btn\">\n"
                        + "<a class=\"button-1\" href=\"./cart\">View Cart</a>\n"
                        + "</div>\n"
                        + "</div>";
            } else {
                data += "</ul>\n"
                        + "<h2 class=\"subtotal\">Subtotal : <span>" + df.format(totalPrice) + "</span></h2>\n"
                        + "<div class=\"minicart-btn\">\n"
                        + "<a class=\"button-1\" href=\"./cart\">View Cart</a>\n"
                        + "<a class=\"button-2\" href=\"./checkout\">Checkout</a>\n"
                        + "</div>\n"
                        + "</div>";
            }

        }
        return data;
    }
    
    public String getCartSession(HttpServletRequest request, HttpServletResponse response, String user_id) {
        HttpSession session = request.getSession();
        String cartItems = (String) session.getAttribute("cartItems");
        if (cartItems == null || cartItems.trim().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            CartDAO cdao = new CartDAO();
//            List<Cart> listTemp = cdao.getCarts();
            List<Cart> listTemp = cdao.getCarts(Integer.parseInt(user_id));

            if (listTemp != null) {
                for (Cart cart : listTemp) {
//                    System.out.println(cart.getCart_id() + "-" + cart.getUser_id() + "-" + cart.getProduct_id() + "-" + cart.getQuantity());

                    sb.append(cart.getUser_id()).append("_").append(cart.getCart_id()).append("_");
                    sb.append(cart.getProduct_id()).append("_").append(cart.getQuantity()).append("@");
                }
            } else {
            }
            cartItems = sb.toString();
            session.setAttribute("cartItems", cartItems);
        }
        return cartItems;
    }

}

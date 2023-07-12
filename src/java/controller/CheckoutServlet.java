/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import static controller.CartServlet.parseCarts;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.*;
import controller.*;
import static controller.CartServlet.parseCarts;
import database.CartDAO;
import database.OrderDAO;
import database.OrderItemDAO;
import database.ProductDAO;
import database.UserDAO;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.DecimalFormat;

/**
 *
 * @author USER
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        if (userName != null) {
            UserDAO udao = new UserDAO();
            String user_id = String.valueOf(udao.getUserByUsername(userName).getId());
            CartServlet sessionHandle = new CartServlet();
            String cartItems = sessionHandle.getCartSession(request, response, user_id);
            ProductDAO dao = new ProductDAO();
            String data = "";
            if (cartItems != null) {
                List<Cart> listCartId = parseCarts(cartItems);
                for (Cart cart : listCartId) {
                    Product p = dao.getProductByID(cart.getProduct_id());
                    DecimalFormat df = new DecimalFormat("#.##");
                    double price = p.getPrice() * (100 - p.getDiscount()) / 100;
                    String formattedPrice = df.format(price);
                    double amount = price * cart.getQuantity();
                    String formattedAmount = "$" + df.format(amount);

                    data += dao.getProductByID(cart.getProduct_id()).getProductName() + "_" + cart.getQuantity() + "_" + formattedAmount + "@";

                }
            }

            request.setAttribute("dataCart", data);
            RequestDispatcher rd = request.getRequestDispatcher("./views/checkout.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("./404");
            
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        if (userName != null) {
            UserDAO udao = new UserDAO();
            String user_id = String.valueOf(udao.getUserByUsername(userName).getId());
            addToOrder(request, response, user_id);
            removeCartItems(request, response, user_id);
            session.setAttribute("cartItems", null);

        }
    }

    private void removeCartItems(HttpServletRequest request, HttpServletResponse response, String user_id) {
        CartServlet sessionHandle = new CartServlet();
        String cartItems = sessionHandle.getCartSession(request, response, user_id);

        CartDAO cdao = new CartDAO();
        if (cartItems != null) {
            List<Cart> cartItemList = parseCarts(cartItems);
            UserDAO udao = new UserDAO();
            User user = udao.getUserByID(cartItemList.get(0).getUser_id());
            cdao.deleteAllCartsByUserId(Integer.parseInt(cartItemList.get(0).getUser_id()));

            for (Cart cartItem : cartItemList) {
                decreaseProductQuantity(request, response, cartItem);
                break;
            }
        }

    }

    private void addToOrderItem(HttpServletRequest request, HttpServletResponse response, String user_id, int order_id) {
        CartServlet sessionHandle = new CartServlet();
        String cartItems = sessionHandle.getCartSession(request, response, user_id);

        CartDAO cdao = new CartDAO();
        if (cartItems != null) {
            List<Cart> cartItemList = parseCarts(cartItems);

            for (Cart cartItem : cartItemList) {
                ProductDAO pdao = new ProductDAO();
                Product p = pdao.getProductByID(cartItem.getProduct_id());
                OrderItem oderItem = new OrderItem(1, order_id, p, cartItem.getQuantity(), p.getPrice() * cartItem.getQuantity());
                OrderItemDAO oidao = new OrderItemDAO();
                oidao.insert(oderItem);
            }
        }

    }

    private void addToOrder(HttpServletRequest request, HttpServletResponse response, String user_id) {
        CartServlet sessionHandle = new CartServlet();
        String cartItems = sessionHandle.getCartSession(request, response, user_id);

        CartDAO cdao = new CartDAO();
        if (cartItems != null) {
            List<Cart> cartItemList = parseCarts(cartItems);
            String address = request.getParameter("address");
            if (address == null || address.equals("null") || address.trim().isEmpty()) {
                address = "";
            }
            String totalPrice = request.getParameter("totalPrice");
            String paymentMethod = request.getParameter("paymentMethod");

            UserDAO udao = new UserDAO();
            User user = udao.getUserByID(cartItemList.get(0).getUser_id());
            Date currentDate = new Date(System.currentTimeMillis());
            Order order = null;
            OrderDAO odao = new OrderDAO();

            try {
                order = new Order(1, user, currentDate, address, paymentMethod, "unconfimred", Double.parseDouble(totalPrice));
                int order_id = odao.insert(order);
                addToOrderItem(request, response, user_id, order_id);
            } catch (Exception e) {
            }
        }

    }

    private void decreaseProductQuantity(HttpServletRequest request, HttpServletResponse response, Cart cartItem) {
        ProductDAO dao = new ProductDAO();
        Product product = dao.getProductByID(cartItem.getProduct_id());
        if(product != null){           
            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            dao.update(product);
        }
    }

}

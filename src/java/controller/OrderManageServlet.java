/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.OrderDAO;
import database.OrderItemDAO;
import database.ProductDAO;
import database.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Order;
import model.OrderItem;
import model.Product;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "OrderManageServlet", urlPatterns = {"/order-management"})
public class OrderManageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void renderOrderList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pageid", 1);
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        UserDAO userDAO = new UserDAO();
        User curUser = userDAO.getUserByUsername(username);
        RequestDispatcher rs = request.getRequestDispatcher("./views/order-list.jsp");
        rs.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String event = request.getParameter("event");
        if (event.equals("order-management")) {
            renderOrderList(request, response);
        } else if (event.equals("all-order")) {
            renderOrderListByStatus(request, response);
        }else if (event.equals("my-order")) {
            request.getRequestDispatcher("./views/order-of-user.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String event = request.getParameter("event");
        if (event.equals("confirm")) {
            confirm(request, response);
        } else if (event.equals("delivery")) {
            delivery(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void confirm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String id = request.getParameter("order_id");
        OrderDAO odao = new OrderDAO();
        Order order = odao.getOrderByID(id);
        order.setStatus("confirm");
        odao.updateDelivery(order);
    }

    protected void delivery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(calendar.getTime().getTime());
        String id = request.getParameter("order_id");
        OrderDAO odao = new OrderDAO();
        Order order = odao.getOrderByID(id);
        order.setStatus("deliveried");
        order.setReceivedDate(currentDate);
        odao.updateDelivery(order);
    }

    protected void renderOrderListByStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        OrderDAO orderDAO = new OrderDAO();
        OrderItemDAO oidao = new OrderItemDAO();
        UserDAO userDAO = new UserDAO();
        ProductDAO pdao = new ProductDAO();
        String pageNum = request.getParameter("page") + "";
        String status = request.getParameter("status") + "";
        String username = (request.getParameter("username")+"").equals("null")?"": (request.getParameter("username")+"");
        String sort = (request.getParameter("sort")+"").equals("null")? "DESC": (request.getParameter("sort")+"");
        List<Order> listOrder = new ArrayList<>();
        int pageNumber = pageNum.equals("null") ? 1 : Integer.parseInt(pageNum.trim());
        request.setAttribute("pageid",pageNum+1);
        pageNumber = pageNumber>1 ? (pageNumber-1)*5 :pageNumber;
        if(status.equals("all") || status.isEmpty()){
            listOrder = orderDAO.getOrdersByPage(pageNumber, pageNumber+4, username, sort);
        }else{
            listOrder = orderDAO.getOrdersByStatusByPage(status, pageNumber, pageNumber+4, username, sort);
        }
        for (Order order : listOrder) {
            out.print("<div class=\"col-md-12 mb-30\">\n"
                    + "                            <div class=\"product-single-list-view fs-6\">\n"
                    + "                                <div class=\"row\" style=\"background-color: rgb(245, 246, 250);border: 1px solid rgb(213, 213, 213);\">\n"
                    + "                                    <div class=\"col-md-11 row fw-medium\">\n"
                    + "                                        <div class=\"col-md-4\"><p class=\"ps-4\">Product</p></div>\n"
                    + "                                        <div class=\"col-md-1\"><p>Price</p></div>\n"
                    + "                                        <div class=\"col-md-2\"><p>Payment method</p></div>\n"
                    + "                                        <div class=\"col-md-2\"><p>Order date</p></div>\n"
                    + "                                        <div class=\"col-md-2\"><p>Delivery date</p></div>\n"
                    + "                                        <div class=\"col-md-1\"><p>Status</p></div>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"col-md-1 text-center\"\"><p>Action</p></div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"row fw-light\" style=\"background-color: rgb(245, 246, 250); border: 1px solid rgb(213, 213, 213); border-top: 0px;\">\n"
                    + "                                    <div class=\"col-md-6 d-flex\"><img class=\"ms-2\" src=\"./resources/img/user-icon.png\" alt=\"user\" width=\"20px\" style=\"object-fit: contain;\"><p class=\"p-2\">" + order.getUser().getName() + "</p></div>\n"
                    + "                                    <div class=\"col-md-6 text-end\"><p class=\"p-2 pe-4\">ID Order: " + order.getOrderID() + "</p></div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"row\">\n"
                            + "<div class=\"col-md-11\">");
            List<OrderItem> listOrderItem = oidao.getOrderItemsByOrderID(order.getOrderID() + "");
            double totalPrice = 0;
            for (OrderItem orderItem : listOrderItem) {
                Product product = orderItem.getProduct();
                double totalPriceOrderItem = orderItem.getQuantity() * (product.getPrice()-product.getDiscount()*product.getPrice()/100) ;
                totalPrice+=totalPriceOrderItem;
                out.print(
                        "                                    <div\n"
                        + "                                        class=\"col-md-12 row fw-normal align-items-center align-items-stretch\" style=\"background-color: rgb(255, 255, 255); \" >\n"
                        + "                                        <div class=\"col-md-4 row border-end\">\n"
                        + "                                            <div class=\"col-md-10 d-flex ps-4\" >\n"
                        + "                                                <img class=\"mb-1\" src=\"" + product.getImageURL() + "\" alt=\"alt\" width=\"50px\"/>\n"
                        + "                                                <div class=\"align-self-center\"><p class=\"overflow-hidden\">" + product.getProductName() + "</p></div>\n"
                        + "                                            </div>\n"
                        + "                                            <div class=\"col-md-2 text-end align-self-center\"><p class=\"overflow-hidden\">x" + orderItem.getQuantity() + "</p></div>\n"
                        + "                                        </div>\n"
                        + "                                        <div class=\"col-md-1 text-center d-flex flex-column justify-content-center border-end\"><p class=\"overflow-hidden\">$" + totalPriceOrderItem + "</p></div>\n"
                        + "                                        <div class=\"col-md-2 text-center d-flex flex-column justify-content-center border-end\"><p class=\"overflow-hidden\">" + order.getPaymentMethod() + "</p></div>\n"
                        + "                                        <div class=\"col-md-2 text-center d-flex flex-column justify-content-center border-end\"><p class=\"overflow-hidden\">" + order.getOrderDate() + "</p></div>\n"
                        + "                                        <div class=\"col-md-2 text-center d-flex flex-column justify-content-center border-end\"><p class=\"overflow-hidden received-date" + order.getOrderID() + "\">");
                                if(order.getReceivedDate()!=null)out.print( order.getReceivedDate().toString() );out.print("</p></div>\n"
                        + "                                        <div class=\"col-md-1 text-center d-flex flex-column justify-content-center\"><p class=\"status" + order.getOrderID() + "\">" + order.getStatus() + "</p></div>\n"
                        + "                                    </div> \n");
            }
            out.print("</div> <div class=\"col-md-1 text-center d-flex flex-column justify-content-center border-start\">\n");
            if (order.getStatus().equals("unconfimred")) {
                out.print("<p class=\"order-action\" id=\"" + order.getOrderID() + "\" onclick=\"comfirm(" + order.getOrderID() + ")\">Comfirm?</p>\n");
            } else if (order.getStatus().equals("confirm")) {
                out.print("<p class=\"order-action\" id=\"" + order.getOrderID() + "\" onclick=\"delivery(" + order.getOrderID() + ")\">Delivery?</p>\n");
            } else {
                out.print("<p style=\"color: silver\">Delivered</p>\n");
            }
            out.print("  </div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"row\" style=\"background-color: rgb(245, 246, 250); border: 1px solid rgb(213, 213, 213);\">\n"
                    + "                                    <div class=\"col-md-11 row fw-normal\">\n"
                    + "                                    <div class=\"col-md-12 row\">\n"
                    + "                                        <div class=\"col-md-4 p-3 text-center\">Total:</div>\n"
                    + "                                        <div class=\"col-md-1 p-3 ps-0  text-center\">$"+((int)(totalPrice * 100)) / 100.0+"</div>\n"
                    + "                                    </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </div>");
        }

    }
}

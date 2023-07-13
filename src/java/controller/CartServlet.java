/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.CartDAO;
import database.ProductDAO;
import database.UserDAO;
import jakarta.servlet.RequestDispatcher;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Cart;
import model.Product;

/**
 *
 * @author USER
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        if (userName != null) {
            UserDAO udao = new UserDAO();
            String user_id = String.valueOf(udao.getUserByUsername(userName).getId());
            showCartList(request, response, user_id);
            RequestDispatcher rd = request.getRequestDispatcher("./views/cart.jsp");
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
            String action = request.getParameter("action");
            if (action.equals("add")) {
                addToCart(request, response, user_id);
            }
            if (action.equals("remove")) {
                removeFromCart(request, response, user_id);
                showCartList(request, response, user_id);
            }
            if (action.equals("show")) {
                showCartList(request, response, user_id);
            }
            if (action.equals("update")) {
                updateCart(request, response, user_id);
                showCartList(request, response, user_id);
            }
        } else {
            String data = "user not found";
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data);
        }

    }

    private void showCartList(HttpServletRequest request, HttpServletResponse response, String user_id) throws IOException, ServletException {
        ProductDAO dao = new ProductDAO();
        String cartItems = getCartSession(request, response, user_id);
        String data = "";
        if (cartItems != null) {
            List<Cart> listCartId = parseCarts(cartItems);
            for (Cart cartItem : listCartId) {
                Product p = dao.getProductByID(cartItem.getProduct_id());
                DecimalFormat df = new DecimalFormat("#.##");
                double price = p.getPrice() * (100 - p.getDiscount()) / 100;
                String formattedPrice = df.format(price);
                double amount = price * cartItem.getQuantity();
                String formattedAmount = df.format(amount);
                if (cartItem.getQuantity() > p.getQuantity()) {
                    cartItem.setQuantity(p.getQuantity());
                }
                String dataItem = "<tr id='product_" + p.getProductID() + "'>\n"
                        + "  <td class='pro-thumbnail'><a href='#'><img class='img-fluid' src='" + p.getImageURL() + "' alt='Product'></a></td>\n"
                        + "  <td class='pro-title'><a href='#'>" + p.getProductName() + "</a></td>\n"
                        + "  <td class='pro-price'><span>" + formattedPrice + "</span></td>\n"
                        + "  <td class='pro-quantity'>\n"
                        + "    <span class='quantity'>\n"
                        + "      <input class='pro-quantity--btn'  type='number' min='1' max='" + p.getQuantity() + "' step='1' value='" + cartItem.getQuantity() + "'>\n"
                        + "<div class=\"quantity-nav cart--quantity\"><div class=\"quantity-button quantity-up\">+</div><div class=\"quantity-button quantity-down\">-</div></div>"
                        + "    </span>\n"
                        + "  </td>\n"
                        + "  <td class='pro-subtotal'><span>" + formattedAmount + "</span></td>\n"
                        + "  <td class='pro-remove'><a href='' class='removeProduct productId_" + cartItem.getProduct_id() + "' id='" + p.getProductID() + "'><i class='fas fa-times' ></i></a></td>\n"
                        + "</tr>";
                data += dataItem + "\n";
            }
        } else {
            data = "not found";
        }
        request.setAttribute("dataList", data);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(data);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response, String user_id) {
        String id = request.getParameter("id");
        String q = request.getParameter("quantity");

        int quantity = 1;
        if (q != null) {
            quantity = Integer.parseInt(q);
        }
        quantity = 2;
        String cartItems = getCartSession(request, response, user_id);
        CartDAO cdao = new CartDAO();
        if (cartItems != null) {
            List<Cart> cartItemList = parseCarts(cartItems);
            boolean found = false;
            for (Cart cartItem : cartItemList) {
                if (cartItem.getProduct_id() == null ? id == null : cartItem.getProduct_id().equals(id)) {
                    ProductDAO dao = new ProductDAO();
                    Product p = dao.getProductByID(cartItem.getProduct_id());
                    int quantityTemp = cartItem.getQuantity() + quantity;
                    if (quantityTemp > p.getQuantity()) {
                        quantityTemp = p.getQuantity();
                    }
                    cartItem.setQuantity(quantityTemp);
                    found = true;
                    break;
                }
            }
            if (!found) {
                cartItemList.add(new Cart(id, quantity, "", user_id));
                cdao.insert(new Cart(id, quantity, "1", user_id));
            }
            cartItems = encodeCarts(cartItemList);
        } else {
            cartItems = encodeCarts(Arrays.asList(new Cart(id, quantity, "", "")));
        }

        returnCartSession(request, cartItems);
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response, String user_id) {
        String dataItems = request.getParameter("dataTransfer");
        String[] items = dataItems.split("@");
        CartDAO cdao = new CartDAO();

        String cartItems = getCartSession(request, response, user_id);
        if (cartItems != null) {
            List<Cart> cartItemList = parseCarts(cartItems);
            for (String item : items) {
                String id = item.split("_")[0];
                String quantity = item.split("_")[1];
                for (Cart cart : cartItemList) {
                    if (cart.getProduct_id().equals(id)) {
                        int cartC = cart.getQuantity();
                        int cartU = Integer.parseInt(quantity);
                        if (cartC != cartU) {
                            cart.setQuantity(cartU);
                            try {
                                cdao.update(cart);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
            }

            cartItems = encodeCarts(cartItemList);
        }

        returnCartSession(request, cartItems);
    }

    public static List<Cart> parseCarts(String cartItemsString) {
        List<Cart> cartItemList = new ArrayList<>();
        String[] cartItems = cartItemsString.split("@");
        for (String cartItem : cartItems) {
            String[] parts = cartItem.split("_");
            if (parts.length == 4) {
                String user_id = parts[0];
                String cart_id = parts[1];
                String product_id = parts[2];
                int quantity = Integer.parseInt(parts[3]);

                if (product_id.equals("null") == false) {
                    cartItemList.add(new Cart(product_id, quantity, cart_id, user_id));
                }
            }
        }
        return cartItemList;
    }

    public static String encodeCarts(List<Cart> cartItemList) {
        StringBuilder sb = new StringBuilder();
        for (Cart cartItem : cartItemList) {
            sb.append(cartItem.getUser_id()).append("_").append(cartItem.getCart_id()).append("_");
            sb.append(cartItem.getProduct_id()).append("_").append(cartItem.getQuantity()).append("@");
        }
        return sb.toString();
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response, String user_id) {
        String id = request.getParameter("id");
        String cartItems = getCartSession(request, response, user_id);
        CartDAO cdao = new CartDAO();
        if (cartItems != null) {
            List<Cart> cartItemList = parseCarts(cartItems);

            for (Cart cartItem : cartItemList) {
                if (cartItem.getProduct_id() == null ? id == null : cartItem.getProduct_id().equals(id)) {
                    cartItemList.remove(cartItem);
                    try {
                        cdao.removeByProductID(id);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

            }
            cartItems = encodeCarts(cartItemList);
        }
        returnCartSession(request, cartItems);
    }

    public String getCartSession(HttpServletRequest request, HttpServletResponse response, String user_id) {
        HttpSession session = request.getSession();
        String cartItems = (String) session.getAttribute("cartItems");
        if (cartItems == null || cartItems.trim().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            CartDAO cdao = new CartDAO();
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

    public void returnCartSession(HttpServletRequest request, String cartItems) {
        HttpSession session = request.getSession();
        session.setAttribute("cartItems", cartItems);
    }
//    public String getCartCookie(HttpServletRequest request, HttpServletResponse response) {
//        Cookie[] cookies = request.getCookies();
//        String cartItems = null;
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("cartItems")) {
//                    cartItems = cookie.getValue();
//                    break;
//                }
//            }
//        }
//        if (cartItems.trim().isEmpty() || cartItems == null) {
//            StringBuilder sb = new StringBuilder();
//            CartDAO cdao = new CartDAO();
//            List<Cart> listTemp = cdao.getCarts();
//            if (listTemp != null) {
//                for (Cart cart : listTemp) {
//                    System.out.println(cart.getCart_id() + "-" + cart.getUser_id() + "-" + cart.getProduct_id() + "-" + cart.getQuantity());
//
//                    sb.append(cart.getUser_id()).append("_").append(cart.getCart_id()).append("_");
//                    sb.append(cart.getProduct_id()).append("_").append(cart.getQuantity()).append("@");
//                }
//            } else {
//            }
//            cartItems = sb.toString();
//            returnCartSession(request, cartItems);
//        }
//        return cartItems;
//    }
//
//    public void returnCartCookie(HttpServletResponse response, String cartItems) {
//        Cookie cookie = new Cookie("cartItems", cartItems);
//        cookie.setMaxAge(60 * 60);
//        response.addCookie(cookie);
//    }
}

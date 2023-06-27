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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        showCartList(request, response);
        RequestDispatcher rd = request.getRequestDispatcher("./views/cart.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if (action.equals("add")) {
            addToCart(request, response);
        }
        if (action.equals("remove")) {
            removeFromMiniCart(request, response);
            showCartList(request, response);
        }
        if (action.equals("show")) {
            showCartList(request, response);
        }
        if (action.equals("update")) {
            updateCart(request, response);
            showCartList(request, response);
        }
    }

    private void showCartList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductDAO dao = new ProductDAO();
        String cartItems = getCartCookie(request, response);
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
                        + "<div class=\"quantity-nav\"><div class=\"quantity-button quantity-up\">+</div><div class=\"quantity-button quantity-down\">-</div></div>"
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

    private void addToCart(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String q = request.getParameter("quantity");

        int quantity = 1;
        if (q != null) {
            quantity = Integer.parseInt(q);
        }
        String cartItems = getCartCookie(request, response);
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
                cartItemList.add(new Cart(id, quantity, "", ""));
            }
            cartItems = encodeCarts(cartItemList);
        } else {
            cartItems = encodeCarts(Arrays.asList(new Cart(id, quantity, "", "")));
        }

        returnCartCookie(response, cartItems);
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) {
        String dataItems = request.getParameter("dataTransfer");
        String[] items = dataItems.split("@");

        String cartItems = getCartCookie(request, response);
        if (cartItems != null) {
            List<Cart> cartItemList = parseCarts(cartItems);
            for (String item : items) {
                String id = item.split("_")[0];
                String quantity = item.split("_")[1];
                for (Cart cart : cartItemList) {
                    if (cart.getProduct_id().equals(id)) {
                        cart.setQuantity(Integer.parseInt(quantity));
                    }
                }
            }

            cartItems = encodeCarts(cartItemList);
        }

        returnCartCookie(response, cartItems);
    }

    public static List<Cart> parseCarts(String cartItemsString) {
        List<Cart> cartItemList = new ArrayList<>();
        String[] cartItems = cartItemsString.split("@");
        for (String cartItem : cartItems) {
            String[] parts = cartItem.split("_");
            if (parts.length == 2) {
                String id = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                if (id.equals("null") == false) {
                    cartItemList.add(new Cart(id, quantity, "", ""));
                }
            }
        }
        return cartItemList;
    }

    public static String encodeCarts(List<Cart> cartItemList) {
        StringBuilder sb = new StringBuilder();
        for (Cart cartItem : cartItemList) {
            sb.append(cartItem.getProduct_id()).append("_").append(cartItem.getQuantity()).append("@");
        }
        return sb.toString();
    }

    public String getCartCookie(HttpServletRequest request, HttpServletResponse response) {
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

    private void returnCartCookie(HttpServletResponse response, String cartItems) {
        Cookie cookie = new Cookie("cartItems", cartItems);
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("remove");
        String cartItems = getCartCookie(request, response);
        if (cartItems != null) {
            List<Cart> cartItemList = parseCarts(cartItems);

            for (Cart cartItem : cartItemList) {
                if (cartItem.getProduct_id() == null ? id == null : cartItem.getProduct_id().equals(id)) {
                    cartItemList.remove(cartItem);
                    break;
                }
            }

            cartItems = encodeCarts(cartItemList);
        }

        returnCartCookie(response, cartItems);
    }

    private void removeFromMiniCart(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String cartItems = getCartCookie(request, response);
        if (cartItems != null) {
            List<Cart> cartItemList = parseCarts(cartItems);

            for (Cart cartItem : cartItemList) {
                if (cartItem.getProduct_id() == null ? id == null : cartItem.getProduct_id().equals(id)) {
                    cartItemList.remove(cartItem);
                    break;
                }
            }

            cartItems = encodeCarts(cartItemList);
        }

        returnCartCookie(response, cartItems);
    }

}

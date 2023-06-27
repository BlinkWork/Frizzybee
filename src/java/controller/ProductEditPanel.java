
package controller;

import database.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author hbich
 */
@WebServlet(name="ProductEditPanel", urlPatterns={"/editProduct"})
public class ProductEditPanel extends HttpServlet {
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("productId");
        ProductDAO p = new ProductDAO();
        request.setAttribute("product", p.getProductByID(id));
        request.getRequestDispatcher("views/productEdit.jsp").forward(request, response);

    }

}

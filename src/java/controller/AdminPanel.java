
package controller;

import database.ProductDAO;
import database.UserDAO;
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
@WebServlet(name="AdminPanel", urlPatterns={"/adminpanel"})
public class AdminPanel extends HttpServlet {
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        UserDAO u = new UserDAO();
        request.setAttribute("count", u.countNumberUser());
        request.getRequestDispatcher("/views/adminpage.jsp").forward(request, response);

    }
}

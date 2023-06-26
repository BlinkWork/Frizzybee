
package controller;

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
@WebServlet(name="UserEditPanel", urlPatterns={"/edituser"})
public class UserEditPanel extends HttpServlet {
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("userId");
        UserDAO u = new UserDAO();
        request.setAttribute("user", u.getUserByID(id));
        request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);

    }

}

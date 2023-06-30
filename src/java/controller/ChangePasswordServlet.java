package controller;

import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author hbich
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changepassword"})
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
        if (((String) session.getAttribute("username")).trim() == "") {
            response.sendRedirect(request.getContextPath());
            return;
        }
        request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        UserDAO u = new UserDAO();
        User user = u.getUserByUsername((String) session.getAttribute("username"));
        String currPassword = (String) request.getParameter("curr-password").trim();
        String newPassword = (String) request.getParameter("new-password").trim();
        String cnewPassword = (String) request.getParameter("conf-password").trim();
        if (currPassword == "") {
            request.setAttribute("errorMessage", "You must enter your current password");
            request.getRequestDispatcher("views/change-password.jsp").forward(request, response);
            return;
        }
        if (newPassword == "") {
            request.setAttribute("errorMessage", "You must enter your new password");
            request.getRequestDispatcher("views/change-password.jsp").forward(request, response);
            return;
        }
        if (cnewPassword == "") {
            request.setAttribute("errorMessage", "You must confirm you new password");
            request.getRequestDispatcher("views/change-password.jsp").forward(request, response);
            return;
        }
        if (!user.getPassword().equals(util.DataEncrypt.toSHA1(currPassword))) {
            request.setAttribute("errorMessage", "You current password is wrong");
            request.getRequestDispatcher("views/change-password.jsp").forward(request, response);
            return;
        }
        if (currPassword.equals(newPassword)) {
            request.setAttribute("errorMessage", "The new password is the same as the current password");
            request.getRequestDispatcher("views/change-password.jsp").forward(request, response);
            return;
        }
        if (!cnewPassword.equals(newPassword)) {
            request.setAttribute("errorMessage", "You re-enter the confirmation password that does not match the new password");
            request.getRequestDispatcher("views/change-password.jsp").forward(request, response);
            return;
        }

        user.setPassword(util.DataEncrypt.toSHA1(newPassword));
        u.update(user);
        response.sendRedirect(request.getContextPath());
    }
}

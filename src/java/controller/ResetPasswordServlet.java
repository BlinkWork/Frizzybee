package controller;

import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import model.User;

/**
 *
 * @author hbich
 */
@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/resetpassword"})
public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = null;
        String username = null;
        try {
            email = (String) request.getParameter("email");
        } catch (Exception e) {

        } finally {
            request.setAttribute("email", email);
            try {
                username = (String) request.getParameter("username");
            } catch (Exception e) {

            } finally {

                request.setAttribute("username", username);
                request.getRequestDispatcher("views/reset-password.jsp").forward(request, response);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = (String) request.getParameter("email").trim();
        String username = (String) request.getParameter("username").trim();
        if (username == "") {
            request.setAttribute("email", email);
            request.setAttribute("errorMessage", "You must enter your username");
            request.getRequestDispatcher("views/reset-password.jsp").forward(request, response);
            return;
        }
        if (email == "") {
            request.setAttribute("username", username);
            request.setAttribute("errorMessage", "You must enter your email");
            request.getRequestDispatcher("views/reset-password.jsp").forward(request, response);
            return;
        }
        UserDAO u = new UserDAO();
        User user = u.getUserByUsername(username);
        if (user == null) {
            request.setAttribute("username", username);
            request.setAttribute("errorMessage", "Username does not exist");
            request.getRequestDispatcher("views/reset-password.jsp").forward(request, response);
            return;
        }

        // Generate randomize password
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        String password = sb.toString();
        // ============================

        user.setPassword(util.DataEncrypt.toSHA1(password));
        u.update(user);
        request.setAttribute("password", password);
        request.getRequestDispatcher("views/reset-password.jsp").forward(request, response);
//        response.sendRedirect("login");
    }
}

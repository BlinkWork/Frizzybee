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

/**
 *
 * @author hbich
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String xUsername = request.getParameter("username").trim();
        String xPassword = request.getParameter("pass").trim();
        if (xUsername == "") {
            request.setAttribute("pass", xPassword);
            request.setAttribute("errorMessage", "You must enter your username");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }
        if (xPassword == "") {
            request.setAttribute("username", xUsername);
            request.setAttribute("errorMessage", "You must enter your password");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }
        UserDAO u = new UserDAO();
        if (!u.loginAccount(xUsername, xPassword)) {
            request.setAttribute("username", xUsername);
            request.setAttribute("pass", xPassword);
            request.setAttribute("errorMessage", "You have entered the wrong account or password");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", xUsername);
            response.sendRedirect(request.getContextPath());
        }
    }
}

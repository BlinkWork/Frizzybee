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
import java.sql.Date;
import model.User;

/**
 *
 * @author hbich
 */
@WebServlet(name = "UserUpdateInformationServlet", urlPatterns = {"/updateinformation"})
public class UserUpdateInformationServlet extends HttpServlet {

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
        UserDAO u = new UserDAO();
        User user = u.getUserByUsername((String) session.getAttribute("username"));
        request.setAttribute("name", user.getName());
        request.setAttribute("username", user.getUsername());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("address", user.getAddress());
        request.setAttribute("dob", user.getBirthday());
        request.setAttribute("gender", user.getSex());
        request.getRequestDispatcher("/views/update-information.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        UserDAO u = new UserDAO();
        User user = u.getUserByUsername((String) session.getAttribute("username"));
        String xName = (String) request.getParameter("name").trim();
        String xUsername = (String) request.getParameter("username").trim();
        String xEmail = (String) request.getParameter("email").trim();
        String xAddress = (String) request.getParameter("address").trim();
        Date xDate = (Date) Date.valueOf(request.getParameter("dob"));
        String xGender = (String) request.getParameter("gender").trim();
        if (xName == "") {
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("errorMessage", "You must enter your name");
            request.getRequestDispatcher("views/update-information.jsp").forward(request, response);
            return;
        }
        if (xUsername == "") {
            request.setAttribute("name", xName);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("errorMessage", "You must enter your username");
            request.getRequestDispatcher("views/update-information.jsp").forward(request, response);
            return;
        }
        if (xEmail == "") {
            request.setAttribute("name", xName);
            request.setAttribute("username", xUsername);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("errorMessage", "You must enter your email");
            request.getRequestDispatcher("views/update-information.jsp").forward(request, response);
            return;
        }
        if (xAddress == "") {
            request.setAttribute("name", xName);
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("errorMessage", "You must enter your address");
            request.getRequestDispatcher("views/update-information.jsp").forward(request, response);
            return;
        }
        try {
            xDate = Date.valueOf(request.getParameter("dob"));
        } catch (Exception e) {
            request.setAttribute("name", xName);
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("gender", xGender);
            request.setAttribute("errorMessage", "Your date of birth is invalid");
            request.getRequestDispatcher("views/update-information.jsp").forward(request, response);
            return;
        }
        if (xGender == "") {
            request.setAttribute("name", xName);
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("errorMessage", "You must select your gender");
            request.getRequestDispatcher("views/update-information.jsp").forward(request, response);
            return;
        }
        if (!session.getAttribute("username").equals(xUsername)) {
            if (u.checkDuplicateUsername(xUsername)) {
                request.setAttribute("name", xName);
                request.setAttribute("username", xUsername);
                request.setAttribute("email", xEmail);
                request.setAttribute("address", xAddress);
                request.setAttribute("dob", xDate);
                request.setAttribute("gender", xGender);
                request.setAttribute("errorMessage", "Your username has been taken.");
                request.getRequestDispatcher("views/update-information.jsp").forward(request, response);
                return;
            }
        }
        user.setName(xName);
        user.setEmail(xEmail);
        user.setBirthday(xDate);
        user.setSex(xGender);
        user.setUsername(xUsername);
        user.setAddress(xAddress);
        u.update(user);
        session.setAttribute("username", xUsername);
        response.sendRedirect(request.getContextPath());
    }
}

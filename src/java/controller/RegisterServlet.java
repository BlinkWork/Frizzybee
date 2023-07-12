package controller;

import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import model.User;

/**
 *
 * @author hbich
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String xFirstname = request.getParameter("fname").trim();
        String xLastname = request.getParameter("lname").trim();
        String xUsername = request.getParameter("username").trim();
        String xEmail = request.getParameter("email").trim();
        String xAddress = request.getParameter("address").trim();
        String xGender = request.getParameter("gender").trim();
        String xPass = request.getParameter("pass").trim();
        String xCPass = request.getParameter("cpass").trim();
        Date xDate = null;
        if (xFirstname == "") {
            request.setAttribute("lname", xLastname);
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("pass", xPass);
            request.setAttribute("cpass", xCPass);
            request.setAttribute("errorMessage", "You must enter your first name");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }
        if (xLastname == "") {
            request.setAttribute("fname", xFirstname);
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("pass", xPass);
            request.setAttribute("cpass", xCPass);
            request.setAttribute("errorMessage", "You must enter your last name");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }
        if (xUsername == "") {
            request.setAttribute("fname", xFirstname);
            request.setAttribute("lname", xLastname);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("pass", xPass);
            request.setAttribute("cpass", xCPass);
            request.setAttribute("errorMessage", "You must enter your username");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }
        if (xEmail == "") {
            request.setAttribute("fname", xFirstname);
            request.setAttribute("lname", xLastname);
            request.setAttribute("username", xUsername);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("pass", xPass);
            request.setAttribute("cpass", xCPass);
            request.setAttribute("errorMessage", "You must enter your email");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }
        if (xAddress == "") {
            request.setAttribute("fname", xFirstname);
            request.setAttribute("lname", xLastname);
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("pass", xPass);
            request.setAttribute("cpass", xCPass);
            request.setAttribute("errorMessage", "You must enter your address");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }
        try {
            xDate = Date.valueOf(request.getParameter("dob"));
        } catch (Exception e) {
            request.setAttribute("fname", xFirstname);
            request.setAttribute("lname", xLastname);
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("gender", xGender);
            request.setAttribute("pass", xPass);
            request.setAttribute("cpass", xCPass);
            request.setAttribute("errorMessage", "Your date of birth is invalid");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }
        if (xGender == "") {
            request.setAttribute("fname", xFirstname);
            request.setAttribute("lname", xLastname);
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("pass", xPass);
            request.setAttribute("cpass", xCPass);
            request.setAttribute("errorMessage", "You must select your gender");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }
        if (xPass == "") {
            request.setAttribute("fname", xFirstname);
            request.setAttribute("lname", xLastname);
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("cpass", xCPass);
            request.setAttribute("errorMessage", "You must enter your password");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }
        if (xCPass == "") {
            request.setAttribute("fname", xFirstname);
            request.setAttribute("lname", xLastname);
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("pass", xPass);
            request.setAttribute("errorMessage", "You must enter confirm password");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }
        if (!xPass.equals(xCPass)) {
            request.setAttribute("fname", xFirstname);
            request.setAttribute("lname", xLastname);
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("pass", xPass);
            request.setAttribute("cpass", xCPass);
            request.setAttribute("errorMessage", "Your password and your confirm password do not match");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }
        UserDAO u = new UserDAO();
        if (u.checkDuplicateUsername(xUsername)) {
            request.setAttribute("fname", xFirstname);
            request.setAttribute("lname", xLastname);
            request.setAttribute("username", xUsername);
            request.setAttribute("email", xEmail);
            request.setAttribute("address", xAddress);
            request.setAttribute("dob", xDate);
            request.setAttribute("gender", xGender);
            request.setAttribute("pass", xPass);
            request.setAttribute("cpass", xCPass);
            request.setAttribute("errorMessage", "Your username already exists");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
            return;
        }
        String avatarUrl = null;
        if (xGender.equals("male")) {
            avatarUrl = "resources/img/male.png";
        } else {
            avatarUrl = "resources/img/female.png";
        }
        User user = new User();
        user.setName(xFirstname.concat(" " + xLastname));
        user.setAvatarURL(avatarUrl);
        user.setUsername(xUsername);
        user.setPassword(util.DataEncrypt.toSHA1(xPass));
        user.setSex(xGender);
        user.setBirthday(xDate);
        user.setEmail(xEmail);
        user.setAddress(xAddress);
        user.setRole("user");
        u.insert(user);
        response.sendRedirect("login");
    }
}

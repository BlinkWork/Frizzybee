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
@WebServlet(name = "UserUpdateServlet", urlPatterns = {"/updateUser"})
public class UserUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = (String) request.getParameter("id").trim();
        String name = (String) request.getParameter("name").trim();
        String avatar = (String) request.getParameter("avatar").trim();
        String username = (String) request.getParameter("username").trim();
        String password = (String) request.getParameter("password").trim();
        String sex = (String) request.getParameter("sex").trim();
        Date birthday = null;
        String email = (String) request.getParameter("email").trim();
        String address = (String) request.getParameter("address").trim();
        String isSeller = (String) request.getParameter("isSeller").trim();
        String isAdmin = (String) request.getParameter("isAdmin").trim();
        UserDAO u = new UserDAO();
        String role;
        if (isAdmin.equals("true")) {
            role = "admin";
        } else if (isSeller.equals("true")) {
            role = "seller";
        } else {
            role = "default";
        }

        if (username == "") {
            request.setAttribute("error", "You must enter an username");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        if (name == "") {
            request.setAttribute("error", "You must enter an name");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        if (avatar == "") {
            request.setAttribute("error", "You must enter an avatar url");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        if (password == "") {
            request.setAttribute("error", "You must enter an password");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        if (sex == "") {
            request.setAttribute("error", "You must choose between male or female");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        try {
            birthday = Date.valueOf(request.getParameter("birthday"));
        } catch (Exception e) {
            request.setAttribute("error", "You must enter an valid birthday");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        if (email == "") {
            request.setAttribute("error", "You must enter an email");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        if (address == "") {
            request.setAttribute("error", "You must enter an address");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        if (isSeller == "") {
            request.setAttribute("error", "You must set the value of isSeller to true or false");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        if (isAdmin == "") {
            request.setAttribute("error", "You must set the value of isAdmin to true or false");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        if (!u.getUserByID(id).getUsername().equals(username) && u.checkDuplicateUsername(username)){
            request.setAttribute("error", "Username already existed");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        User user = new User(Integer.parseInt(id), name, avatar, username, util.DataEncrypt.toSHA1(password), sex, birthday, email, address, role);
        u.update(user);
        response.sendRedirect("usermanagement");
    }
}

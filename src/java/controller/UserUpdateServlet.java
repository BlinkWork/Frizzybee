package controller;

import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.Base64;
import model.User;

/**
 *
 * @author hbich
 */
@WebServlet(name = "UserUpdateServlet", urlPatterns = {"/updateUser"})
@MultipartConfig
public class UserUpdateServlet extends HttpServlet {

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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = (String) request.getParameter("id").trim();
        String name = (String) request.getParameter("name").trim();
        String username = (String) request.getParameter("username").trim();
        String password = (String) request.getParameter("password").trim();
        String sex = (String) request.getParameter("sex").trim();
        Date birthday = null;
        Part filePart = null;
        String remain = request.getParameter("remain").trim();
        String imageBase64 = null;
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

        if (name == "") {
            request.setAttribute("error", "You must enter an name");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        if (username == "") {
            request.setAttribute("error", "You must enter an username");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        if (remain.equals("true")) {
            imageBase64 = (String) request.getParameter("imagePath");
        } else {
            try {
                filePart = request.getPart("image");
                byte[] imageData = convertToByteArray(filePart);
                imageBase64 = Base64.getEncoder().encodeToString(imageData);
                if (imageBase64.equals("") || imageBase64.equals(null)) throw new Exception();
                imageBase64 = "data:image/jpeg;base64," + imageBase64;
            } catch (Exception e) {
                request.setAttribute("error", "You must upload an valid image");
                request.setAttribute("user", u.getUserByID(id));
                request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
                return;
            }
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
        if (!u.getUserByID(id).getUsername().equals(username) && u.checkDuplicateUsername(username)) {
            request.setAttribute("error", "Username already existed");
            request.setAttribute("user", u.getUserByID(id));
            request.getRequestDispatcher("views/userEdit.jsp").forward(request, response);
            return;
        }
        if (!u.getUserByID(id).getPassword().equals(password)) {
            password = util.DataEncrypt.toSHA1(password);
        }
        User user = new User(Integer.parseInt(id), name, imageBase64, username, password, sex, birthday, email, address, role);
        u.update(user);
        response.sendRedirect("usermanagement");
    }

    private byte[] convertToByteArray(Part filePart) throws IOException {
        InputStream inputStream = filePart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        byte[] imageData = outputStream.toByteArray();
        outputStream.close();

        return imageData;
    }
}

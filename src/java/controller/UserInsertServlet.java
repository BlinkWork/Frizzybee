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
@WebServlet(name = "UserInsertServlet", urlPatterns = {"/insertUser"})
@MultipartConfig
public class UserInsertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("views/userInsert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        Date birthday = null;
        Part filePart = null;
        String imageBase64;
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
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("sex", sex);
            request.setAttribute("birthday", birthday);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("isSeller", isSeller);
            request.setAttribute("isAdmin", isAdmin);
            request.getRequestDispatcher("views/userInsert.jsp").forward(request, response);
            return;
        }
        if (username == "") {
            request.setAttribute("error", "You must enter an username");
            request.setAttribute("name", name);
            request.setAttribute("password", password);
            request.setAttribute("sex", sex);
            request.setAttribute("birthday", birthday);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("isSeller", isSeller);
            request.setAttribute("isAdmin", isAdmin);
            request.getRequestDispatcher("views/userInsert.jsp").forward(request, response);
            return;
        }
        try {
            filePart = request.getPart("avatar");
            byte[] imageData = convertToByteArray(filePart);
            imageBase64 = Base64.getEncoder().encodeToString(imageData);
            if (imageBase64.equals("") || imageBase64.equals(null)) throw new Exception();
            imageBase64 = "data:image/jpeg;base64," + imageBase64;
        } catch (Exception e) {
            request.setAttribute("error", "You must upload an valid image");
            request.setAttribute("name", name);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("sex", sex);
            request.setAttribute("birthday", birthday);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("isSeller", isSeller);
            request.setAttribute("isAdmin", isAdmin);
            request.getRequestDispatcher("views/userInsert.jsp").forward(request, response);
            return;
        }
        if (password == "") {
            request.setAttribute("error", "You must enter an password");
            request.setAttribute("name", name);
            request.setAttribute("username", username);
            request.setAttribute("sex", sex);
            request.setAttribute("birthday", birthday);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("isSeller", isSeller);
            request.setAttribute("isAdmin", isAdmin);
            request.getRequestDispatcher("views/userInsert.jsp").forward(request, response);
            return;
        }
        if (sex == "") {
            request.setAttribute("error", "You must choose between male or female");
            request.setAttribute("name", name);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("birthday", birthday);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("isSeller", isSeller);
            request.setAttribute("isAdmin", isAdmin);
            request.getRequestDispatcher("views/userInsert.jsp").forward(request, response);
            return;
        }
        try {
            birthday = Date.valueOf(request.getParameter("birthday"));
        } catch (Exception e) {
            request.setAttribute("error", "You must enter an valid birthday");
            request.setAttribute("name", name);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("sex", sex);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("isSeller", isSeller);
            request.setAttribute("isAdmin", isAdmin);
            request.getRequestDispatcher("views/userInsert.jsp").forward(request, response);
            return;
        }
        if (email == "") {
            request.setAttribute("error", "You must enter an email");
            request.setAttribute("name", name);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("sex", sex);
            request.setAttribute("birthday", birthday);
            request.setAttribute("address", address);
            request.setAttribute("isSeller", isSeller);
            request.setAttribute("isAdmin", isAdmin);
            request.getRequestDispatcher("views/userInsert.jsp").forward(request, response);
            return;
        }
        if (address == "") {
            request.setAttribute("error", "You must enter an address");
            request.setAttribute("name", name);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("sex", sex);
            request.setAttribute("birthday", birthday);
            request.setAttribute("email", email);
            request.setAttribute("isSeller", isSeller);
            request.setAttribute("isAdmin", isAdmin);
            request.getRequestDispatcher("views/userInsert.jsp").forward(request, response);
            return;
        }
        if (isSeller == "") {
            request.setAttribute("error", "You must set the value of isSeller to true or false");
            request.setAttribute("name", name);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("sex", sex);
            request.setAttribute("birthday", birthday);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("isAdmin", isAdmin);
            request.getRequestDispatcher("views/userInsert.jsp").forward(request, response);
            return;
        }
        if (isAdmin == "") {
            request.setAttribute("error", "You must set the value of isAdmin to true or false");
            request.setAttribute("name", name);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("sex", sex);
            request.setAttribute("birthday", birthday);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("isSeller", isSeller);
            request.getRequestDispatcher("views/userInsert.jsp").forward(request, response);
            return;
        }
        if (u.checkDuplicateUsername(username)) {
            request.setAttribute("error", "Username already existed");
            request.setAttribute("name", name);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("sex", sex);
            request.setAttribute("birthday", birthday);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("isSeller", isSeller);
            request.setAttribute("isAdmin", isAdmin);
            request.getRequestDispatcher("views/userInsert.jsp").forward(request, response);
            return;
        }
        User user = new User();
        user.setName(name);
        user.setAvatarURL(imageBase64);
        user.setUsername(username);
        user.setPassword(util.DataEncrypt.toSHA1(password));
        user.setSex(sex);
        user.setBirthday(birthday);
        user.setEmail(email);
        user.setAddress(address);
        user.setRole(role);
        u.insert(user);
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

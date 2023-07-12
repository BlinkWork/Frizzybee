package controller;

import database.BrandDAO;
import database.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "BrandRemoveServlet", urlPatterns = {"/removeBrand"})
public class BrandRemoveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("brandId");
        BrandDAO b = new BrandDAO();
        b.deleteByID(id);
        response.sendRedirect("brandmanagement");
    }

}

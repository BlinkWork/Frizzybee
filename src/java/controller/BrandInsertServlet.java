package controller;

import database.BrandDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Brand;

@WebServlet(name = "BrandInsertServlet", urlPatterns = {"/insertBrand"})
public class BrandInsertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("views/brandInsert.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = (String) request.getParameter("brand_name").trim();
        BrandDAO b = new BrandDAO();
        if (name == "") {
            request.setAttribute("error", "You must enter an name");
            request.getRequestDispatcher("views/brandInsert.jsp").forward(request, response);
            return;
        }
        Brand brand = new Brand();
        brand.setBrandName(name);
        b.insert(brand);
        response.sendRedirect("brandmanagement");
    }

}

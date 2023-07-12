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

/**
 *
 * @author hbich
 */
@WebServlet(name = "BrandUpdateServlet", urlPatterns = {"/editBrand"})
public class BrandUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("brandId");
        BrandDAO b = new BrandDAO();
        request.setAttribute("brand", b.getBrandByID(id));
        request.getRequestDispatcher("views/brandEdit.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String brand_id = (String) request.getParameter("brand_id").trim();
        String brand_name = (String) request.getParameter("brand_name").trim();
       BrandDAO b = new BrandDAO();
        if (brand_name == "") {
            request.setAttribute("error", "You must enter an name");
            request.setAttribute("brand", b.getBrandByID(brand_id));
            request.getRequestDispatcher("views/brandEdit.jsp").forward(request, response);
            return;
        }
        Brand brand = b.getBrandByID(brand_id);
        brand.setBrandName(brand_name);
        b.update(brand);
        response.sendRedirect("brandmanagement");
    }
}

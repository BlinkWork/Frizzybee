package controller;

import database.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;

/**
 *
 * @author hbich
 */
@WebServlet(name = "CategoryPanel", urlPatterns = {"/editCategory"})
public class CategoryUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("categoryId");
        CategoryDAO c = new CategoryDAO();
        request.setAttribute("category", c.getCategoryByID(id));
        request.getRequestDispatcher("views/categoryEdit.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String category_id = (String) request.getParameter("category_id").trim();
        String category_name = (String) request.getParameter("category_name").trim();
        CategoryDAO c = new CategoryDAO();
        if (category_name == "") {
            request.setAttribute("error", "You must enter an name");
            request.setAttribute("category", c.getCategoryByID(category_id));
            request.getRequestDispatcher("views/categoryEdit.jsp").forward(request, response);
            return;
        }
        Category category = c.getCategoryByID(category_id);
        category.setCategoryName(category_name);
        c.update(category);
        response.sendRedirect("categorymanagement");
    }
}

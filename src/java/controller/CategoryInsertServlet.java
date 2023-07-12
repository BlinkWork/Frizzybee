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

@WebServlet(name = "CategoryInsertServlet", urlPatterns = {"/insertCategory"})
public class CategoryInsertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("views/categoryInsert.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = (String) request.getParameter("category_name").trim();
        CategoryDAO c = new CategoryDAO();
        if (name == "") {
            request.setAttribute("error", "You must enter an name");
            request.getRequestDispatcher("views/categoryInsert.jsp").forward(request, response);
            return;
        }
        Category category = new Category();
        category.setCategoryName(name);
        c.insert(category);
        response.sendRedirect("categorymanagement");
    }

}

package controller;

import database.BrandDAO;
import database.CategoryDAO;
import database.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author hbich
 */
@WebServlet(name = "ProductInsertServlet", urlPatterns = {"/insertProduct"})
public class ProductInsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = (String) request.getParameter("product_id").trim();
        String name = (String) request.getParameter("product_name").trim();
        String description = (String) request.getParameter("product_description").trim();
        String category_id = (String) request.getParameter("category_id").trim();
        String brand_id = (String) request.getParameter("brand_id").trim();
        Double price = 0.0;
        int quantity = 0;
        String image = (String) request.getParameter("image").trim();
        int discount = 0;
        ProductDAO p = new ProductDAO();

        if (id == "") {
            request.setAttribute("product_name",name);
            request.setAttribute("product_description",description);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("image",image);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must enter an id");
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        if (name == "") {
            request.setAttribute("product_id",id);
            request.setAttribute("product_description",description);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("image",image);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must enter an name");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        if (description == "") {
            request.setAttribute("product_id",id);
            request.setAttribute("product_name",name);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("image",image);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must enter an description");
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        if (category_id == "") {
            request.setAttribute("product_id",id);
            request.setAttribute("product_description",description);
            request.setAttribute("product_name",name);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("image",image);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must select an category id");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        if (brand_id == "") {
            request.setAttribute("product_id",id);
            request.setAttribute("product_description",description);
            request.setAttribute("product_name",name);
            request.setAttribute("category_id",category_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("image",image);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must select an brand id");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (Exception e) {
            request.setAttribute("product_id",id);
            request.setAttribute("product_description",description);
            request.setAttribute("product_name",name);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("quantity",quantity);
            request.setAttribute("image",image);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must enter an valid price");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        try {
            quantity = Integer.parseInt(request.getParameter("quantity").trim());
        } catch (Exception e) {
            request.setAttribute("product_id",id);
            request.setAttribute("product_description",description);
            request.setAttribute("product_name",name);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("image",image);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must enter an valid quantity");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        if (image == "") {
            request.setAttribute("product_id",id);
            request.setAttribute("product_description",description);
            request.setAttribute("product_name",name);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must enter an image url");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        try {
            discount = Integer.parseInt(request.getParameter("discount").trim());
        } catch (Exception e) {
            request.setAttribute("product_id",id);
            request.setAttribute("product_description",description);
            request.setAttribute("product_name",name);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("image",image);
            request.setAttribute("error", "You must enter an valid discount");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        CategoryDAO c = new CategoryDAO();
        BrandDAO b = new BrandDAO();
        Product product = new Product(Integer.parseInt(id), name, description, c.getCategoryByID(category_id), b.getBrandByID(brand_id), price, quantity, image, discount);
        p.insert(product);
        response.sendRedirect("productmanagement");
    }
}

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
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import model.Product;
import jakarta.servlet.annotation.MultipartConfig;

/**
 *
 * @author hbich
 */
@WebServlet(name = "ProductUpdateServlet", urlPatterns = {"/updateProduct"})
@MultipartConfig
public class ProductUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("productId");
        ProductDAO p = new ProductDAO();
        request.setAttribute("product", p.getProductByID(id));
        request.getRequestDispatcher("views/productEdit.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("product_id").trim();
        String name = request.getParameter("product_name").trim();
        String description = request.getParameter("product_description").trim();
        String category_id = request.getParameter("category_id").trim();
        String brand_id = request.getParameter("brand_id").trim();
        String remain = request.getParameter("remain").trim();
        Double price = 0.0;
        int quantity = 0;
        Part filePart = null;
        String imageBase64 = null;
        int discount = 0;
        ProductDAO p = new ProductDAO();

        if (name == "") {
            request.setAttribute("error", "You must enter an name");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productEdit.jsp").forward(request, response);
            return;
        }
        if (description == "") {
            request.setAttribute("error", "You must enter an description");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productEdit.jsp").forward(request, response);
            return;
        }
        if (category_id == "") {
            request.setAttribute("error", "You must select an category id");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productEdit.jsp").forward(request, response);
            return;
        }
        if (brand_id == "") {
            request.setAttribute("error", "You must select an brand id");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productEdit.jsp").forward(request, response);
            return;
        }
        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (Exception e) {
            request.setAttribute("error", "You must enter an valid price");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productEdit.jsp").forward(request, response);
            return;
        }
        try {
            quantity = Integer.parseInt(request.getParameter("quantity").trim());
        } catch (Exception e) {
            request.setAttribute("error", "You must enter an valid quantity");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productEdit.jsp").forward(request, response);
            return;
        }
        if (remain.equals("true")) {
            imageBase64 = (String) request.getParameter("imagePath");
        } else {
            try {
                filePart = request.getPart("image");
                byte[] imageData = convertToByteArray(filePart);
                imageBase64 = Base64.getEncoder().encodeToString(imageData);
                if (imageBase64.equals("") || imageBase64.equals(null)) {
                    throw new Exception();
                }
                imageBase64 = "data:image/jpeg;base64," + imageBase64;
            } catch (Exception e) {

                request.setAttribute("error", e.toString());
                request.setAttribute("product", p.getProductByID(id));
                request.getRequestDispatcher("views/productEdit.jsp").forward(request, response);
                return;
            }
        }
        try {
            discount = Integer.parseInt(request.getParameter("discount").trim());
        } catch (Exception e) {
            request.setAttribute("error", "You must enter an valid discount");
            request.setAttribute("product", p.getProductByID(id));
            request.getRequestDispatcher("views/productEdit.jsp").forward(request, response);
            return;
        }
        CategoryDAO c = new CategoryDAO();
        BrandDAO b = new BrandDAO();
        Product product = new Product(Integer.parseInt(id), name, description, c.getCategoryByID(category_id), b.getBrandByID(brand_id), price, quantity, imageBase64, discount);
        p.update(product);
        response.sendRedirect("productmanagement");
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

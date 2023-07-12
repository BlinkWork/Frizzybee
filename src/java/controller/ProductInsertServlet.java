package controller;

import database.BrandDAO;
import database.CategoryDAO;
import database.ProductDAO;
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
import java.util.Base64;
import model.Product;

/**
 *
 * @author hbich
 */
@WebServlet(name = "ProductInsertServlet", urlPatterns = {"/insertProduct"})
@MultipartConfig

public class ProductInsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String seller_id = (String) request.getParameter("seller_id").trim();
        String name = (String) request.getParameter("product_name").trim();
        String description = (String) request.getParameter("product_description").trim();
        String category_id = (String) request.getParameter("category_id").trim();
        String brand_id = (String) request.getParameter("brand_id").trim();
        Double price = 0.0;
        int quantity = 0;
        Part filePart = null;
        String imageBase64;
        int discount = 0;
        ProductDAO p = new ProductDAO();
        if (seller_id == "") {
            request.setAttribute("product_name",name);
            request.setAttribute("product_description",description);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must enter an name");
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        if (name == "") {
            request.setAttribute("seller_id",seller_id);
            request.setAttribute("product_description",description);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must enter an name");
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        if (description == "") {
            request.setAttribute("seller_id",seller_id);
            request.setAttribute("product_name",name);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must enter an description");
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        if (category_id == "") {
            request.setAttribute("seller_id",seller_id);
            request.setAttribute("product_description",description);
            request.setAttribute("product_name",name);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must select an category id");
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        if (brand_id == "") {
            request.setAttribute("seller_id",seller_id);
            request.setAttribute("product_description",description);
            request.setAttribute("product_name",name);
            request.setAttribute("category_id",category_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must select an brand id");
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (Exception e) {
            request.setAttribute("seller_id",seller_id);
            request.setAttribute("product_description",description);
            request.setAttribute("product_name",name);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("quantity",quantity);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must enter an valid price");
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        try {
            quantity = Integer.parseInt(request.getParameter("quantity").trim());
        } catch (Exception e) {
            request.setAttribute("seller_id",seller_id);
            request.setAttribute("product_description",description);
            request.setAttribute("product_name",name);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must enter an valid quantity");
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        try {
            filePart = request.getPart("image");
            byte[] imageData = convertToByteArray(filePart);
            imageBase64 = Base64.getEncoder().encodeToString(imageData);
            if (imageBase64.equals("") || imageBase64.equals(null)) throw new Exception();
            imageBase64 = "data:image/jpeg;base64," + imageBase64;
        } catch (Exception e) {
            request.setAttribute("seller_id",seller_id);
            request.setAttribute("product_description",description);
            request.setAttribute("product_name",name);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("discount",discount);
            request.setAttribute("error", "You must upload an valid image");
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        try {
            discount = Integer.parseInt(request.getParameter("discount").trim());
        } catch (Exception e) {
            request.setAttribute("seller_id",seller_id);
            request.setAttribute("product_description",description);
            request.setAttribute("product_name",name);
            request.setAttribute("category_id",category_id);
            request.setAttribute("brand_id",brand_id);
            request.setAttribute("price",price);
            request.setAttribute("quantity",quantity);
            request.setAttribute("error", "You must enter an valid discount");
            request.getRequestDispatcher("views/productInsert.jsp").forward(request, response);
            return;
        }
        CategoryDAO c = new CategoryDAO();
        BrandDAO b = new BrandDAO();
        Product product = new Product();
        product.setSellerID(Integer.parseInt(seller_id));
        product.setProductName(name);
        product.setDescription(description);
        product.setCategory(c.getCategoryByID(category_id));
        product.setBrand(b.getBrandByID(brand_id));
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setImageURL(imageBase64);
        product.setDiscount(discount);
        p.insert(product);
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

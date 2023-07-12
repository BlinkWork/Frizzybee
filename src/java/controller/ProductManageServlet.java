/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.BrandDAO;
import database.CategoryDAO;
import database.ProductDAO;
import database.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;
import model.Brand;
import model.Product;
import model.User;

/**
 *
 * @author Admin
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 10, // 10 KB
        maxFileSize = 1024 * 300, // 300 KB
        maxRequestSize = 1024 * 1024 // 1 MB 
)
public class ProductManageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void renderProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        String spageid = request.getParameter("page");
        int pageid;
        if (spageid == null) {
            pageid = 1;
        } else {
            pageid = Integer.parseInt(spageid);
        }
        request.setAttribute("pageid", pageid);
        int total = 9;
        if (pageid == 1) {
            pageid = 0;
        } else {
            pageid = pageid - 1;
            pageid = pageid * total + 1;
        }
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        UserDAO userDAO = new UserDAO();
        User curUser = userDAO.getUserByUsername(username);
        List<Product> listProduct = null;
        if (curUser != null) {
            listProduct = dao.getProductsByPageBySeller(pageid, total, curUser.getId());

        }
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher rs = request.getRequestDispatcher("./views/shop-list.jsp");
        rs.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        if (userName != null) {
            UserDAO udao = new UserDAO();
            String role = udao.getUserByUsername(userName).getRole();
            if (role.equals("admin") == false) {
                response.sendRedirect("./404");
                return;
            }
        } else {
            response.sendRedirect("./404");
            return;

        }
        String event = request.getParameter("event");
        getLatestProduct(request, response);
        if (event.equals("product-management")) {
            renderProduct(request, response);
        } else if (event.equals("product-detail")) {
            renderProductDetails(request, response);
        } else if (event.equals("send-to-add")) {
            request.getRequestDispatcher("./views/add-product.jsp").forward(request, response);
        } else if (event.equals("sort-product")) {
            renderSortProductList(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String event = request.getParameter("event");
        switch (event) {
            case "product-management":
                renderProduct(request, response);
                break;
            case "product-detail":
                renderProductDetails(request, response);
                break;
            case "product-edit":
                renderFormProductEdit(request, response);
                break;
            case "edit-product":
                editProduct(request, response);
                break;
            case "product-delete":
                deleteProduct(request, response);
                break;
            case "create-product":
                createProduct(request, response);
                break;

            default:
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void renderProductDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("product-id");
        response.sendRedirect("./productDetails?id=" +id);
//        ProductDAO dao = new ProductDAO();
//        Product product = dao.getProductByID(id);
//        if (product != null) {
//            request.setAttribute("productDetail", product);
//            System.out.println(product.getProductName());
//            RequestDispatcher rd = request.getRequestDispatcher("./views/product-details.jsp");
//            rd.forward(request, response);
//        }
    }

    protected void renderFormProductEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("product-id");
        ProductDAO dao = new ProductDAO();
        Product product = dao.getProductByID(id);
        if (product != null) {
            request.setAttribute("product", product);
            RequestDispatcher rd = request.getRequestDispatcher("./views/product-edit.jsp");
            rd.forward(request, response);
        }
    }

    protected void editProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String brand = request.getParameter("brand");
        String priceInput = request.getParameter("price");
        String quantityInput = request.getParameter("quantity");
        String discountInput = request.getParameter("discount");
        String newImg = request.getParameter("productURL");
        ProductDAO productDAO = new ProductDAO();
        BrandDAO brandDAO = new BrandDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        double price = 0;
        int quantity = 0;
        int discount = 0;
        String ProductID = request.getParameter("ProductID");
        String imageURL = productDAO.getProductByID(ProductID).getImageURL();
        // Lấy phần tệp được tải lên
        try {
            Part filePart = request.getPart("productImage");
            String fileName = filePart.getSubmittedFileName();

            // Lưu tệp vào đường dẫn cụ thể trên server
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String url = uploadPath + File.separator + fileName;
            if(fileName.length()>0){
                imageURL = "./uploads/" + fileName;
            }else{
                throw new IOException();
            }
            
            filePart.write(url);
        } catch (IOException e) {
            if (newImg.length() > 0) {
                imageURL = newImg;
            }
        }
        System.out.println(imageURL);
        //validate input
        boolean check = true;
        if (productName.isEmpty()) {
            check = false;
            request.setAttribute("productNameErr", "*Product name can not be left blank!");
        }
        if (description.isEmpty()) {
            check = false;
            request.setAttribute("descriptionErr", "*Description can not be left blank!");
        }
        if (brand.isEmpty()) {
            check = false;
            request.setAttribute("brandErr", "*Brand can not be left blank!");
        } else {
            if (brandDAO.getBrandByName(brand) == null) {
                brandDAO.insert(new Brand(brand));
            }
        }
        if (category.isEmpty()) {
            check = false;
            request.setAttribute("categoryErr", "*Category can not be left blank!");
        }
        if (priceInput.isEmpty()) {
            check = false;
            request.setAttribute("priceErr", "*Price can not be left blank!");
        } else {
            try {
                System.out.println(priceInput);
                price = Double.parseDouble(priceInput);
            } catch (NumberFormatException e) {
                check = false;
                request.setAttribute("priceErr", "*Price must be a number!");
            }
        }
        if (quantityInput.isEmpty()) {
            check = false;
            request.setAttribute("quantityErr", "*quantity can not be left blank!");
        } else {
            try {
                quantity = Integer.parseInt(quantityInput);
            } catch (NumberFormatException e) {
                check = false;
                request.setAttribute("quantityErr", "*Quanity must be a number!");
            }
        }
        if (discountInput.isEmpty()) {
            check = false;
            discount = 0;
        } else {
            try {
                discount = Integer.parseInt(discountInput);
            } catch (NumberFormatException e) {
                check = false;
                request.setAttribute("discountErr", "*Discount must be a number!");
            }
        }
        if (!check) {
            request.setAttribute("validate", check);
            request.getRequestDispatcher("./views/product-edit.jsp").forward(request, response);
        } else {
            Product product = new Product(Integer.parseInt(ProductID.trim()), productName, description, categoryDAO.getCategoryByName(category), brandDAO.getBrandByName(brand), price, quantity, imageURL, discount);
            productDAO.update(product);
            response.sendRedirect("product-management?event=product-management");
        }
    }

    protected void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("product-id");
        ProductDAO dao = new ProductDAO();
        Product product = dao.getProductByID(id);
        if (product != null) {
            dao.deleteByID(id);
            response.sendRedirect("product-management?event=product-management");
        }
    }

    protected void createProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        System.out.println("username: " + username);
        UserDAO userDAO = new UserDAO();
        User curUser = userDAO.getUserByUsername(username);
        System.out.println("ID: " + curUser.getId());
        int sellerID = curUser.getId();
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String brand = request.getParameter("brand");
        String priceInput = request.getParameter("price");
        String quantityInput = request.getParameter("quantity");
        String discountInput = request.getParameter("discount");
        String newImg = request.getParameter("productURL");
        ProductDAO productDAO = new ProductDAO();
        BrandDAO brandDAO = new BrandDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        double price = 0;
        int quantity = 0;
        int discount = 0;
        String imageURL = "./resources/img/product-default.jpg";
        // Lấy phần tệp được tải lên
        try {
            Part filePart = request.getPart("productImage");
            String fileName = filePart.getSubmittedFileName();

            // Lưu tệp vào đường dẫn cụ thể trên server
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String url = uploadPath + File.separator + fileName;
            imageURL = "./uploads/" + fileName;
            filePart.write(url);
        } catch (IOException e) {
            if (newImg.length() > 0) {
                imageURL = newImg;
            }
        }

        //validate input
        boolean check = true;
        System.out.println("first:  " + check);
        if (productName.isEmpty()) {
            check = false;
            request.setAttribute("productNameErr", "*Product name can not be left blank!");
        }
        if (description.isEmpty()) {
            check = false;
            request.setAttribute("descriptionErr", "*Description can not be left blank!");
        }
        if (brand.isEmpty()) {
            check = false;
            request.setAttribute("brandErr", "*Brand can not be left blank!");
        } else {
            if (brandDAO.getBrandByName(brand) == null) {
                brandDAO.insert(new Brand(brand));
            }
        }
        if (priceInput.isEmpty()) {
            check = false;
            request.setAttribute("priceErr", "*Price can not be left blank!");
        } else {
            try {
                price = Double.parseDouble(priceInput);
            } catch (NumberFormatException e) {
                check = false;
                request.setAttribute("priceErr", "*Price must be a number!");
            }
        }
        if (quantityInput.isEmpty()) {
            check = false;
            request.setAttribute("quantityErr", "*quantity can not be left blank!");
        } else {
            try {
                quantity = Integer.parseInt(quantityInput);
            } catch (NumberFormatException e) {
                check = false;
                request.setAttribute("quantityErr", "*Quanity must be a number!");
            }
        }
        if (discountInput.isEmpty()) {
            discount = 0;
        } else {
            try {
                discount = Integer.parseInt(discountInput);
            } catch (NumberFormatException e) {
                check = false;
                request.setAttribute("discountErr", "*Discount must be a number!");
            }
        }
        if (!check) {
            request.setAttribute("validate", check);
            request.setAttribute("productName", productName);
            request.setAttribute("description", description);
            request.setAttribute("category", category);
            request.setAttribute("brand", brand);
            request.setAttribute("imageURL", imageURL);
            System.out.println(check);
            request.getRequestDispatcher("./views/add-product.jsp").forward(request, response);
        } else {
            Product product = new Product(productName, sellerID, description, categoryDAO.getCategoryByName(category), brandDAO.getBrandByName(brand), price, quantity, imageURL, discount);
            productDAO.insert(product);
            System.out.println("oke oke");
            response.sendRedirect("product-management?event=product-management");
        }
    }

    private void renderSortProductList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void getLatestProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        ShowListServlet sl = new ShowListServlet();
        sl.getLatestProducts(dao, request, response);
    }
}

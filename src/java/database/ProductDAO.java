package database;

import java.util.*;
import java.sql.*;
import model.Product;
import model.Brand;
import model.Category;

public class ProductDAO extends MyDAO {

    public List<Product> getProducts() {
        List<Product> t = new ArrayList<>();
        xSql = "SELECT *  FROM [dbo].[Product]";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int productID;
            String productName;
            String description;
            Category category;
            Brand brand;
            double price;
            int quantity;
            String imageURL;
            int discount;
            Product x;
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();
            while (rs.next()) {
                productID = rs.getInt("product_id");
                productName = rs.getString("product_name");
                description = rs.getString("product_description");
                category = categoryDAO.getCategoryByID(rs.getInt("category_id") + "");
                brand = brandDAO.getBrandByID(rs.getInt("brand_id") + "");
                price = rs.getDouble("price");
                quantity = rs.getInt("quantity");
                imageURL = rs.getString("image");
                discount = rs.getInt("discount");
                x = new Product(productID, productName, description, category, brand, price, quantity, imageURL, discount);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Product> getProductsBySeller(int sellerID) {
        List<Product> t = new ArrayList<>();
        xSql = "SELECT *  FROM [dbo].[Product] where [seller_id] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, sellerID);
            rs = ps.executeQuery();
            int productID;
            String productName;
            String description;
            Category category;
            Brand brand;
            double price;
            int quantity;
            String imageURL;
            int discount;
            Product x;
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();
            while (rs.next()) {
                productID = rs.getInt("product_id");
                productName = rs.getString("product_name");
                description = rs.getString("product_description");
                category = categoryDAO.getCategoryByID(rs.getInt("category_id") + "");
                brand = brandDAO.getBrandByID(rs.getInt("brand_id") + "");
                price = rs.getDouble("price");
                quantity = rs.getInt("quantity");
                imageURL = rs.getString("image");
                discount = rs.getInt("discount");
                x = new Product(productID, sellerID, productName, description, category, brand, price, quantity, imageURL, discount);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Product getProductByID(String ID) {
        Product x = null;
        xSql = "select * from [dbo].[Product] where product_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, ID);
            rs = ps.executeQuery();
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();
            if (rs.next()) {
                int productID = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                String description = rs.getString("product_description");
                Category category = categoryDAO.getCategoryByID(rs.getInt("category_id") + "");
                Brand brand = brandDAO.getBrandByID(rs.getInt("brand_id") + "");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String imageURL = rs.getString("image");
                int discount = rs.getInt("discount");
                x = new Product(productID, productName, description, category, brand, price, quantity, imageURL, discount);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public List<Product> getProductsByCategory(String categoryID) {
        List<Product> t = new ArrayList<>();
        xSql = "select * from [dbo].[Product] where category_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, categoryID);
            rs = ps.executeQuery();
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();

            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                String description = rs.getString("product_description");
                Category category = categoryDAO.getCategoryByID(rs.getInt("category_id") + "");
                Brand brand = brandDAO.getBrandByID(rs.getInt("brand_id") + "");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String imageURL = rs.getString("image");
                int discount = rs.getInt("discount");
                Product x = new Product(productID, productName, description, category, brand, price, quantity, imageURL, discount);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Product> getProductsByBrand(String brandID) {
        List<Product> t = new ArrayList<>();
        xSql = "select * from [dbo].[Product] where brand_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, brandID);
            rs = ps.executeQuery();
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();
            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                String description = rs.getString("product_description");
                Category category = categoryDAO.getCategoryByID(rs.getInt("category_id") + "");
                Brand brand = brandDAO.getBrandByID(rs.getInt("brand_id") + "");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String imageURL = rs.getString("image");
                int discount = rs.getInt("discount");
                Product x = new Product(productID, productName, description, category, brand, price, quantity, imageURL, discount);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Product> getProductsByName(String keyword) {
        List<Product> t = new ArrayList<>();
        xSql = "select * from [dbo].[Product] where product_name like ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();
            while (rs.next()) {
                int productID = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                String description = rs.getString("product_description");
                Category category = categoryDAO.getCategoryByID(rs.getInt("category_id") + "");
                Brand brand = brandDAO.getBrandByID(rs.getInt("brand_id") + "");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String imageURL = rs.getString("image");
                int discount = rs.getInt("discount");
                Product x = new Product(productID, productName, description, category, brand, price, quantity, imageURL, discount);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public void insert(Product x) {
        xSql = "INSERT INTO [dbo].[Product]([seller_id], [product_name],[product_description],[category_id],[brand_id],[price],[quantity],[image],[discount])\n"
                + "     VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, x.getSellerID());
            ps.setString(2, x.getProductName());
            ps.setString(3, x.getDescription());
            ps.setInt(4, x.getCategory().getCategoryID());
            ps.setInt(5, x.getBrand().getBrandID());
            ps.setDouble(6, x.getPrice());
            ps.setInt(7, x.getQuantity());
            ps.setString(8, x.getImageURL());
            ps.setInt(9, x.getDiscount());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByID(String ID) {
        CartDAO c = new CartDAO();
        c.removeByProductID(ID);
        RatingDAO r = new RatingDAO();
        r.removeByProductID(ID);
        OrderItemDAO o = new OrderItemDAO();
        o.removeByProductID(ID);
        xSql = "delete from Product where [product_id]=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, ID);
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Product x) {
        xSql = "UPDATE [dbo].[Product]\n"
                + "   SET [product_name] = ?,[product_description] = ?,[category_id] = ?,[brand_id] = ?\n"
                + "      ,[price] = ?,[quantity] = ?,[image] = ?,[discount] = ?\n"
                + " WHERE [product_id] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getProductName());
            ps.setString(2, x.getDescription());
            ps.setInt(3, x.getCategory().getCategoryID());
            ps.setInt(4, x.getBrand().getBrandID());
            ps.setDouble(5, x.getPrice());
            ps.setInt(6, x.getQuantity());
            ps.setString(7, x.getImageURL());
            ps.setInt(8, x.getDiscount());
            ps.setInt(9, x.getProductID());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // Handle exception appropriately
            System.out.println(e);
        }
    }

    public List<Product> getLatest3Products() {
        List<Product> t = new ArrayList<>();
        xSql = "SELECT TOP 3 * FROM [dbo].[Product] ORDER BY product_id DESC";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int productID;
            String productName;
            String description;
            Category category;
            Brand brand;
            double price;
            int quantity;
            String imageURL;
            int discount;
            Product x;
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();
            while (rs.next()) {
                productID = rs.getInt("product_id");
                productName = rs.getString("product_name");
                description = rs.getString("product_description");
                category = categoryDAO.getCategoryByID(rs.getInt("category_id") + "");
                brand = brandDAO.getBrandByID(rs.getInt("brand_id") + "");
                price = rs.getDouble("price");
                quantity = rs.getInt("quantity");
                imageURL = rs.getString("image");
                discount = rs.getInt("discount");
                x = new Product(productID, productName, description, category, brand, price, quantity, imageURL, discount);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Product> getNLatestProducts(int n) {
        List<Product> t = new ArrayList<>();
        xSql = "SELECT TOP " + n + " * FROM [dbo].[Product] ORDER BY product_id DESC";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int productID;
            String productName;
            String description;
            Category category;
            Brand brand;
            double price;
            int quantity;
            String imageURL;
            int discount;
            Product x;
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();
            while (rs.next()) {
                productID = rs.getInt("product_id");
                productName = rs.getString("product_name");
                description = rs.getString("product_description");
                category = categoryDAO.getCategoryByID(rs.getInt("category_id") + "");
                brand = brandDAO.getBrandByID(rs.getInt("brand_id") + "");
                price = rs.getDouble("price");
                quantity = rs.getInt("quantity");
                imageURL = rs.getString("image");
                discount = rs.getInt("discount");
                x = new Product(productID, productName, description, category, brand, price, quantity, imageURL, discount);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public int getRecordNum(String scope) {
        Product x = null;
        int rowCount = 1;
        xSql = "select COUNT(*) from [dbo].[Product]";

        if (scope.trim().isEmpty() == false) {
            xSql += " where " + scope;
        }

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    public int getPageNum() {
        Product x = null;
        int rowCount = 1;
        xSql = "select COUNT(*) from [dbo].[Product]";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    public int getPageNumBySeller(int sellerID) {
        Product x = null;
        int rowCount = 1;
        xSql = "select COUNT(*) from [dbo].[Product] where [seller_id] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, sellerID);
            rs = ps.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

//    public static void main(String[] args) {
//        ProductDAO dao = new ProductDAO();
//        CategoryDAO ctdao = new CategoryDAO();
//
//        List<Product> temp = dao.getProductsByPageBySeller(0, 9, 1);
////        for (Product p : temp) {
////            System.out.println(p.getProductName());
////        }
//
//        System.out.println(dao.getPageNumBySeller(2));
//        BrandDAO bdao = new BrandDAO();
////    System.out.println(dao.getProductByID("2").getProductName());
////    ctdao.insert(new Category(0, "t"));
////    bdao.insert(new Brand(0, "nn"));
//    }

    public List<Product> getProductsByPage(int offSetPage, int numberOfPage) {
        List<Product> t = new ArrayList<>();
        xSql = "SELECT *  FROM [dbo].[Product] ORDER BY (SELECT NULL) \n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT ? ROWS ONLY;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, offSetPage);
            ps.setInt(2, numberOfPage);
            rs = ps.executeQuery();
            int productID;
            String productName;
            String description;
            Category category;
            Brand brand;
            double price;
            int quantity;
            String imageURL;
            int discount;
            Product x;
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();
            while (rs.next()) {
                productID = rs.getInt("product_id");
                productName = rs.getString("product_name");
                description = rs.getString("product_description");
                category = categoryDAO.getCategoryByID(rs.getInt("category_id") + "");
                brand = brandDAO.getBrandByID(rs.getInt("brand_id") + "");
                price = rs.getDouble("price");
                quantity = rs.getInt("quantity");
                imageURL = rs.getString("image");
                discount = rs.getInt("discount");
                x = new Product(productID, productName, description, category, brand, price, quantity, imageURL, discount);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Product> getProductsByPageBySeller(int offSetPage, int numberOfPage, int sellerID) {
        List<Product> t = new ArrayList<>();
        xSql = "SELECT * FROM (\n"
                + "    SELECT *\n"
                + "    FROM [dbo].[Product]\n"
                + "    WHERE [seller_id] = ?\n"
                + ") AS Products\n"
                + "ORDER BY (SELECT NULL)\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, sellerID);
            ps.setInt(2, offSetPage);
            ps.setInt(3, numberOfPage);
            rs = ps.executeQuery();
            int productID;
            String productName;
            String description;
            Category category;
            Brand brand;
            double price;
            int quantity;
            String imageURL;
            int discount;
            Product x;
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();
            while (rs.next()) {
                productID = rs.getInt("product_id");
                sellerID = rs.getInt("seller_id");
                productName = rs.getString("product_name");
                description = rs.getString("product_description");
                category = categoryDAO.getCategoryByID(rs.getInt("category_id") + "");
                brand = brandDAO.getBrandByID(rs.getInt("brand_id") + "");
                price = rs.getDouble("price");
                quantity = rs.getInt("quantity");
                imageURL = rs.getString("image");
                discount = rs.getInt("discount");
                x = new Product(productID, sellerID, productName, description, category, brand, price, quantity, imageURL, discount);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Product> getProductsByPage(int offSetPage, int numberOfPage, String query, String kindOfSort, String order) {
        List<Product> t = new ArrayList<>();
        xSql = query + " ORDER BY [" + kindOfSort + "] " + order + "\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT ? ROWS ONLY;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, offSetPage);
            ps.setInt(2, numberOfPage);
            rs = ps.executeQuery();
            int productID;
            String productName;
            String description;
            Category category;
            Brand brand;
            double price;
            int quantity;
            String imageURL;
            int discount;
            Product x;
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();
            while (rs.next()) {
                productID = rs.getInt("product_id");
                productName = rs.getString("product_name");
                description = rs.getString("product_description");
                category = categoryDAO.getCategoryByID(rs.getInt("category_id") + "");
                brand = brandDAO.getBrandByID(rs.getInt("brand_id") + "");
                price = rs.getDouble("price");
                quantity = rs.getInt("quantity");
                imageURL = rs.getString("image");
                discount = rs.getInt("discount");
                x = new Product(productID, productName, description, category, brand, price, quantity, imageURL, discount);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Product> getProductsByPageSeller(int offSetPage, int numberOfPage) {
        List<Product> t = new ArrayList<>();
        xSql = "SELECT *  FROM [dbo].[Product] ORDER BY (SELECT NULL) \n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT ? ROWS ONLY;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, offSetPage);
            ps.setInt(2, numberOfPage);
            rs = ps.executeQuery();
            int productID;
            String productName;
            String description;
            Category category;
            Brand brand;
            double price;
            int quantity;
            String imageURL;
            int discount;
            Product x;
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDAO = new BrandDAO();
            while (rs.next()) {
                productID = rs.getInt("product_id");
                productName = rs.getString("product_name");
                description = rs.getString("product_description");
                category = categoryDAO.getCategoryByID(rs.getInt("category_id") + "");
                brand = brandDAO.getBrandByID(rs.getInt("brand_id") + "");
                price = rs.getDouble("price");
                quantity = rs.getInt("quantity");
                imageURL = rs.getString("image");
                discount = rs.getInt("discount");
                x = new Product(productID, productName, description, category, brand, price, quantity, imageURL, discount);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public String getProductInformation(String id, String proper) {
        xSql = "select " + proper + " from [dbo].[Product] where product_id = '" + id + "'";
        String result = "";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString(proper);

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String[] getColNames(String xTable) {
        List<String> columnNames = new ArrayList<>();
        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?";
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, xTable);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String columnName = rs.getString("COLUMN_NAME");
                    columnNames.add(columnName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnNames.toArray(new String[0]);
    }

    public String[] getRowNames(String xColumns, String xTable) {
        xSql = "SELECT DISTINCT " + xColumns + " from [dbo].[" + xTable +"]";
        List<String> rowNames = new ArrayList<>();
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String rowName = rs.getString(xColumns);
                rowNames.add(rowName);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowNames.toArray(new String[0]);
    }
    
    public String[] getSellerId() {
        xSql = "SELECT DISTINCT id from [dbo].[User] where isSeller = 1";
        List<String> rowNames = new ArrayList<>();
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String rowName = rs.getString("id");
                rowNames.add(rowName);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowNames.toArray(new String[0]);
    }
    
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product>  list = dao.getProducts();
//        for (Product product : list) {
//            System.out.println(product.getProductName() + " " + product.getQuantity());
//        }
//         System.out.println(dao.getPageNumBySeller(1));
    }
}

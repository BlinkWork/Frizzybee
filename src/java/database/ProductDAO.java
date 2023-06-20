package database;

import java.util.*;
import java.sql.*;
import model.Product;
import java.sql.Date;
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

  public List<Product> getProductsByPage(int startPage, int numberOfPage) {
    List<Product> t = new ArrayList<>();
    xSql = "SELECT *  FROM [dbo].[Product] OFFSET ? ROWS\n"
            + "FETCH NEXT ? ROWS ONLY";
    try {
      ps = con.prepareStatement(xSql);
      ps.setInt(1, startPage);
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
          x = new Product(productID, productName, description, category, brand, price, quantity, imageURL, discount);
        }
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

      if (rs.next()) {
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
      if (rs.next()) {
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
    xSql = "select * from [dbo].[Product] where name like ?";
    try {
      ps = con.prepareStatement(xSql);
      ps.setString(1, "%" + keyword + "%");
      rs = ps.executeQuery();
      CategoryDAO categoryDAO = new CategoryDAO();
      BrandDAO brandDAO = new BrandDAO();
      if (rs.next()) {
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
      }
      rs.close();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return (t);
  }

  public void insert(Product x) {
    xSql = "INSERT INTO [dbo].[Product]([product_name],[product_description],[category_id],[brand_id],[price],[quantity],[image],[discount])\n"
            + "     VALUES (?,?,?,?,?,?,?,?)";
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
      ps.executeUpdate();
      ps.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteByID(String ID) {
    xSql = "delete from Product where [ProductID]=?";
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
            + "      ,[price] = ?,[quantity] = ?,[image] = ?,[status] = ?\n"
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

  public static void main(String[] args) {
    ProductDAO dao = new ProductDAO();
    Category cate = new Category(1, "ao");
    Brand brand = new Brand(1, "hm");
    dao.insert(new Product(1, "Menkirt", "dep", cate , brand , 75, 20, "ch co", 10));
    dao.insert(new Product(2, "Mqwdenirt", "desp", cate , brand , 75, 20, "ch co", 10));
    dao.insert(new Product(3, "Meqewqt", "deap", cate , brand , 75, 20, "ch co", 10));
    dao.insert(new Product(4, "sadqwnirt", "daep", cate , brand , 75, 20, "ch co", 10));
    dao.insert(new Product(5, "Mwwewqt", "deep", cate , brand , 75, 20, "ch co", 10));
    dao.insert(new Product(6, "Mqqweqwt", "qdep", cate , brand , 75, 20, "ch co", 10));
    dao.insert(new Product(7, "Meqwert", "deap", cate , brand , 75, 20, "ch co", 10));
    dao.insert(new Product(8, "Mqweqweqrt", "dsep", cate , brand , 75, 20, "ch co", 10));
    dao.insert(new Product(9, "Mqweqqwekirt", "daep", cate , brand , 75, 20, "ch co", 10));
    dao.insert(new Product(10, "Mqweqwert", "desp", cate , brand , 75, 20, "ch co", 10));
    dao.insert(new Product(11, "Mqweirt", "deap", cate , brand , 75, 20, "ch co", 10));

  }

}

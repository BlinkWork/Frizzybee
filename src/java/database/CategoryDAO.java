package database;

import java.util.*;
import java.sql.*;
import model.Category;
import model.Product;

public class CategoryDAO extends MyDAO {

    public List<Category> getCategorys() {
        List<Category> t = new ArrayList<>();
        xSql = "SELECT *  FROM [dbo].[Category]";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int categoryID;
            String categoryName;
            Category x;
            while (rs.next()) {
                categoryID = rs.getInt("category_id");
                categoryName = rs.getString("category_name");

                x = new Category(categoryID, categoryName);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Category getCategoryByID(String ID) {
        xSql = "select * from [dbo].[Category] where category_id = ?";
        Category x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, ID);
            rs = ps.executeQuery();

            if (rs.next()) {
                int categoryID;
                String categoryName;
                categoryID = rs.getInt("category_id");
                categoryName = rs.getString("category_name");

                x = new Category(categoryID, categoryName);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public Category getCategoryByName(String name) {
        xSql = "select * from [dbo].[Category] where category_name = ?";
        Category x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, name);
            rs = ps.executeQuery();

            if (rs.next()) {
                int categoryID;
                String categoryName;
                categoryID = rs.getInt("category_id");
                categoryName = rs.getString("category_name");

                x = new Category(categoryID, categoryName);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public void insert(Category x) {
        xSql = "INSERT INTO [dbo].[Category] ([category_name]) VALUES (?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getCategoryName());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Category x) {
        xSql = "UPDATE [dbo].[Category]\n"
                + "   SET [category_name] = ? WHERE [category_id] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getCategoryName());
            ps.setInt(2, x.getCategoryID());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public int countProduct(String id) {
        xSql = "select count(*) as NumberOfProduct from [dbo].[Product] where category_id = ?";
        int numberOfProduct = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                numberOfProduct = rs.getInt("NumberOfProduct");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfProduct;
    }
    
    public void deleteByID(String ID) {
        ProductDAO p = new ProductDAO();
        for (Product product : p.getProductsByCategory(ID)) {
            p.deleteByID(Integer.toString(product.getProductID()));
        }
        xSql = "delete from Category where [category_id] = ?";
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

    public String getCategoryInformation(String id, String proper) {
        xSql = "select " + proper + " from [dbo].[Category] where category_id = '" + id + "'";
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
    
    public static void main(String[] args) {
        CategoryDAO ctdao = new CategoryDAO();
//        ctdao.insert(new Category(0, "t"));
//        List<Category> t = ctdao.getCategorys();
//        for (Category category : t) {
//            System.out.println(category.getCategoryName());
//        }
        System.out.println(ctdao.getCategoryByID("2").getCategoryName());
    }
}

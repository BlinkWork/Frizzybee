package database;

import java.util.*;
import java.sql.*;
import model.User;
import java.sql.Date;
import model.Category;
import model.Rating;

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

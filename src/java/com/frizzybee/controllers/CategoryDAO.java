package com.frizzybee.controllers;

import java.util.*;
import java.sql.*;
import com.frizzybee.model.User;
import java.sql.Date;
import com.frizzybee.model.Category;

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
                categoryID = rs.getInt("brand_id");
                categoryName = rs.getString("brand_name");

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
        xSql = "select * from Category where id = ?";
        Category x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, ID);
            rs = ps.executeQuery();

            if (rs.next()) {
                int categoryID;
            String categoryName;
            while (rs.next()) {
                categoryID = rs.getInt("brand_id");
                categoryName = rs.getString("brand_name");

                x = new Category(categoryID, categoryName);
            }
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

   
}

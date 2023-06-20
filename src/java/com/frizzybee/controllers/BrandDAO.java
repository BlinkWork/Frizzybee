package com.frizzybee.controllers;

import java.util.*;
import java.sql.*;
import com.frizzybee.model.User;
import java.sql.Date;
import com.frizzybee.model.Brand;

public class BrandDAO extends MyDAO {

    public List<Brand> getBrands() {
        List<Brand> t = new ArrayList<>();
        xSql = "SELECT *  FROM [dbo].[Brand]";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int brandID;
            String brandName;
            Brand x;
            while (rs.next()) {
                brandID = rs.getInt("brand_id");
                brandName = rs.getString("brand_name");

                x = new Brand(brandID, brandName);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Brand getBrandByID(String ID) {
<<<<<<< HEAD:src/java/database/BrandDAO.java
        xSql = "select * from [dbo].[Brand] where id = ?";
=======
        xSql = "select * from \"User\" where id = ?";
>>>>>>> ae6ce6ebea6551d8f7083cc1e9690c83528aaa04:src/java/com/frizzybee/controllers/BrandDAO.java
        Brand x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, ID);
            rs = ps.executeQuery();

            if (rs.next()) {
                int brandID;
            String brandName;
            while (rs.next()) {
                brandID = rs.getInt("brand_id");
                brandName = rs.getString("brand_name");

                x = new Brand(brandID, brandName);
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

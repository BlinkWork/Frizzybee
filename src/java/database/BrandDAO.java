package database;

import java.util.*;
import java.sql.*;
import model.User;
import java.sql.Date;
import model.Brand;

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
        xSql = "select * from [dbo].[Brand] where id = ?";
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

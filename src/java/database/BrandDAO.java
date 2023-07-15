package database;

import java.util.*;
import java.sql.*;
import model.Brand;
import model.Product;

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
        xSql = "select * from [dbo].[Brand] where brand_id = ?";
        Brand x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, ID);
            rs = ps.executeQuery();

            if (rs.next()) {
                int brandID = rs.getInt("brand_id");
                String brandName = rs.getString("brand_name");

                x = new Brand(brandID, brandName);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public Brand getBrandByName(String name) {
        xSql = "select * from [dbo].[Brand] where brand_name = ?";
        Brand x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, name);
            rs = ps.executeQuery();

            if (rs.next()) {
                int brandID = rs.getInt("brand_id");
                String brandName = rs.getString("brand_name");

                x = new Brand(brandID, brandName);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public void insert(Brand x) {
        xSql = "INSERT INTO [dbo].[Brand] ([brand_name]) VALUES(?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getBrandName());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void update(Brand x) {
        xSql = "UPDATE [dbo].[Brand]\n"
                + "   SET [brand_name] = ? WHERE [brand_id] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getBrandName());
            ps.setInt(2, x.getBrandID());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public int countProduct(String id) {
        xSql = "select count(*) as NumberOfProduct from [dbo].[Product] where brand_id = ?";
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
        xSql = "delete from Brand where [brand_id] = ?";
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

    public String getBrandInformation(String id, String proper) {
        xSql = "select " + proper + " from [dbo].[Brand] where brand_id = '" + id + "'";
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
        BrandDAO bdao = new BrandDAO();
        bdao.insert(new Brand(0, "nn"));
    }
}

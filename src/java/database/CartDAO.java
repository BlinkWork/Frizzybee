package database;

import java.util.*;
import java.sql.*;
import model.Cart;
import java.sql.Date;

public class CartDAO extends MyDAO {

    public List<Cart> getCarts() {
        List<Cart> t = new ArrayList<>();
        xSql = "SELECT *  FROM [dbo].[Cart]";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int cart_id = rs.getInt("cart_id");
                int product_id = rs.getInt("product_id");
                int quanity = rs.getInt("quanity");
                int user_id = rs.getInt("user_id");

                Cart x = new Cart(String.valueOf(product_id), quanity, String.valueOf(cart_id), String.valueOf(user_id));
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }


    public void insert(Cart x) {
        xSql = "INSERT INTO [dbo].[Cart] ([product_id] ,[quantity] ,[user_id])\n" +
"     VALUES (?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            int product_id = Integer.parseInt(x.getProduct_id());
            int quantity = x.getQuantity();
            int user_id = Integer.parseInt(x.getUser_id());
            ps.setInt(1, product_id);
            ps.setInt(2, quantity);
            ps.setInt(3, user_id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeByProductID(String ID) {
        xSql = "delete from [dbo].[Cart] where [product_id]=?";
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

    public void deleteByCartID(String ID) {
        xSql = "delete from [dbo].[Cart] where [cart_id]=?";
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
    
    public void update(Cart x) {
        xSql = "UPDATE [dbo].[Cart]\n" +
"   SET [product_id] = ? ,[quantity] = ? ,[user_id] = ?\n" +
" WHERE [cart_id] = ?";
        try {
            ps = con.prepareStatement(xSql);
            int product_id = Integer.parseInt(x.getProduct_id());
            int quantity = x.getQuantity();
            int user_id = Integer.parseInt(x.getUser_id());
            int cart_id = Integer.parseInt(x.getCart_id());
            ps.setInt(1, product_id);
            ps.setInt(2, quantity);
            ps.setInt(3, user_id);
            ps.setInt(4, cart_id);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // Handle exception appropriately
            System.out.println(e);
        }
    }

    
}

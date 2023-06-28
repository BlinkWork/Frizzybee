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
                int quanity = rs.getInt("quantity");
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

    public List<Cart> getCarts(int user_id) {
        List<Cart> carts = new ArrayList<>();
        xSql = "SELECT * FROM [dbo].[Cart] WHERE user_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int cart_id = rs.getInt("cart_id");
                int product_id = rs.getInt("product_id");
                int quanity = rs.getInt("quantity");

                Cart cart = new Cart(String.valueOf(product_id), quanity, String.valueOf(cart_id), String.valueOf(user_id));
                carts.add(cart);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carts;
    }

    public void deleteAllCarts() {
        xSql = "DELETE FROM [dbo].[Cart]";
        try {
            ps = con.prepareStatement(xSql);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllCartsByUserId(int userId) {
        xSql = "delete from [dbo].[Cart] where [user_id]=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(Cart x) {
        xSql = "INSERT INTO [dbo].[Cart] ([product_id] ,[quantity] ,[user_id])\n"
                + "     VALUES (?,?,?)";
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
        xSql = "UPDATE [dbo].[Cart]\n"
                + "   SET [product_id] = ? ,[quantity] = ? ,[user_id] = ?\n"
                + " WHERE [cart_id] = ?";
        try {
//            System.out.println(x.getCart_id() + "-" + x.getUser_id() + "-" + x.getProduct_id() + "-" + x.getQuantity());

            ps = con.prepareStatement(xSql);
            int product_id = Integer.parseInt(x.getProduct_id());
            int quantity = x.getQuantity();
            int user_id = 1;
            if (x.getUser_id().trim().isEmpty() == false) {
                user_id = Integer.parseInt(x.getUser_id());
            }
            int cart_id = 1;
            if (x.getUser_id().trim().isEmpty() == false) {
                cart_id = Integer.parseInt(x.getCart_id());
            }
//            System.out.println(x.getProduct_id() + "-" + quantity + "-" + cart_id + "-" + user_id);

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

    public static void main(String[] args) {

        CartDAO cdao = new CartDAO();
//        cdao.insert(new Cart("10", 5, "1", "1"));
//        cdao.insert(new Cart("12", 5, "1", "1"));
//        cdao.insert(new Cart("14", 5, "1", "1"));
//        cdao.insert(new Cart("18", 5, "1", "1"));
//        cdao.insert(new Cart("20", 5, "1", "1"));
//        cdao.insert(new Cart("13", 5, "1", "1"));
//        cdao.insert(new Cart("5", 5, "1", "1"));
        List<Cart> temp = cdao.getCarts();
        for (Cart cart : temp) {
            System.out.println(cart.getCart_id() + "-" + cart.getUser_id() + "-" + cart.getProduct_id() + "-" + cart.getQuantity());
        }
//        cdao.deleteAllCarts();
//        cdao.update(new Cart("19", 3, "1", "1"));
    }
}

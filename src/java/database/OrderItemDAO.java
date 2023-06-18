package database;

import java.util.*;
import java.sql.*;
import model.OrderItem;
import java.sql.Date;
import model.Product;

public class OrderItemDAO extends MyDAO {

    public List<OrderItem> getOrderItems() {
        List<OrderItem> t = new ArrayList<>();
        xSql = "SELECT *  FROM [dbo].[OrderItem]";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int orderItemID;
            int orderID;
            Product product;
            int quantity;
            double price;
            OrderItem x;
            ProductDAO productDAO = new ProductDAO();
            while (rs.next()) {
                orderItemID = rs.getInt("OrderItemID");
                orderID = rs.getInt("OrderID");
                product = productDAO.getProductByID(rs.getInt("product_id") + "");
                quantity = rs.getInt("Quantity");
                price = rs.getDouble("Price");

                x = new OrderItem(orderItemID, orderID, product, quantity, price);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public OrderItem getOrderItemByID(String ID) {
        xSql = "select * from OrderItem where OrderItemID = ?";
        OrderItem x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, ID);
            rs = ps.executeQuery();
            ProductDAO productDAO = new ProductDAO();
            if (rs.next()) {
                while (rs.next()) {
                    int orderItemID = rs.getInt("OrderItemID");
                    int orderID = rs.getInt("OrderID");
                    Product product = productDAO.getProductByID(rs.getInt("product_id") + "");
                    int quantity = rs.getInt("Quantity");
                    double price = rs.getDouble("Price");

                    x = new OrderItem(orderItemID, orderID, product, quantity, price);
                }
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public List<OrderItem> getOrderItemsByOrderID(String orderId) {
        List<OrderItem> t = new ArrayList<>();
        xSql = "select * from OrderItem where OrderID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, orderId);
            rs = ps.executeQuery();
            ProductDAO productDAO = new ProductDAO();
            if (rs.next()) {
                while (rs.next()) {
                    int orderItemID = rs.getInt("OrderItemID");
                    int orderID = rs.getInt("OrderID");
                    Product product = productDAO.getProductByID(rs.getInt("product_id") + "");
                    int quantity = rs.getInt("Quantity");
                    double price = rs.getDouble("Price");
                    OrderItem x = new OrderItem(orderItemID, orderID, product, quantity, price);
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

    public void insert(OrderItem x) {
        xSql = "INSERT INTO [dbo].[OrderItem]([OrderID] ,[product_id],[Quantity],[Price])\n"
                + "     VALUES(?,?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, x.getOrderID());
            ps.setInt(2, x.getProduct().getProductID());
            ps.setInt(3, x.getQuantity());
            ps.setDouble(4, x.getPrice());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByID(String ID) {
        xSql = "delete from OrderItem where [OrderItemID]=?";
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

    public void update(OrderItem x) {
        xSql = "UPDATE [dbo].[OrderItem]\n"
                + "   SET [OrderID] = ?,[product_id] = ?,[Quantity] = ?,[Price] = ?\n"
                + " WHERE [OrderItemID] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, x.getOrderID());
            ps.setInt(2, x.getProduct().getProductID());
            ps.setInt(3, x.getQuantity());
            ps.setDouble(4, x.getPrice());
            ps.setInt(5, x.getOrderItemID());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // Handle exception appropriately
            System.out.println(e);
        }
    }

}

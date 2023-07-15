package database;

import java.util.*;
import java.sql.*;
import model.OrderItem;
import model.Order;
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
        xSql = "select * from [dbo].[OrderItem] where OrderItemID = ?";
        OrderItem x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, ID);
            rs = ps.executeQuery();
            ProductDAO productDAO = new ProductDAO();
            if (rs.next()) {
                int orderItemID = rs.getInt("OrderItemID");
                int orderID = rs.getInt("OrderID");
                Product product = productDAO.getProductByID(rs.getInt("product_id") + "");
                int quantity = rs.getInt("Quantity");
                double price = rs.getDouble("Price");
                
                x = new OrderItem(orderItemID, orderID, product, quantity, price);
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
        xSql = "select * from [dbo].[OrderItem] where OrderID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, orderId);
            rs = ps.executeQuery();
            ProductDAO productDAO = new ProductDAO();
            
            while (rs.next()) {
                int orderItemID = rs.getInt("OrderItemID");
                int orderID = rs.getInt("OrderID");
                Product product = productDAO.getProductByID(rs.getInt("product_id") + "");
                int quantity = rs.getInt("Quantity");
                double price = rs.getDouble("Price");
                OrderItem x = new OrderItem(orderItemID, orderID, product, quantity, price);
                t.add(x);
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
                + "     VALUES(?,?,?,?)";
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
        xSql = "delete from [dbo].[OrderItem] where [OrderItemID]=?";
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
    
    public void removeByProductID(String ID) {
        xSql = "delete from [dbo].[OrderItem] where [product_id]=?";
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
    
    public static void main(String[] args) {
        OrderItemDAO dao = new OrderItemDAO();
        CategoryDAO ctdao = new CategoryDAO();
        ProductDAO pdao = new ProductDAO();
        BrandDAO bdao = new BrandDAO();
        
        UserDAO userDAO = new UserDAO();
        OrderDAO odao = new OrderDAO();
//        odao.insert(new Order(0, userDAO.getUserByID("1"), Date.valueOf("2023-06-27"), "bbbb", "chuyen khoan", "unconfimred", 100));
//        odao.insert(new Order(0, userDAO.getUserByID("2"), Date.valueOf("2023-06-27"), "bbbb", "chuyen khoan", "unconfimred", 100));
//        odao.insert(new Order(0, userDAO.getUserByID("2"), Date.valueOf("2023-06-27"), "bbbb", "chuyen khoan", "unconfimred", 100));
//        odao.insert(new Order(0, userDAO.getUserByID("2"), Date.valueOf("2023-06-27"), "bbbb", "chuyen khoan", "unconfimred", 100));
//        odao.insert(new Order(0, userDAO.getUserByID("1"), Date.valueOf("2023-06-27"), "bbbb", "chuyen khoan", "unconfimred", 100));
////        
//        dao.insert(new OrderItem(0, 1, pdao.getProductByID("7"), 1, 1000));
//        dao.insert(new OrderItem(0, 2, pdao.getProductByID("8"), 7, 1000));
//        dao.insert(new OrderItem(0, 6, pdao.getProductByID("9"), 9, 1000));
//        dao.insert(new OrderItem(0, 7, pdao.getProductByID("4"), 1, 1000));
//        dao.insert(new OrderItem(0, 8, pdao.getProductByID("5"), 14, 1000));

        List<Order> t = odao.getOrders();
        for (Order order : t) {
            List<OrderItem> listOrderItem = dao.getOrderItemsByOrderID(order.getOrderID() + "");
            for (OrderItem orderItem : listOrderItem) {
//                Product product = pdao.getProductByID(orderItem.getProduct().getProductID() + "");
                dao.deleteByID(String.valueOf(orderItem.getOrderID()));
//                System.out.println(orderItem.getOrderID());
            }
        }
        
    }
}

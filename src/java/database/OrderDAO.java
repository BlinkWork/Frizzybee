package database;

import java.util.*;
import java.sql.*;
import model.Order;
import java.sql.Date;
import model.Product;
import model.User;

public class OrderDAO extends MyDAO {

    public List<Order> getOrders() {
        List<Order> t = new ArrayList<>();
        xSql = "SELECT *  FROM [dbo].[Order]";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int orderID;
            User user;
            Date orderDate;
            String address;
            String paymentMethod;
            String status;
            double totalPrice;
            Order x;
            UserDAO userDAO = new UserDAO();
            while (rs.next()) {
                orderID = rs.getInt("OrderID");
                user = userDAO.getUserByID(rs.getInt("user_id") + "");
                orderDate = rs.getDate("OrderDate");
                address = rs.getString("address");
                paymentMethod = rs.getString("payment_method");
                status = rs.getString("Status");
                totalPrice = rs.getDouble("total_price");

                x = new Order(orderID, user, orderDate, address, paymentMethod, status, totalPrice);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Order getOrderByID(String ID) {
        xSql = "select * from [dbo].[Order] where OrderID = ?";
        Order x = null;
        UserDAO userDAO = new UserDAO();
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                int orderID = rs.getInt("OrderID");
                User user = userDAO.getUserByID(rs.getInt("user_id") + "");
                Date orderDate = rs.getDate("OrderDate");
                String address = rs.getString("address");
                String paymentMethod = rs.getString("payment_method");
                String status = rs.getString("Status");
                double totalPrice = rs.getDouble("total_price");

                x = new Order(orderID, user, orderDate, address, paymentMethod, status, totalPrice);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public List<Order> getOrdersByUserID(String userId) {
        List<Order> t = new ArrayList<>();
        xSql = "select * from [dbo].[Order] where user_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            UserDAO userDAO = new UserDAO();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                User user = userDAO.getUserByID(rs.getInt("user_id") + "");
                Date orderDate = rs.getDate("OrderDate");
                String address = rs.getString("address");
                String paymentMethod = rs.getString("payment_method");
                String status = rs.getString("Status");
                double totalPrice = rs.getDouble("total_price");

                Order x = new Order(orderID, user, orderDate, address, paymentMethod, status, totalPrice);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public int insert(Order x) {
        xSql = "INSERT INTO [dbo].[Order]([user_id],[OrderDate],[address],[payment_method],[Status] ,[total_price])\n"
                + "     VALUES(?,?,?,?,?,?)";
        int order_id = 0;
        try {
            ps = con.prepareStatement(xSql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, x.getUser().getId());
            ps.setDate(2, x.getOrderDate());
            ps.setString(3, x.getAddress());
            ps.setString(4, x.getPaymentMethod());
            ps.setString(5, x.getStatus());
            ps.setDouble(6, x.getTotalPrice());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                order_id = rs.getInt(1);
            }

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order_id;
    }

    public void deleteByID(String ID) {
        xSql = "delete from [dbo].[Order] where [OrderID]=?";
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

    public void update(Order x) {
        xSql = "UPDATE [dbo].[Order]\n"
                + "   SET [user_id] = ?,[OrderDate] = ?,[address] = ?,[payment_method] = ?,[Status] = ?,[total_price] = ?\n"
                + " WHERE [OrderID] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, x.getUser().getId());
            ps.setDate(2, x.getOrderDate());
            ps.setString(3, x.getAddress());
            ps.setString(4, x.getPaymentMethod());
            ps.setString(5, x.getStatus());
            ps.setDouble(6, x.getTotalPrice());
            ps.setInt(7, x.getOrderID());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // Handle exception appropriately
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
//        dao.insert(new Order(0, new User(1, "Lê Minh Thang", "cccc", "lethangd", "123456", "M", Date.valueOf("2003-08-05"), "lethangd@gmail.com", "Phu Ly", "admin"), Date.valueOf("2003-08-05"), "aaa", "chuyen khoan", "done", 100));
//        dao.insert(new Order(0, new User(1, "Lê Minh Thang", "cccc", "lethangd", "123456", "M", Date.valueOf("2003-08-05"), "lethangd@gmail.com", "Phu Ly", "admin"), Date.valueOf("2003-08-05"), "aaa", "chuyen khoan", "done", 100));
//        dao.insert(new Order(0, new User(1, "Lê Minh Thang", "cccc", "lethangd", "123456", "M", Date.valueOf("2003-08-05"), "lethangd@gmail.com", "Phu Ly", "admin"), Date.valueOf("2003-08-05"), "aaa", "chuyen khoan", "done", 100));

//        dao.update(new Order(2, new User(1, "Lê Minh Thang", "cccc", "lethangd", "123456", "M", Date.valueOf("2003-08-05"), "lethangd@gmail.com", "Phu Ly", "admin"), Date.valueOf("2003-08-05"), "bbbb", "chuyen khoan", "done", 100));
//        dao.deleteByID("2");
        List<Order> t = dao.getOrders();
        for (Order order : t) {
            System.out.println(order.getOrderID());
        }
    }
}

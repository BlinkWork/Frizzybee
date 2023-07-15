package database;

import java.util.*;
import java.sql.*;
import model.User;
import java.sql.Date;
import model.Product;

public class UserDAO extends MyDAO {
    
    public List<User> getUsers() {
        List<User> t = new ArrayList<>();
        xSql = "select * from [dbo].[User]";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int id;
            String name;
            String avatarURL;
            String username;
            String password;
            String sex;
            Date birthday;
            String email;
            String address;
            String role;
            User x;
            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                avatarURL = rs.getString("avatar");
                username = rs.getString("username");
                password = rs.getString("password");
                sex = rs.getString("sex");
                birthday = rs.getDate("birthday");
                email = rs.getString("email");
                address = rs.getString("address");
                if (rs.getBoolean("isAdmin")) {
                    role = "admin";
                } else if (rs.getBoolean("isSeller")) {
                    role = "seller";
                } else {
                    role = "user";
                }
                
                x = new User(id, name, avatarURL, username, password, sex, birthday, email, address, role);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
    
    public User getUserByID(String ID) {
        xSql = "select * from [dbo].[User] where id = ?";
        User x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, ID);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String avatarURL = rs.getString("avatar");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                Date birthday = rs.getDate("birthday");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String role;
                if (rs.getBoolean("isAdmin")) {
                    role = "admin";
                } else if (rs.getBoolean("isSeller")) {
                    role = "seller";
                } else {
                    role = "user";
                }
                x = new User(id, name, avatarURL, username, password, sex, birthday, email, address, role);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
    public User getUserByUsername(String xUsername) {
        xSql = "select * from [dbo].[User] where username = ?";
        User x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xUsername);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String avatarURL = rs.getString("avatar");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                Date birthday = rs.getDate("birthday");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String role;
                if (rs.getBoolean("isAdmin")) {
                    role = "admin";
                } else if (rs.getBoolean("isSeller")) {
                    role = "seller";
                } else {
                    role = "user";
                }
                x = new User(id, name, avatarURL, username, password, sex, birthday, email, address, role);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
    public List<User> getUsersByName(String keyword) {
        List<User> t = new ArrayList<>();
        xSql = "select * from [dbo].[User] where name like ?";
        User x;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String avatarURL = rs.getString("avatar");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                Date birthday = rs.getDate("birthday");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String role;
                if (rs.getBoolean("isAdmin")) {
                    role = "admin";
                } else if (rs.getBoolean("isSeller")) {
                    role = "seller";
                } else {
                    role = "user";
                }
                x = new User(id, name, avatarURL, username, password, sex, birthday, email, address, role);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
    
    public boolean checkDuplicateUsername(String xUsername) {
        xSql = "SELECT * FROM [dbo].[User] WHERE username = ?";
        boolean result = false;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xUsername);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean loginAccount(String xUsername, String xPassword) {
        xSql = "SELECT * FROM [dbo].[User] WHERE username = ? AND password = ?";
        boolean result = false;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xUsername);
            ps.setString(2, util.DataEncrypt.toSHA1(xPassword));
            rs = ps.executeQuery();
            
            if (rs.next()) {
                result = true;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void insert(User x) {
        xSql = "INSERT INTO [dbo].[User]([name],[avatar],[username],[password],[sex],[birthday],[email],[address],[isSeller],[isAdmin])"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getName());
            ps.setString(2, x.getAvatarURL());
            ps.setString(3, x.getUsername());
            ps.setString(4, x.getPassword());
            ps.setString(5, x.getSex());
            ps.setDate(6, x.getBirthday());
            ps.setString(7, x.getEmail());
            ps.setString(8, x.getAddress());
            switch (x.getRole()) {
                case "admin":
                    ps.setBoolean(9, true);
                    ps.setBoolean(10, true);
                    break;
                case "seller":
                    ps.setBoolean(9, true);
                    ps.setBoolean(10, false);
                    break;
                default:
                    ps.setBoolean(9, false);
                    ps.setBoolean(10, false);
                    break;
            }
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(String ID) {
        CartDAO c = new CartDAO();
        c.deleteAllCartsByUserId(Integer.parseInt(ID));
        RatingDAO r = new RatingDAO();
        r.removeByUserID(ID);
        OrderDAO o = new OrderDAO();
        o.deleteByUserID(ID);
        ProductDAO p = new ProductDAO();
        for (Product product : p.getProductsBySeller(Integer.parseInt(ID))) {
            p.deleteByID(Integer.toString(product.getProductID()));
        }
        xSql = "delete from [dbo].[User] where id=?";
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
    
    public void update(User x) {
        xSql = "UPDATE [dbo].[User]\n"
                + "   SET [name] =     ?\n"
                + "      ,[avatar] =   ?\n"
                + "      ,[username] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[sex] =      ?\n"
                + "      ,[birthday] = ?\n"
                + "      ,[email] =    ?\n"
                + "      ,[address] =  ?\n"
                + "      ,[isSeller] = ?\n"
                + "      ,[isAdmin] =  ?\n"
                + " WHERE [id] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getName());
            ps.setString(2, x.getAvatarURL());
            ps.setString(3, x.getUsername());
            ps.setString(4, x.getPassword());
            ps.setString(5, x.getSex());
            ps.setDate(6, x.getBirthday());
            ps.setString(7, x.getEmail());
            ps.setString(8, x.getAddress());
            switch (x.getRole()) {
                case "admin":
                    ps.setBoolean(9, true);
                    ps.setBoolean(10, true);
                    break;
                case "seller":
                    ps.setBoolean(9, true);
                    ps.setBoolean(10, false);
                    break;
                default:
                    ps.setBoolean(9, false);
                    ps.setBoolean(10, false);
                    break;
            }
            ps.setInt(11, x.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // Handle exception appropriately
            System.out.println(e);
        }
    }
    
    public int countNumberUser() {
        int number = 0;
        xSql = "select count(*) as numberUser from [dbo].[User] where isAdmin = 'FALSE'";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                number = rs.getInt("numberUser");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
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
    
    public String getUserInformation(String username, String proper) {
        xSql = "select " + proper + " from [dbo].[User] where username = '" + username + "'";
        String result = "";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString(proper);
                if (proper.equals("isAdmin") || proper.equals("isSeller")) {
                    result = (result.equals("1") ? "true" : "false");
                }
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
//    dao.insert(new User(1, "Lê Minh Thang", "cccc", "lethangd", "123456", "M", Date.valueOf("2003-08-05"), "lethangd@gmail.com", "Phu Ly", "admin"));

        List<User> temp = dao.getUsers();
        for (User te : temp) {
            System.out.println(te.getId());
            
        }        
        
    }
}

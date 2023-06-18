package database;

import java.util.*;
import java.sql.*;
import model.User;
import java.sql.Date;

public class UserDAO extends MyDAO {

    public List<User> getUsers() {
        List<User> t = new ArrayList<>();
        xSql = "select * from User";
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
        xSql = "select * from User where id = ?";
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

    public List<User> getUsersByName(String keyword) {
        List<User> t = new ArrayList<>();
        xSql = "select * from User where name like ?";
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
    
    public User getUsersByUserName(String keyword) {
        xSql = "SELECT * FROM [User] WHERE username = ?";
        User user = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, keyword);
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
                user = new User(id, name, avatarURL, username, password, sex, birthday, email, address, role);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
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
                    ps.setBoolean(10, true);
                    break;
                default:
                    ps.setBoolean(9, true);
                    ps.setBoolean(10, true);
                    break;
            }
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String ID) {
        xSql = "delete from User where id=?";
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
                    ps.setBoolean(10, true);
                    break;
                default:
                    ps.setBoolean(9, true);
                    ps.setBoolean(10, true);
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

//    public static void main(String[] args) {
//        UserDAO dao = new UserDAO();
//        dao.insert(new User(1, "Lê Minh Thang", "aaa", "lethangd", "123456", "M",Date.valueOf("2003-08-05") , "lethangd@gmail.com", "Phu Ly", "admin"));
//        
//    }
}

package database;

import java.util.*;
import java.sql.*;
import model.Rating;

public class RatingDAO extends MyDAO {

    public List<Rating> getRatings() {
        List<Rating> t = new ArrayList<>();
        xSql = "SELECT *  FROM [dbo].[Rating]";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int ratingID;
            int userID;
            int productID;
            double vote;
            String comment;
            Rating x;
            while (rs.next()) {
                ratingID = rs.getInt("RatingID");
                userID = rs.getInt("user_id");
                productID = rs.getInt("product_id");
                vote = rs.getDouble("vote");
                comment = rs.getString("comment");

                x = new Rating(ratingID, userID, productID, vote, comment);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Rating> getRatingByProductID(String productID) {
        List<Rating> t = new ArrayList<>();
        xSql = "select * from [dbo].[Rating] where product_id = ?";
        Rating x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, productID);
            rs = ps.executeQuery();

            int ratingID;
            int userID;
            int productId;
            double vote;
            String comment;

            while (rs.next()) {
                ratingID = rs.getInt("RatingID");
                userID = rs.getInt("user_id");
                productId = rs.getInt("product_id");
                vote = rs.getDouble("vote");
                comment = rs.getString("comment");

                x = new Rating(ratingID, userID, productId, vote, comment);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public void insert(Rating x) {
        xSql = "INSERT INTO [dbo].[Rating] ([user_id],[product_id],[vote],[comment]) VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, x.getUserID());
            ps.setInt(2, x.getProductID());
            ps.setDouble(3, x.getVote());
            ps.setString(4, x.getComment());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByID(String ID) {
        xSql = "delete from [dbo].[Rating] where [RatingID]=?";
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

    public void update(Rating x) {
        xSql = "UPDATE [dbo].[Rating] SET [user_id] = ?,[product_id] = ?,[vote] = ?,[comment] = ?\n"
                + " WHERE [RatingID] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, x.getUserID());
            ps.setInt(2, x.getProductID());
            ps.setDouble(3, x.getVote());
            ps.setString(4, x.getComment());
            ps.setInt(5, x.getRatingID());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // Handle exception appropriately
            System.out.println(e);
        }
    }
    
    public void removeByProductID(String ID) {
        xSql = "delete from [dbo].[Rating] where [product_id]=?";
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
    
    public void removeByUserID(String ID) {
        xSql = "delete from [dbo].[Rating] where [user_id]=?";
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
        RatingDAO dao = new RatingDAO();
//        dao.insert(new Rating(0, 1, 1, 0, "dat ngu"));
//        dao.insert(new Rating(0, 1, 1, 0, "dat ngu"));
//        dao.insert(new Rating(0, 1, 1, 0, "dat ngu"));
//dao.update(new Rating(5, 1, 1, 0, "dat del ngu"));
//        dao.deleteByID("5");
        List<Rating> t = dao.getRatings();
        for (Rating rating : t) {
            dao.deleteByID(rating.getRatingID()+"");
        }
    }
}

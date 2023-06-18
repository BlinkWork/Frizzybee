package database;

import java.util.*;
import java.sql.*;
import model.Rating;
import java.sql.Date;

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

    public Rating getRatingByProductID(String productID) {
        xSql = "select * from Rating where product_id = ?";
        Rating x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, productID);
            rs = ps.executeQuery();

            if (rs.next()) {
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
            }
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public void insert(Rating x) {
        xSql = "INSERT INTO [dbo].[Rating] ([user_id],[product_id],[vote],[comment]) VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1 , x.getUserID());
            ps.setInt(2 , x.getProductID());
            ps.setDouble(3 , x.getVote());
            ps.setString(4 , x.getComment());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByID(String ID) {
        xSql = "delete from Rating where [RatingID]=?";
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
        xSql = "UPDATE [dbo].[Rating] SET [user_id] = ?,[product_id] = ?,[vote] = ?,[comment] = ?\n" +
                " WHERE [RatingID] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1 , x.getUserID());
            ps.setInt(2 , x.getProductID());
            ps.setDouble(3 , x.getVote());
            ps.setString(4 , x.getComment());
            ps.setInt(5 , x.getRatingID());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // Handle exception appropriately
            System.out.println(e);
        }
    }

}

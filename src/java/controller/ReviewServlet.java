/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import database.RatingDAO;
import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import model.Rating;
import model.User;

/**
 *
 * @author USER
 */
@WebServlet(name = "ReviewServlet", urlPatterns = {"/review"})
public class ReviewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO udao = new UserDAO();
        RatingDAO rdao = new RatingDAO();

        String method = request.getParameter("method");
        if (method.equals("add")) {
            String comment = request.getParameter("comment");
            String rateScore = request.getParameter("rateScore");
            String productId = request.getParameter("productId");
            HttpSession session = request.getSession();
            String userName = (String) session.getAttribute("username");

            String user_id = String.valueOf(udao.getUserByUsername(userName).getId());
            Rating rate = new Rating(1, Integer.parseInt(user_id), Integer.parseInt(productId), Double.parseDouble(rateScore), comment);
            rdao.insert(rate);
        }

        if (method.equals("rate")) {
            String productId = request.getParameter("productId");
            double avgRate = getAvgRating(productId);
            String rateText = "";
            int i = 1;
            for (; i <= avgRate; i++) {
                rateText += "<span><i class='fas fa-star'></i></span>";
            }

            for (; i <= 5; i++) {
                rateText += "<i class='fas fa-star'></i>";
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(rateText);

        }

        if (method.equals("more")) {
            String data = "";
            String productId = request.getParameter("productId");
            String temp = request.getParameter("numberComment");
            int numberComment = Integer.parseInt(temp);
            numberComment += 1;
            List<Rating> listRate = rdao.getRatingByProductID(productId);
            if (listRate.size() == 0 || listRate.size() < numberComment) {
               
                data = "0";
            } else {
                data = "1";
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(data);

        }

        if (method.equals("show")) {
            String data = "";
            String productId = request.getParameter("productId");
            String temp = request.getParameter("numberComment");
            int numberComment = Integer.parseInt(temp);

            List<Rating> listRate = rdao.getRatingByProductID(productId);

            if (listRate.size() > 0) {
                for (int index = listRate.size() - 1; index >= 0; index--) {
                    Rating rating = listRate.get(index);
                    User user = udao.getUserByID(String.valueOf(rating.getUserID()));

                    String rateText = "";
                    double rateScoreTemp = rating.getVote();
                    int i = 1;
                    for (; i <= rateScoreTemp; i++) {
                        rateText += "<span><i class='fas fa-star'></i></span>";
                    }
                    for (; i <= 5; i++) {
                        rateText += "<i class='fas fa-star'></i>";
                    }

                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
                    String formattedDateTime = now.format(formatter);

                    String dataItem = ""
                            + "<li class='review-single'>"
                            + "<img src='" + user.getAvatarURL() + "' alt='avatar'>"
                            + "<div class='review-info'>"
                            + "<h5>" + user.getName() + "</h5>"
                            + "<small> " + formattedDateTime + " </small>"
                            + "</div>"
                            + "<div class='ratting'>"
                            + rateText
                            + "</div>"
                            + "<div class='revie-con'>"
                            + "<p>" + rating.getComment() + "</p>"
                            + "</div>"
                            + "</li>";
                    data += dataItem;
                    numberComment--;
                    if (numberComment == 0) {
                        break;
                    }
                }
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(data);
            }
        }
    }

    public double getAvgRating(String productId) {
        UserDAO udao = new UserDAO();
        RatingDAO rdao = new RatingDAO();
        List<Rating> listRate = rdao.getRatingByProductID(productId);
        double avgRate = 0;
        if (listRate.size() > 0) {
            for (Rating rating : listRate) {
                avgRate += rating.getVote();
            }
            avgRate /= listRate.size();
        } else {
            avgRate = 5;
        }
        return avgRate;
    }

}

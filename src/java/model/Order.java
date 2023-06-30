/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Order {
    private int orderID ;
    private User user ;
    private Date orderDate;
    private Date receivedDate;
    private String address;
    private String paymentMethod;
    private String status;
    private double totalPrice;

    public Order() {
    }

    public Order(int orderID, User user, Date orderDate, String address, String paymentMethod, String status, double totalPrice) {
        this.orderID = orderID;
        this.user = user;
        this.orderDate = orderDate;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Order(int orderID, User user, Date orderDate, Date receivedDate, String address, String paymentMethod, String status, double totalPrice) {
        this.orderID = orderID;
        this.user = user;
        this.orderDate = orderDate;
        this.receivedDate = receivedDate;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
            
}

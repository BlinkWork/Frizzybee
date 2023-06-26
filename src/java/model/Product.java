/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Product {
    private int productID;
    private int sellerID;
    private String productName;
    private String description;
    private Category category;
    private Brand brand ;
    private double price ;
    private int quantity ;
    private String imageURL;
    private int discount;

    public Product() {
    }

    public Product(int productID, String productName, String description, Category category, Brand brand, double price, int quantity, String imageURL, int discount) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.discount = discount;
    }

    public Product(int productID, int sellerID, String productName, String description, Category category, Brand brand, double price, int quantity, String imageURL, int discount) {
        this.productID = productID;
        this.sellerID = sellerID;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.discount = discount;
    }

    public Product(String productName,int sellerID,  String description, Category category, Brand brand, double price, int quantity, String imageURL, int discount) {
        this.sellerID = sellerID;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.discount = discount;
    }
    
    
    
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    
}

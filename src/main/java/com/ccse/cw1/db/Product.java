package com.ccse.cw1.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int category; //1: electric guitar 2: acoustic guitar
    private String imagelink;

    public Product() {
        
    }

    // Getters and setters for the fields
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getCategory() {
        return category;
    }
    public void setCategory(int category) {
        this.category = category;
    }
    public String getImagelink() {
        return imagelink;
    }
    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }
    public Long getId() {
        return id;
    }


}
    

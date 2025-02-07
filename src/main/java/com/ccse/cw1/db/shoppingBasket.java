package com.ccse.cw1.db;


import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class shoppingBasket {
    //id of the basket, the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    
    //lists of product ids and quantities
    @ElementCollection
    private List<Long> productIDsList = new ArrayList<>();
    @ElementCollection
    private List<Integer> quantities = new ArrayList<>();

    public shoppingBasket()
    {

    }
    
    // Getters and setters for the fields
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getUserId()
    {
        return userId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
    public List<Long> getProductID()
    {
        return productIDsList;
    }
    public void setProductID(List<Long> productID)
    {
        this.productIDsList = productID;
    }
    public List<Integer> getQuantities()
    {
        return quantities;
    }
    public void setQuantities(List<Integer> quantities)
    {
        this.quantities = quantities;
    }


    public void incrementQuantity(Long productId) {
        int index = productIDsList.indexOf(productId);
        if (index != -1) {
            quantities.set(index, quantities.get(index) + 1);
        }
    }

    public void decrementQuantity(Long productId) {
        int index = productIDsList.indexOf(productId);
        if (index != -1) {
            int newQuantity = quantities.get(index) - 1;
            if (newQuantity > 0) {
                quantities.set(index, newQuantity);
            } else {
                productIDsList.remove(index);
                quantities.remove(index);
            }
        }
    }



    
}

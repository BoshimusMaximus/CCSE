package com.ccse.cw1.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Annotate the class as a Spring service component
@Service
public class basketService 
{
	@Autowired
    private basketRepository basketRepository;

    // Method to get the shopping basket for a user
    public shoppingBasket getBasket(Long userId) {
        // Find the basket by user ID, or create a new one if it doesn't exist
        return basketRepository.findByUserId(userId).orElseGet(() -> {
            shoppingBasket basket = new shoppingBasket();
            basket.setUserId(userId);
            return basketRepository.save(basket);
        });
    }

    
    // Method to add a product to the basket
    public void addProduct(Long userId, Long productId, int quantity) {
        shoppingBasket basket = getBasket(userId);
        if (basket.getProductID().contains(productId)) {
            basket.incrementQuantity(productId);
        } else {
            basket.getProductID().add(productId);
            basket.getQuantities().add(quantity);
        }
        basketRepository.save(basket);
    }

    // Method to remove a product from the basket
    public void removeProduct(Long userId, Long productId) {
        shoppingBasket basket = getBasket(userId);
        basket.decrementQuantity(productId);
        basketRepository.save(basket);
    }
     // Method to empty the basket
    public void emptyBasket(Long userId) {
        shoppingBasket basket = getBasket(userId);
        basket.getProductID().clear();
        basket.getQuantities().clear();
        basketRepository.save(basket);
    }
    
}

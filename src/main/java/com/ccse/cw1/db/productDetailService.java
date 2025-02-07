package com.ccse.cw1.db;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class productDetailService 
{
    @Autowired
    private productRepository repository;

    //saves a product to the database
    public void saveProduct(Product product)
    {
        repository.save(product);
    }
    //returns a product by its name
    public Product getProductByName(String name)
    {
        Optional<Product> product = repository.findByname(name);
        if (product.isPresent())
        {
            return product.get();
        }
        else
        {
            return null;
        }
    }

    //returns a list of all products of a certain category
    public List<Product> findByCategory(int category)
    {
        List<Product> allProducts = repository.findAll();
        List<Product> categoryProducts = new ArrayList<Product>();
        for (Product product : allProducts)
        {
            if (product.getCategory() == category)
            {
                categoryProducts.add(product);
            }
        }
        return categoryProducts;
    }

    //returns a product based on its ID
    public Product getProductById(long id)
    {
        Optional<Product> product = repository.findByid(id);
        if (product.isPresent())
        {
            return product.get();
        }
        else
        {
            return null;
        }
    }
    //deletes a product based on its id
    public void deleteProduct(long id)
    {
        repository.deleteById(id);
    }
}

package com.ccse.cw1.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// This is the repository for the Product class, extends JpaRepository 
// for access to CRUD operations
public interface productRepository extends JpaRepository<Product, Long>
{
    // custom query to find a product by their id
    Optional<Product> findByid(long id);
    // custom query to find a product by its name
    Optional<Product> findByname(String name);
}

package com.ccse.cw1.db;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// This is the repository for the shoppingBasket class, extends JpaRepository 
// for access to CRUD operations
public interface basketRepository extends JpaRepository<shoppingBasket, Long> {
    // custom query to find a basket by the user id
    Optional<shoppingBasket> findByUserId(Long userId);
    
}

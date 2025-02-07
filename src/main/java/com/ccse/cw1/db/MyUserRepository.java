package com.ccse.cw1.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// This is the repository for the MyUser class, extends JpaRepository 
// for access to CRUD operations
public interface MyUserRepository extends JpaRepository<MyUser, Long>
{
    // custom query to find a user by their username/email
    Optional<MyUser> findByUsername(String username);
    
}

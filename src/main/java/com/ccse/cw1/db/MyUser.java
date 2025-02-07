package com.ccse.cw1.db;

import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Annotates the class as an entity so that it can be mapped to a table in the database
@Entity
public class MyUser implements UserDetails
{
    // Annotates the field as the primary key and 
    // configures it to be generated automatically
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Defines fields for user attributes, these will form columns in the database table
    private String username;
    private String password;
    private String role;

        // Override the getAuthorities method to return the user's roles as a collection of GrantedAuthority
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Stream.of(role.split(","))
                     .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                     .collect(Collectors.toList());
        }
    
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }
    
        @Override
        public boolean isAccountNonLocked() {
            return true;
        }
    
        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }
    
        @Override
        public boolean isEnabled() {
            return true;
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
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getRole()
    {
        return role;
    }
    public void setRole(String role)
    {
        this.role = role;
    }
}

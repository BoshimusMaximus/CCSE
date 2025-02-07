package com.ccse.cw1.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class MyUserDetailService implements UserDetailsService
{
    @Autowired
    private MyUserRepository repository;
    
    
    //loads the user by their username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<MyUser> user = repository.findByUsername(username);
        if (user.isPresent())
        {
            return user.get();
        }
        else
        {
            throw new UsernameNotFoundException("User not found: " + username);
            
        }
        
    }

}

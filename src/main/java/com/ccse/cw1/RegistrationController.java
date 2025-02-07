package com.ccse.cw1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ccse.cw1.db.MyUser;
import com.ccse.cw1.db.MyUserRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class RegistrationController
{
    @Autowired
    private MyUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //registers a new account
    public MyUser RegisterNewAccount(RegistrationDTO rDto)
    {
        if (duplicateEmail(rDto.getEmail()))
        {
            throw new IllegalArgumentException("Email already in use");
        }
        else
        {
            MyUser myUser = new MyUser();
        myUser.setUsername(rDto.getEmail());
        myUser.setPassword(passwordEncoder.encode(rDto.getPassword()));
        //edit this to "ADMIN" to create an admin user account
        myUser.setRole("USER");
        System.out.println("registered");
        return repository.save(myUser);
        }
        
        
    }

    //checks if the email is already in use
    private boolean duplicateEmail(String email)
    {
        return repository.findByUsername(email).isPresent();
    }
    
    
}

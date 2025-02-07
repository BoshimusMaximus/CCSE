package com.ccse.cw1;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegistrationDTO {
    //fields for the user registration
    @NotEmpty
    private String password;
    @NotEmpty
    private String matchingPassword;
    
    @NotEmpty
    @Email
    private String email;
    private String role = "USER";
    

    //getters and setters for the fields
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getMatchingPassword()
    {
        return matchingPassword;
    }
    public void setMatchingPassword(String matchingPassword)
    {
        this.matchingPassword = matchingPassword;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getRole()
    {
        return role;
    }
}

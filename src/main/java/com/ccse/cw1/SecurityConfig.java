package com.ccse.cw1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import com.ccse.cw1.db.MyUserDetailService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Autowired
    private MyUserDetailService userDetailService;

    // Security fileter chain configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(authorize -> authorize
                //allows access to the home page, registration page, css and images to all users
                .requestMatchers("/", "/home","/register/**","/css/**","images/**").permitAll()
                //allows access to the admin page only to users with the role ADMIN
                .requestMatchers("/admin/**").hasRole("ADMIN")
                //allows access to these pages to any signed in user
                .requestMatchers("/user/**","/basket/**","/eGuitars","/aGuitars","/product").hasAnyRole("ADMIN","USER")
                //denies any access to any other requests
                .anyRequest().denyAll()
                
            )
            //enables the default login page created by Spring Security
            .formLogin(withDefaults())
            //enables the default logout page created by Spring Security, also clears the session cookie upon logout
            .logout(logout -> logout.permitAll().deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler()))
            //enables CSRF protection, with the CSRF token stored in a cookie
            .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .build();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        return userDetailService;
    }

    //configures the authentication provider to use the userDetailService and password encoder
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    //configures the password encoder to use BCrypt
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    // configures the logout success handler to redirect to the home page
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        SimpleUrlLogoutSuccessHandler successHandler = new SimpleUrlLogoutSuccessHandler();
        successHandler.setDefaultTargetUrl("/home");
        return successHandler;
    }

}

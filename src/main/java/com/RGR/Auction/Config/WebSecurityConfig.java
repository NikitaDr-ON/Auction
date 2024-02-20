package com.RGR.Auction.Config;

import com.RGR.Auction.Service.UserServices.UserDetailsServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImple userDetailsServiceImple;


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf()
            .disable().authorizeRequests()
                .antMatchers("/lk","/index/*","/search").authenticated()
                .antMatchers("/admin","/admin/*").hasAuthority("ADMIN")
                .antMatchers("/registr/*","/registr","/index","/login","/").permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login");

    }
    @Bean
    public PasswordEncoder bcryptPasswordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
                .userDetailsService(userDetailsServiceImple)
                .passwordEncoder(bcryptPasswordEncoder());
    }


}
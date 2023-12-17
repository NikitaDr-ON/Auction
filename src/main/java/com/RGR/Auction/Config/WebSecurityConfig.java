package com.RGR.Auction.Config;

import com.RGR.Auction.Service.DataService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf()
            .disable().authorizeRequests()
                .antMatchers("/privateOffice","/lk").authenticated()
                .antMatchers("/authorization","/hello","/registr","/index","/login","/reg").permitAll()
                .and().formLogin().loginPage("/login")
                .permitAll()
                .and()
                .logout();

    }
    @Bean
    public PasswordEncoder bcryptPasswordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userService).passwordEncoder(bcryptPasswordEncoder());
    }

}
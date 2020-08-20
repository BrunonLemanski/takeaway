package com.brunon.takeaway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.brunon.takeaway.security.SecurityConstants.ADMIN_ROLE;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("pass")
                .roles(ADMIN_ROLE)
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/item").permitAll()
                .antMatchers("/order").permitAll()
                .antMatchers("/admin").hasRole(ADMIN_ROLE)
                .antMatchers("/admin/**").hasRole(ADMIN_ROLE)
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
    }
}

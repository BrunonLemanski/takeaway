package com.brunon.takeaway.security;

import com.brunon.takeaway.service.CustomeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.brunon.takeaway.security.SecurityConstants.ADMIN_ROLE;
import static com.brunon.takeaway.security.SecurityConstants.SUPER_ADMIN_ROLE;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomeUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("pass")
                .roles(ADMIN_ROLE)
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/item").permitAll()
                .antMatchers("/order").permitAll()
                .antMatchers("/admin").hasAnyRole(ADMIN_ROLE, SUPER_ADMIN_ROLE)
                .antMatchers("/admin/orders/**").hasAnyRole(ADMIN_ROLE, SUPER_ADMIN_ROLE)
                .antMatchers("/admin/users/**").hasRole(SUPER_ADMIN_ROLE)
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}

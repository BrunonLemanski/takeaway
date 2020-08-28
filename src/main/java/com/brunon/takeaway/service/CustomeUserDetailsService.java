package com.brunon.takeaway.service;

import com.brunon.takeaway.model.Admin;
import com.brunon.takeaway.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomeUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByLogin(login);
        UserBuilder builder = null;

        if(admin == null) {
            throw new UsernameNotFoundException(login);
        } else {
            builder = User.withUsername(login);
            builder.password(new BCryptPasswordEncoder().encode(admin.getPassword()));
            builder.roles(admin.getRole().toString());
        }
        return builder.build();
    }
}

package com.example.LMS.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleUserDetailsService implements UserDetailsService {

    private final String adminUsername;
    private final String adminPassword;

    public SimpleUserDetailsService(@Value("${app.admin.username}") String adminUsername,
                                    @Value("${app.admin.password}") String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!adminUsername.equals(username)) {
            throw new UsernameNotFoundException("User not found");
        }

        // {noop} because we use NoOpPasswordEncoder for demo speed
        return new User(adminUsername, "{noop}" + adminPassword,
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

}

package com.byodian.security.config;

import com.byodian.security.InMemoryUserDetailsService;
import com.byodian.security.bad_patterns.UserMorRes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

//@Configuration
public class ProjectConfig3 {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = new UserMorRes("sele", "12345", "read");
        UserDetails user2 = new UserMorRes("john", "123456", "read");
        List<UserDetails> users = List.of(user1, user2);
        return new InMemoryUserDetailsService(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

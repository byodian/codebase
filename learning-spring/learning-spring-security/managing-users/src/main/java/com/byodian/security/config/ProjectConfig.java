package com.byodian.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class ProjectConfig {
    @Bean
    UserDetailsService userDetailsService() {
        var user = User.withUsername("john")
            .password("12345")
            .authorities("read")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // To customise authentication and authorization
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // configures endpoint authorization with the same behavior as the default one.
        // The default authentication is Basic authentication
        http.httpBasic(Customizer.withDefaults());
        // configures the authorization rules at the endpoint level
        http.authorizeHttpRequests(
//            auth -> auth.anyRequest().authenticated()
            auth -> auth.anyRequest().permitAll()
        );

        return http.build();
    }
}

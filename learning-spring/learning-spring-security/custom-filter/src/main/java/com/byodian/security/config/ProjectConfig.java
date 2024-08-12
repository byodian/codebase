package com.byodian.security.config;

import com.byodian.security.AuthenticationLoggingFilter;
import com.byodian.security.RequestValidationFilter;
import com.byodian.security.StaticKeyAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig {
    private final StaticKeyAuthenticationFilter filter;
    public ProjectConfig(StaticKeyAuthenticationFilter staticKeyAuthenticationFilter) {
        this.filter =  staticKeyAuthenticationFilter;

    }

//    @Bean
//    UserDetailsService userDetailsService() {
//        var user = User.withUsername("john")
//            .password("12345")
//            .authorities("read")
//            .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // To customise authentication and authorization
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(// Adding a filter before an existing one in the chain
            new RequestValidationFilter(),
            BasicAuthenticationFilter.class
        )
        .addFilterAfter( // Adding a filter after an existing one in the chain
            new AuthenticationLoggingFilter(),
            BasicAuthenticationFilter.class
        )
        .addFilterAt( // Adding the filter at the location of another in the chain
            filter,
            BasicAuthenticationFilter.class
        )
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }
}

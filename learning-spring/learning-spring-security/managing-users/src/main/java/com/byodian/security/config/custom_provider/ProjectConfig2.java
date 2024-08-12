package com.byodian.security.config.custom_provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//public class ProjectConfig2 {
//    private final CustomAuthenticationProvider customAuthenticationProvider;
//
//    public ProjectConfig2(CustomAuthenticationProvider customAuthenticationProvider) {
//        this.customAuthenticationProvider = customAuthenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.httpBasic(Customizer.withDefaults());
//        http.authenticationProvider(customAuthenticationProvider);
//
//        http.authorizeHttpRequests(
//            auth -> auth.anyRequest().authenticated()
//        );
//        return http.build();
//    }
//}

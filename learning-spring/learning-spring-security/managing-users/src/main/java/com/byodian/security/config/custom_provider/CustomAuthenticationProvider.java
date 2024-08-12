package com.byodian.security.config.custom_provider;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//    @Override
//    public Authentication authenticate(Authentication authentication) {
//        String username = authentication.getName();
//        String password = String.valueOf(authentication.getCredentials());
//
//        if ("lily".equals(username) && "12345".equals(password)) {
//            return new UsernamePasswordAuthenticationToken(username, password, List.of());
//        } else {
//            throw new AuthenticationCredentialsNotFoundException("Error");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authenticationType) {
//        return UsernamePasswordAuthenticationToken
//            .class
//            .isAssignableFrom((authenticationType));
//    }
//}

package com.byodian.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class AuthenticationLoggingFilter extends OncePerRequestFilter {
    private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        var requestId = request.getHeader("Request-Id");
        logger.info("Successfully authenticated request with id: " + requestId);
        chain.doFilter(request, response);
    }
}

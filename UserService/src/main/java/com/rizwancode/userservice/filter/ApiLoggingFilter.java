package com.rizwancode.userservice.filter;

import java.io.IOException;

import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class ApiLoggingFilter implements Filter {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ApiLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        logger.info("Request: {} {}", httpRequest.getMethod(), httpRequest.getRequestURI());

        chain.doFilter(request, response);
    }
}

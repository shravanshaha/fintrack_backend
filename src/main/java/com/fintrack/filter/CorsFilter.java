package com.fintrack.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class CorsFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException,
            ServletException {

        HttpServletResponse res =
                (HttpServletResponse) response;

        res.setHeader(
                "Access-Control-Allow-Origin",
                "https://fintrack-frontend-lemon.vercel.app"
        );

        res.setHeader(
                "Access-Control-Allow-Credentials",
                "true"
        );

        res.setHeader(
                "Access-Control-Allow-Headers",
                "*"
        );

        res.setHeader(
                "Access-Control-Allow-Methods",
                "GET,POST,PUT,DELETE,OPTIONS"
        );

        chain.doFilter(
                request,
                response
        );
    }
}
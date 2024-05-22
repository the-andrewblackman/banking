package com.bank.bank.util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class PortBasedRequestFilter implements Filter {

    private static final int ADMIN_PORT = 8081;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // Check if the 'X-Allow-Testing' header is present and bypass the port check if it is
        if ("true".equals(httpServletRequest.getHeader("X-Allow-Testing")) || httpServletRequest.getLocalPort() == ADMIN_PORT) {
            chain.doFilter(request, response);
        } else {
            response.getWriter().write("Invalid access port.");
            response.getWriter().close();
        }
    }

    @Override
    public void destroy() {
    }
}


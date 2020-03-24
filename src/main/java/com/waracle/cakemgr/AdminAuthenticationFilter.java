package com.waracle.cakemgr;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
/**
 * This Java filter demonstrates how to intercept the request
 * and transform the response to implement authentication feature.
 * for the website's back-end.Although this functionality is very trivial
 * now,later can be transformed into well developed authentication and authorization
 * mechanisms like OAuth2 or a proprietary protocol.
 *
 * @author 
 */
@WebFilter("/cakes")
public class AdminAuthenticationFilter implements Filter {
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = (session != null);
        String oauthToken = httpRequest.getParameter("token");
 
        
        if (isLoggedIn && oauthToken.contains("abcd")) {
            chain.doFilter(request, response);
 
        } else {
            // the admin is not logged in, so authentication is required
            // forwards to the Login page
            RequestDispatcher dispatcher = request.getRequestDispatcher("cakes.html");
            dispatcher.forward(request, response);
 
        }
 
    }
 
    public AdminAuthenticationFilter() {
    }
 
    public void destroy() {
    }
 
    public void init(FilterConfig fConfig) throws ServletException {
    }
 
}
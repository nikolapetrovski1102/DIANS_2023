package com.example.wineries.controllers.filter;



import com.example.wineries.models.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter(filterName = "auth-filter", urlPatterns = "/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        initParams = {
                @WebInitParam(name = "login-path", value = "/login"),
                @WebInitParam(name = "register-path", value = "/register"),
                @WebInitParam(name = "static-path", value = "/static")
        })
public class LoginFilter implements Filter {

    private String loginPath;
    private String registerPath;
    private String staticPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loginPath = filterConfig.getInitParameter("login-path");
        registerPath = filterConfig.getInitParameter("register-path");
        staticPath = filterConfig.getInitParameter("static-path");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");
        String path = request.getServletPath();

        if (shouldAllowRequest(path, user)) {
            System.out.println("AuthFilter preprocessing...");
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("AuthFilter postprocessing...");
        } else {
            response.sendRedirect("/login");
        }
    }

    private boolean shouldAllowRequest(String path, User user) {
        return path.startsWith(loginPath) || path.startsWith(registerPath) || user != null || path.startsWith(staticPath);
    }

}

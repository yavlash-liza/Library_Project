package com.yavlash.library.controller.filter;

import com.yavlash.library.controller.command.CommandType;
import com.yavlash.library.controller.command.RequestParameter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.yavlash.library.controller.command.SessionAttribute.CURRENT_PAGE;

@WebFilter(filterName = "PageFilter", urlPatterns = {"/controller", "/pages/*"})
public class PageFilter implements Filter {
    private static final String COMMAND_DELIMITER = "?";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        if (req.getParameter(RequestParameter.COMMAND) != null) {
            String commandType = req.getParameter(RequestParameter.COMMAND);
            if (CommandType.CHANGE_LOCALE != CommandType.valueOf(commandType.toUpperCase())) {
                String currentPage = req.getServletPath() + COMMAND_DELIMITER + req.getQueryString();
                session.setAttribute(CURRENT_PAGE, currentPage);
            }
        } else {
            session.setAttribute(CURRENT_PAGE, req.getServletPath());
        }
        filterChain.doFilter(request, response);
    }
}
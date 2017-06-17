package com.source.it.web.filters;

import com.source.it.jdbc.model.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.source.it.web.utils.ServletConstants.*;

public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
        User user = (User)session.getAttribute(USER);
        if (user == null) {
            req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
        } else {
            String userRole = user.getUserRole().getRole();
            switch (userRole) {
                case SECRETARY_ROLE :
                    req.getRequestDispatcher(ORDER_MANAGEMENT).forward(req, resp);
                    break;

                case CLIENT :
                    req.getRequestDispatcher(CLIENT_PAGE).forward(req, resp);
                    break;

                default :
                    chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}

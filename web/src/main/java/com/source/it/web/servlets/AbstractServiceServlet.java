package com.source.it.web.servlets;

import com.source.it.services.UserRoleService;
import com.source.it.services.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractServiceServlet extends HttpServlet {
    protected UserService userService = new UserService();
    protected UserRoleService userRoleService = new UserRoleService();

    public Map<String, String> getRequestParams(HttpServletRequest req) {
        Map<String, String> params = new HashMap<>();
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String current = parameterNames.nextElement();
            params.put(current, req.getParameter(current));
        }

        return params;
    }
}

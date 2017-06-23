package com.source.it.web.servlets;

import com.source.it.services.UserRoleService;
import com.source.it.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractServiceServlet extends HttpServlet {
    protected AutowireCapableBeanFactory ctx;

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserRoleService userRoleService;

    @Override
    public void init() throws ServletException {
        super.init();
        if (ctx == null) {
            WebApplicationContext context = WebApplicationContextUtils
                    .getWebApplicationContext(getServletContext());
            ctx = context.getAutowireCapableBeanFactory();
            ctx.autowireBean(this);
        }
    }

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

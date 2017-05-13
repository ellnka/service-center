package com.source.it.web.servlets;

import com.source.it.web.UserService;
import jdbc.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/JSP/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Enumeration<String> parameterNames = req.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
                String current = parameterNames.nextElement();
                params.put(current, req.getParameter(current));
        }
        String login;
            if ((login = params.get("login")) != null) {
                User user = userService.getUserByLogin(login);
                if (user == null || !user.getPassword().equals(params.get("password"))) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("loginFailed", true);
                    resp.sendRedirect("/services/login");
                } else {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("user", user);
                    session.setAttribute("loginFailed", false);
                    Cookie cookie = new Cookie("user_logon", "true");
                    resp.addCookie(cookie);
                    resp.sendRedirect("/services/welcome");
                }
            }
    }
}

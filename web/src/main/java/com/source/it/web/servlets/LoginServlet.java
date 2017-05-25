package com.source.it.web.servlets;

import com.source.it.jdbc.model.User;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;
import static com.source.it.web.utils.ServletConstants.*;

public class LoginServlet extends AbstractServiceServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Request received for method " + req.getMethod() + ", URI = " + req.getRequestURI());
        resp.setContentType(TEXT_HTML);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(LOGIN_JSP);
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> params = getRequestParams(req);
        LOGGER.debug("Request received for method " + req.getMethod() + ", URI = " + req.getRequestURI()
                + ", parameters " + params.toString());
        resp.setContentType(TEXT_HTML);
        HttpSession session = req.getSession(true);
        if (params.get(REGISTER) != null) {
            getServletContext().getRequestDispatcher(REGISTER_JSP).forward(req, resp);
        }
        if (params.get(LOGOUT) != null) {
            session.removeAttribute(USER);
            getServletContext().getRequestDispatcher(LOGIN_JSP).forward(req, resp);
        }
        String login;
            if ((login = params.get(LOGIN)) != null) {
                User user = userService.getUserByLogin(login);
                if (user == null || !user.getPassword().equals(params.get(PASSWORD))) {
                    session.setAttribute(LOGIN_FAILED, true);
                    resp.sendRedirect(LOGIN_PAGE);
                } else {
                    session.setAttribute(USER, user);
                    session.setAttribute(LOGIN_FAILED, false);
                    resp.sendRedirect(LOGIN_PAGE);
                }
            }
    }
}

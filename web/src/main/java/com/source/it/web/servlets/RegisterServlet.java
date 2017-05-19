package com.source.it.web.servlets;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.User;
import org.apache.commons.validator.routines.EmailValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import static com.source.it.web.utils.ServletConstants.*;

public class RegisterServlet extends AbstractServiceServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        getServletContext().getRequestDispatcher(REGISTER_JSP).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        Map<String, String> params = getRequestParams(req);

        boolean errorsInForm = false;
        try {
            if (!validateDate(params.get(DATE_OF_BIRTH))) {
                req.setAttribute(DATE_ERROR, DATE_MESSAGE);
                errorsInForm = true;
            }
            if (!validateEmail(params.get(EMAIL))) {
                req.setAttribute(EMAIL_ERROR, EMAIL_NOT_VALID);
                errorsInForm = true;
            }
            if (!errorsInForm) {
                User user = createUser(params);
                userService.createUser(user);
            }
        } catch (GenericDaoException e) {
            if (e.getCause().getMessage().toLowerCase().contains(NON_UNIQUE_EMAIL)) {
                req.setAttribute(EMAIL_ERROR, EMAIL_MESSAGE);
            }
            if (e.getCause().getMessage().toLowerCase().contains(NON_UNIQUE_LOGIN)) {
                req.setAttribute(LOGIN_ERROR, LOGIN_MESSAGE);
            }
            errorsInForm = true;
        }
        if (errorsInForm) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(REGISTER_JSP);
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(LOGIN_PAGE);
        }
    }

    private boolean validateDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    private User createUser(Map<String, String> params) {
        User user = new User();
        user.setName(params.get(NAME));
        user.setLastName(params.get(LAST_NAME));
        user.setLogin(params.get(LOGIN));
        user.setEmail(params.get(EMAIL));
        user.setPassword(params.get(PASSWORD));
        user.setUserRole(userRoleService.getUserRoleByRoleName(CLIENT));
        return user;
    }
}

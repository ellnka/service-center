package com.source.it.web.utils;

public class ServletConstants {
    //Common constants
    public final static String TEXT_HTML     = "text/html";
    public final static String DATE_FORMAT   = "YYYY-mm-DD";
    public final static String CLIENT        = "Client";

    /*Parameters constants*/
    public final static String LOGIN         = "login";
    public final static String LOGOUT        = "logout";
    public final static String NAME          = "name";
    public final static String LAST_NAME     = "lastName";
    public final static String DATE_OF_BIRTH = "dateOfBirth";
    public final static String EMAIL         = "email";
    public final static String PASSWORD      = "password";
    public final static String LOGIN_FAILED  = "loginFailed";
    public final static String USER          = "user";
    public final static String REGISTER      = "register";

    /*Messages constants*/
    public final static String DATE_ERROR    = "dateError";
    public final static String EMAIL_ERROR   = "emailError";
    public final static String LOGIN_ERROR   = "loginError";
    public final static String LOGIN_MESSAGE = "Login is already used";
    public final static String EMAIL_MESSAGE = "Email is already used";
    public final static String DATE_MESSAGE  = "Date should be at \"YYYY-mm-DD\" format";

    public final static String EMAIL_NOT_VALID = "Email is not valid";

    /*JSP mapping constants*/
    public final static String REGISTER_JSP  = "/JSP/register.jsp";
    public final static String LOGIN_JSP     = "/JSP/login.jsp";

    /*Path constants*/
    public final static String LOGIN_PAGE     = "/services/login";

    /*Exceptions constants*/
    public final static String NON_UNIQUE_EMAIL = "email_unique";
    public final static String NON_UNIQUE_LOGIN = "login_unique";

    private ServletConstants() {}
}

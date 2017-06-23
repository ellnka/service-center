package com.source.it.web.utils;

public class ServletConstants {
    //Common constants
    public final static String TEXT_HTML     = "text/html";
    public final static String DATE_FORMAT   = "YYYY-mm-DD";
    public final static String CLIENT        = "Client";

    /*Parameters constants*/
    public final static String LOGIN          = "login";
    public final static String LOGOUT         = "logout";
    public final static String NAME           = "name";
    public final static String LAST_NAME      = "lastName";
    public final static String DATE_OF_BIRTH  = "dateOfBirth";
    public final static String EMAIL          = "email";
    public final static String PASSWORD       = "password";
    public final static String LOGIN_FAILED   = "loginFailed";
    public final static String USER           = "user";
    public final static String REGISTER       = "register";
    public final static String ORDER          = "order";
    public final static String ORDERS         = "orders";
    public final static String CREATE_ORDER   = "create_order";
    public final static String SEARCH_ORDER   = "search_for_order";
    public final static String ORDER_NUMBER   = "order_number";
    public final static String ORDER_CREATION = "order_creation";
    public final static String SERIAL_NUMBER  = "serial_number";

    /*Messages constants*/
    public final static String DATE_ERROR    = "dateError";
    public final static String EMAIL_ERROR   = "emailError";
    public final static String LOGIN_ERROR   = "loginError";
    public final static String LOGIN_MESSAGE = "Login is already used";
    public final static String EMAIL_MESSAGE = "Email is already used";
    public final static String DATE_MESSAGE  = "Date should be at \"YYYY-mm-DD\" format";

    public final static String EMAIL_NOT_VALID = "Email is not valid";

    /*JSP mapping constants*/
    public final static String REGISTER_JSP         = "/JSP/register.jsp";
    public final static String LOGIN_JSP            = "/JSP/login.jsp";
    public final static String ORDER_MANAGEMENT_JSP = "/JSP/order-management.jsp";
    public final static String CLIENT_PAGE_JSP      = "/JSP/client-page.jsp";

    /*Path constants*/
    public final static String LOGIN_PAGE       = "/services/login";
    public final static String MAIN_PAGE        = "/services/main";
    public final static String ORDER_MANAGEMENT = "/orderManagement";
    public final static String CLIENT_PAGE      = "/clientPage";

    /*Exceptions constants*/
    public final static String NON_UNIQUE_EMAIL = "email_unique";
    public final static String NON_UNIQUE_LOGIN = "login_unique";

    /*UserRoles constants*/
    public final static String SECRETARY_ROLE = "Secretary";

    private ServletConstants() {}
}

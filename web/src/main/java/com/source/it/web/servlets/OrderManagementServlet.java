package com.source.it.web.servlets;

import com.source.it.jdbc.dao.GenericDao;
import com.source.it.jdbc.model.Item;
import com.source.it.jdbc.model.Order;
import com.source.it.jdbc.model.Status;
import com.source.it.jdbc.model.User;
import com.source.it.services.ItemService;
import com.source.it.services.OrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;

import static com.source.it.web.utils.ServletConstants.*;

public class OrderManagementServlet extends AbstractServiceServlet {
    private static final Logger LOGGER = Logger.getLogger(OrderManagementServlet.class);

    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;

    @Autowired
    @Qualifier("itemService")
    private ItemService itemService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Request received for method " + req.getMethod() + ", URI = " + req.getRequestURI());
        resp.setContentType(TEXT_HTML);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ORDER_MANAGEMENT_JSP);
        req.setAttribute(CREATE_ORDER, false);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Request received for method " + req.getMethod() + ", URI = " + req.getRequestURI());
        resp.setContentType(TEXT_HTML);
        Map<String, String> params = getRequestParams(req);
        if (params.get(CREATE_ORDER) != null) {
            req.setAttribute(CREATE_ORDER, true);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ORDER_MANAGEMENT_JSP);
            dispatcher.forward(req, resp);
        }

        if (params.get(ORDER_CREATION) != null) {
            User user = new User();
            user.setName(params.get(NAME));
            user.setLastName(params.get(LAST_NAME));
            user.setLogin(params.get(LOGIN));
            user.setEmail(params.get(EMAIL));
            user = getUserOrCreateNew(user);

            Item item = itemService.getBySerialNumber(params.get(SERIAL_NUMBER));
            if (item == null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ORDER_MANAGEMENT_JSP);
                dispatcher.forward(req, resp);
            }

            Order order = new Order();
            order.setDate(new Date(System.currentTimeMillis()));
            order.setItem(item);
            order.setStatus(Status.NEW);
            order.setUser(user);
            order.setWarranty(true);
            orderService.createOrder(order);

            req.setAttribute(ORDER, order);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ORDER_MANAGEMENT_JSP);
            dispatcher.forward(req, resp);
        }

        if (params.get(SEARCH_ORDER) != null) {
            long orderNumber = Long.parseLong(params.get(ORDER_NUMBER));
            Order order = orderService.getOrderById(orderNumber);
            if (order != null) {
                req.setAttribute(ORDER, order);
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ORDER_MANAGEMENT_JSP);
            dispatcher.forward(req, resp);
        }
    }

    private User getUserOrCreateNew(User user) {
        User savedUser = userService.getUserByLogin(user.getLogin());
        if (savedUser == null) {
            user.setUserRole(userRoleService.getUserRoleByRoleName(CLIENT));
            user.setPassword(RandomStringUtils.random(10, true, true));
            userService.createUser(user);
            return user;
        }
        return savedUser;
    }
}

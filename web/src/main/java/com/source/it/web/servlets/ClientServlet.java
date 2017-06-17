package com.source.it.web.servlets;

import com.source.it.jdbc.model.Order;
import com.source.it.jdbc.model.User;
import com.source.it.services.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.source.it.web.utils.ServletConstants.*;

public class ClientServlet extends AbstractServiceServlet {
    private static final Logger LOGGER = Logger.getLogger(ClientServlet.class);

    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Request received for method " + req.getMethod() + ", URI = " + req.getRequestURI());
        resp.setContentType(TEXT_HTML);
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute(USER);
        if (user !=  null) {
            List<Order> orders = orderService.getAllOrdersByUser(user);
            req.setAttribute(ORDERS, orders);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(CLIENT_PAGE_JSP);
        dispatcher.forward(req, resp);
    }
}

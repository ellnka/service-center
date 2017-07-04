package com.source.it.web.servlets;

import com.source.it.jdbc.model.Order;
import com.source.it.jdbc.model.User;
import com.source.it.services.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.transport.Session;
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
import java.util.Map;

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
        Map<String, String> params = getRequestParams(req);
        String orderId = params.get(ORDER_ID);
        if (StringUtils.isNotEmpty(orderId)) {
            req.setAttribute(ORDER, orderService.getOrderById(Long.parseLong(orderId)));
        }
        if (user !=  null) {
            List<Order> orders = orderService.getAllOrdersByUser(user);
            req.setAttribute(ORDERS, orders);
        }
        String paymentEnabled = params.get(ENABLE_PAYMENT);
        if (paymentEnabled != null && Boolean.parseBoolean(paymentEnabled)) {
            req.setAttribute(PAY_FOR_ALL_ENABLED, true);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(CLIENT_PAGE_JSP);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

package com.source.it.web.servlets;

import com.source.it.jdbc.model.Order;
import com.source.it.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.source.it.web.utils.ServletConstants.*;

public class ClientOrderManagementServlet extends AbstractServiceServlet {
    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> params = getRequestParams(req);
        long orderId = Long.parseLong(params.get(ORDER_ID));
        Order order = orderService.getOrderById(orderId);
        HttpSession session = req.getSession(true);
        List<Order> orderForPayment = (List<Order>)session.getAttribute(ORDERS_FOR_PAYMENT);
        if (orderForPayment == null) {
            orderForPayment = new ArrayList<>();
        }
        if (!orderForPayment.contains(order)) {
            orderForPayment.add(order);
        }
        session.setAttribute(ORDERS_FOR_PAYMENT, orderForPayment);
    }
}

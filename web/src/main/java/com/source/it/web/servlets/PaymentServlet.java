package com.source.it.web.servlets;

import com.source.it.jdbc.model.Order;
import com.source.it.jdbc.model.Status;
import com.source.it.payment.stub.PaymentBean;
import com.source.it.services.OrderService;
import com.source.it.web.controller.PaymentController;
import org.apache.commons.lang3.StringUtils;
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

public class PaymentServlet extends AbstractServiceServlet {
    private static final Logger LOGGER = Logger.getLogger(PaymentServlet.class);

    @Autowired
    private PaymentController paymentController;

    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Request received for method " + req.getMethod() + ", URI = " + req.getRequestURI());
        resp.setContentType(TEXT_HTML);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(CLIENT_PAGE);
        dispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(TEXT_HTML);
        Map<String, String> params = getRequestParams(req);
        PaymentBean paymentBean = createPaymentBean(params);
        paymentBean = paymentController.processPayment(paymentBean);
        req.setAttribute("paymentSuccessful", paymentBean.isSuccessful());
        HttpSession session = req.getSession(true);
        List<Order> orders = (List<Order>)session.getAttribute(ORDERS_FOR_PAYMENT);
        if (paymentBean.isSuccessful()) {

            if (orders != null && StringUtils.isEmpty(params.get(ORDER_ID))) {
               for (Order order : orders) {
                   order.setStatus(Status.PAID);
                   orderService.update(order);
               }
               session.removeAttribute(ORDERS_FOR_PAYMENT);
            } else {
                long orderId = Long.parseLong(params.get(ORDER_ID));
                Order order = orderService.getOrderById(orderId);
                if (order != null) {
                    if (orders != null && orders.contains(order)) {
                        orders.remove(order);
                        session.setAttribute(ORDERS_FOR_PAYMENT, orders);
                    }
                    order.setStatus(Status.PAID);
                    orderService.update(order);
                }
            }
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(CLIENT_PAGE);
        dispatcher.forward(req, resp);
    }

    private PaymentBean createPaymentBean(Map<String, String> params) {
        PaymentBean result  = new PaymentBean();
        result.setName(params.get("name"));
        result.setExpDateMonth(params.get("month"));
        result.setExpDateYear(params.get("year"));
        result.setCardNumber(params.get("cardNumber"));
        result.setCvc(params.get("cvc"));

        return result;
    }

}

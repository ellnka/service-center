package com.source.it.web.controller;

import com.source.it.payment.stub.PaymentBean;
import com.source.it.payment.stub.PaymentFault_Exception;
import com.source.it.payment.stub.PaymentStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentController {
    @Autowired
    @Qualifier("paymentClient")
    private PaymentStub paymentClient;

    public PaymentBean processPayment(PaymentBean bean) {
        try {
            bean = paymentClient.processPaymentBean(bean);
        } catch (PaymentFault_Exception fault) {
            bean.setSuccessful(false);
        }
        return bean;
    }
}

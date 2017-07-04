package com.source.it.payment.stub.impl;

import com.source.it.payment.stub.PaymentStub;
import com.source.it.payment.stub.exceptions.PaymentFault;
import com.source.it.payment.stub.model.PaymentBean;

import javax.jws.WebService;

@WebService(endpointInterface = "com.source.it.payment.stub.PaymentStub",
        targetNamespace = "http://service.paymentServiceStub.it.source.com/paymentService",
        serviceName = "PaymentStub",
        portName = "PaymentStubPort",
        wsdlLocation = "PaymentStub.wsdl")
public class PaymentStubImpl implements PaymentStub {

    @Override
    public PaymentBean processPaymentBean(PaymentBean paymentBean) throws PaymentFault {
        String cardNumber = paymentBean.getCardNumber();
        if (cardNumber.length() > 5) {
            paymentBean.setSuccessful(true);
        } else {
            paymentBean.setSuccessful(false);
        }
        return paymentBean;
    }
}
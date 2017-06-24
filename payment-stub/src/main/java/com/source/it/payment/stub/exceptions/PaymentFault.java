package com.source.it.payment.stub.exceptions;

import javax.xml.ws.WebFault;

@WebFault()
public class PaymentFault extends RuntimeException {
    public PaymentFault() {
        super();
    }

    public PaymentFault(String message) {
        super(message);
    }

    public PaymentFault(String message, Throwable cause) {
        super(message, cause);
    }

}

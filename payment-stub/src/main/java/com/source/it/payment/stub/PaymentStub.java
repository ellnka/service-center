package com.source.it.payment.stub;

import com.source.it.payment.stub.exceptions.PaymentFault;
import com.source.it.payment.stub.model.PaymentBean;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
        use = SOAPBinding.Use.LITERAL,
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface PaymentStub {
    @WebMethod(action="#processPaymentBean")
    @WebResult(name = "response")
    PaymentBean processPaymentBean(@WebParam(name="paymentBean") PaymentBean paymentBean)
            throws PaymentFault;
}

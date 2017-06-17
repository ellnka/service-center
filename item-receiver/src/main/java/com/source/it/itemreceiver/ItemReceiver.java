package com.source.it.itemreceiver;

import com.source.it.itemreceiver.exceptions.ItemFault;
import com.source.it.itemreceiver.model.Item;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
        use = SOAPBinding.Use.LITERAL,
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface ItemReceiver {
    @WebMethod(action="#processItem")
    @WebResult(name = "response")
    String processItem(@WebParam(name="item") Item item)
            throws ItemFault;
}

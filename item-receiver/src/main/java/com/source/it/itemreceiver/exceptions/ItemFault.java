package com.source.it.itemreceiver.exceptions;

import javax.xml.ws.WebFault;

@WebFault()
public class ItemFault extends RuntimeException {
    public ItemFault() {
        super();
    }

    public ItemFault(String message) {
        super(message);
    }

    public ItemFault(String message, Throwable cause) {
        super(message, cause);
    }

}


package com.source.it.payment.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.source.it.payment.stub package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ProcessPaymentBean_QNAME = new QName("http://stub.payment.it.source.com/", "processPaymentBean");
    private final static QName _ProcessPaymentBeanResponse_QNAME = new QName("http://stub.payment.it.source.com/", "processPaymentBeanResponse");
    private final static QName _PaymentFault_QNAME = new QName("http://stub.payment.it.source.com/", "PaymentFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.source.it.payment.stub
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcessPaymentBean }
     * 
     */
    public ProcessPaymentBean createProcessPaymentBean() {
        return new ProcessPaymentBean();
    }

    /**
     * Create an instance of {@link ProcessPaymentBeanResponse }
     * 
     */
    public ProcessPaymentBeanResponse createProcessPaymentBeanResponse() {
        return new ProcessPaymentBeanResponse();
    }

    /**
     * Create an instance of {@link PaymentFault }
     * 
     */
    public PaymentFault createPaymentFault() {
        return new PaymentFault();
    }

    /**
     * Create an instance of {@link PaymentBean }
     * 
     */
    public PaymentBean createPaymentBean() {
        return new PaymentBean();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessPaymentBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stub.payment.it.source.com/", name = "processPaymentBean")
    public JAXBElement<ProcessPaymentBean> createProcessPaymentBean(ProcessPaymentBean value) {
        return new JAXBElement<ProcessPaymentBean>(_ProcessPaymentBean_QNAME, ProcessPaymentBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessPaymentBeanResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stub.payment.it.source.com/", name = "processPaymentBeanResponse")
    public JAXBElement<ProcessPaymentBeanResponse> createProcessPaymentBeanResponse(ProcessPaymentBeanResponse value) {
        return new JAXBElement<ProcessPaymentBeanResponse>(_ProcessPaymentBeanResponse_QNAME, ProcessPaymentBeanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stub.payment.it.source.com/", name = "PaymentFault")
    public JAXBElement<PaymentFault> createPaymentFault(PaymentFault value) {
        return new JAXBElement<PaymentFault>(_PaymentFault_QNAME, PaymentFault.class, null, value);
    }

}

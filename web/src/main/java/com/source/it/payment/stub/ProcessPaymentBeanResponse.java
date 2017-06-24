
package com.source.it.payment.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for processPaymentBeanResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="processPaymentBeanResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="response" type="{http://stub.payment.it.source.com/}paymentBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "processPaymentBeanResponse", propOrder = {
    "response"
})
public class ProcessPaymentBeanResponse {

    protected PaymentBean response;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentBean }
     *     
     */
    public PaymentBean getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentBean }
     *     
     */
    public void setResponse(PaymentBean value) {
        this.response = value;
    }

}

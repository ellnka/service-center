
package com.source.it.payment.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for processPaymentBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="processPaymentBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="paymentBean" type="{http://stub.payment.it.source.com/}paymentBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "processPaymentBean", propOrder = {
    "paymentBean"
})
public class ProcessPaymentBean {

    protected PaymentBean paymentBean;

    /**
     * Gets the value of the paymentBean property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentBean }
     *     
     */
    public PaymentBean getPaymentBean() {
        return paymentBean;
    }

    /**
     * Sets the value of the paymentBean property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentBean }
     *     
     */
    public void setPaymentBean(PaymentBean value) {
        this.paymentBean = value;
    }

}

package com.source.it.payment.stub.model;

public class PaymentBean {
    private String name;
    private String expDateMonth;
    private String expDateYear;
    private String cardNumber;
    private String cvc;
    private boolean successful;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpDateMonth() {
        return expDateMonth;
    }

    public void setExpDateMonth(String expDateMonth) {
        this.expDateMonth = expDateMonth;
    }

    public String getExpDateYear() {
        return expDateYear;
    }

    public void setExpDateYear(String expDateYear) {
        this.expDateYear = expDateYear;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}


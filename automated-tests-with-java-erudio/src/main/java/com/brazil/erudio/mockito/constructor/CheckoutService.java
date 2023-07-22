package com.brazil.erudio.mockito.constructor;

import java.math.BigDecimal;

import static java.math.BigDecimal.TEN;

public class CheckoutService {

    public BigDecimal purchaseProduct(String productName, String customerID) {
        // any arbitrary implementation
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        return paymentProcessor.chargeCustomer(customerID, TEN);
    }
}

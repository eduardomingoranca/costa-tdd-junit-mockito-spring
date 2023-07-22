package com.brazil.erudio.mockito.constructor;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class PaymentProcessor {
    private boolean allowForeignCurrencies;
    private String fallbackOption;
    private BigDecimal taxRate;

    public PaymentProcessor() {
        this(false, "DEBIT", new BigDecimal("19.00"));
    }

    public PaymentProcessor(String fallbackOption, BigDecimal taxRate) {
        this(false, fallbackOption, taxRate);
    }

    public PaymentProcessor(boolean allowForeignCurrencies, String fallbackOption, BigDecimal taxRate) {
        this.allowForeignCurrencies = allowForeignCurrencies;
        this.fallbackOption = fallbackOption;
        this.taxRate = taxRate;
    }

    // processando o pagamento
    public BigDecimal chargeCustomer(String customerID, BigDecimal netPrice) {
        // any arbitrary implementation
        System.out.println("About to charge customer: " + customerID);
        return ZERO;
    }

}

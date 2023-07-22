package com.brazil.erudio.mockito.constructor;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.math.BigDecimal;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PaymentProcessorTest {

    @Test
    void testMockObjectConstructor() {
        try (MockedConstruction<PaymentProcessor> mocked = mockConstruction(PaymentProcessor.class)) {
            // Every object creation is returning a mock from now on
            PaymentProcessor paymentProcessor = new PaymentProcessor();

            when(paymentProcessor.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(TEN);

            assertEquals(TEN, paymentProcessor.chargeCustomer("42", valueOf(42)));
        }
    }

    @Test
    void testMockDifferentObjectConstruction() {
        try(MockedConstruction<PaymentProcessor> mocked = mockConstruction(PaymentProcessor.class)) {
            PaymentProcessor firstInstance = new PaymentProcessor("PayPal", TEN);
            PaymentProcessor secondInstance = new PaymentProcessor(true, "PayPal", TEN);

            when(firstInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(TEN);
            when(secondInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(TEN);

            assertEquals(TEN, firstInstance.chargeCustomer("42", valueOf(42)));
            assertEquals(TEN, secondInstance.chargeCustomer("42", valueOf(42)));
            assertEquals(2, mocked.constructed().size());
        }
    }

    @Test
    void testMockDifferentObjectConstructionWithAnswer() {
        try(MockedConstruction<PaymentProcessor> mocked = mockConstructionWithAnswer(PaymentProcessor.class,
                // Default answer for the first mock
                invocation -> new BigDecimal("500.00"),
                // Additional answer for all further mocks
                invocation -> new BigDecimal("42.00")))
        {
            PaymentProcessor firstInstance = new PaymentProcessor();
            PaymentProcessor secondInstance = new PaymentProcessor();
            PaymentProcessor thirdInstance = new PaymentProcessor();

            assertEquals(new BigDecimal("500.00"), firstInstance.chargeCustomer("42", BigDecimal.ZERO));
            assertEquals(new BigDecimal("42.00"), secondInstance.chargeCustomer("42", BigDecimal.ZERO));
            assertEquals(new BigDecimal("42.00"), thirdInstance.chargeCustomer("42", BigDecimal.ZERO));
        }
    }
}
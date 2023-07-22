package com.brazil.erudio.mockito.constructor;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.math.BigDecimal;

import static java.math.BigDecimal.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CheckoutServiceTest {

    @Test
    void testMockObjectConstruction() {
        // Given / Arrange
        try(MockedConstruction<PaymentProcessor> mocked = mockConstruction(PaymentProcessor.class,
                (mock, context) -> when(mock.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(TEN)))
        {
            CheckoutService service = new CheckoutService();

            // When / Act
            BigDecimal result = service.purchaseProduct("MacBook Pro", "42");

            // Then / Assert
            assertEquals(TEN, result);
            assertEquals(1, mocked.constructed().size());
        }
    }
}
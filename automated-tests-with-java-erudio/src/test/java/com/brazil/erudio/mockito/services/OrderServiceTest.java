package com.brazil.erudio.mockito.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.UUID;

import static java.util.UUID.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

class OrderServiceTest {

    private final OrderService service = new OrderService();
    private final UUID defaultUUID = fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac");

    @DisplayName("Should Include Random OrderId When No OrderId Exists")
    @Test
    void testShouldIncludeRandomOrderId_When_NoOrderIdExists() {
        // Given / Arrange
        try(MockedStatic<UUID> mockedUUID = mockStatic(UUID.class)) {
            mockedUUID.when(UUID::randomUUID).thenReturn(defaultUUID);

            // When / Act
            Order result = service.createOrder("MacBook Pro",2L, null);

            // Then / Assert
            assertEquals(defaultUUID.toString(), result.getId());
        }

    }

}
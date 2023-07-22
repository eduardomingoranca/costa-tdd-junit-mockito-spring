package com.brazil.erudio.mockito.staticwithparams;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static com.brazil.erudio.mockito.staticwithparams.MyUtils.getWelcomeMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

class MyUtilsTest {

    @Test
    void testShouldMockStaticMethodWithParams() {
        try (MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)) {
            // Given / Arrange
            mockedStatic.when(() -> getWelcomeMessage(eq("Erudio"),
                    anyBoolean()))
                    .thenReturn("Howdy Erudio!");

            // When / Act
            String result = getWelcomeMessage("Erudio", false);

            // Then / Assert
            assertEquals("Howdy Erudio!", result);
        }
    }

}
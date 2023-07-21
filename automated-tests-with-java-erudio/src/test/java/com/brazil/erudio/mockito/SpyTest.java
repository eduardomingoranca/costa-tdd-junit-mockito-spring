package com.brazil.erudio.mockito;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Spy -> eh um mock parcial.Cria uma instancia da classe que
 * pode-se invocar ou chamar os metodos reais.
 */
class SpyTest {

    @Test
    void testVersionOne() {
        // Given / Arrange
        List<String> mockArrayList = spy(ArrayList.class);

        // When / Act & Then / Assert
        assertEquals(0, mockArrayList.size());

        when(mockArrayList.size()).thenReturn(5);
        mockArrayList.add("Foo-Bar");

        assertEquals(5, mockArrayList.size());
    }

    @Test
    void testVersionTwo() {
        // Given / Arrange
        List<String> spyArrayList = spy(ArrayList.class);

        // When / Act & Then / Assert
        assertEquals(0, spyArrayList.size());

        spyArrayList.add("Foo-Bar");
        assertEquals(1, spyArrayList.size());

        spyArrayList.remove("Foo-Bar");
        assertEquals(0, spyArrayList.size());
    }

    @Test
    void testVersionThree() {
        // Given / Arrange
        List<String> spyArrayList = spy(ArrayList.class);

        // When / Act & Then / Assert
        assertEquals(0, spyArrayList.size());

        when(spyArrayList.size()).thenReturn(5);
        assertEquals(5, spyArrayList.size());
    }

    @Test
    void testVersionFour() {
        // Given / Arrange
        List<String> spyArrayList = spy(ArrayList.class);

        // When / Act & Then / Assert
        spyArrayList.add("Foo-Bar");

        verify(spyArrayList).add("Foo-Bar");
        verify(spyArrayList, never()).remove("Foo-Bar");
        verify(spyArrayList, never()).remove(anyString());
        verify(spyArrayList, never()).clear();
    }

}

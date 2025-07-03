package com.moksha.todof.Service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyValueDTOTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        KeyValueDTO dto = new KeyValueDTO();
        dto.setKey("username");
        dto.setValue("moksha123");

        assertEquals("username", dto.getKey());
        assertEquals("moksha123", dto.getValue());
    }

    @Test
    void testAllArgsConstructor() {
        KeyValueDTO dto = new KeyValueDTO("email", "moksha@example.com");

        assertEquals("email", dto.getKey());
        assertEquals("moksha@example.com", dto.getValue());
    }
}

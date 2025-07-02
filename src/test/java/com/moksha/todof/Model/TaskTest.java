package com.moksha.todof.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        task = Task.builder()
                .id("123")
                .title("Test Task")
                .description("This is a sample task.")
                .status("Pending")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void testBuilder() {
        assertEquals("123", task.getId());
        assertEquals("Test Task", task.getTitle());
        assertEquals("This is a sample task.", task.getDescription());
        assertEquals("Pending", task.getStatus());
        assertNotNull(task.getCreatedAt());
        assertNotNull(task.getUpdatedAt());
    }

    @Test
    void testSettersAndGetters() {
        Task t = new Task();
        t.setId("999");
        t.setTitle("Another Task");
        t.setDescription("Another description");
        t.setStatus("Completed");

        assertEquals("999", t.getId());
        assertEquals("Another Task", t.getTitle());
        assertEquals("Another description", t.getDescription());
        assertEquals("Completed", t.getStatus());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        Task t = new Task("111", "Task X", "Desc X", "InProgress", now, now);

        assertEquals("111", t.getId());
        assertEquals("Task X", t.getTitle());
        assertEquals("Desc X", t.getDescription());
        assertEquals("InProgress", t.getStatus());
        assertEquals(now, t.getCreatedAt());
        assertEquals(now, t.getUpdatedAt());
    }

    @Test
    void testCustomConstructorWithoutTimestamps() {
        Task t = new Task("234", "Quick Task", "Fast job", "Done");

        assertEquals("234", t.getId());
        assertEquals("Quick Task", t.getTitle());
        assertEquals("Fast job", t.getDescription());
        assertEquals("Done", t.getStatus());
        assertNotNull(t.getCreatedAt());
        assertNotNull(t.getUpdatedAt());
    }
}

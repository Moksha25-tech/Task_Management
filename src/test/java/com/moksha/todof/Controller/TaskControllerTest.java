package com.moksha.todof.Controller;

import com.moksha.todof.Model.Task;
import com.moksha.todof.Service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private Task mockTask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockTask = Task.builder()
                .id("1")
                .title("Test Task")
                .description("Sample description")
                .status("Pending")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void testGetAllTasks() {
        when(taskService.getAllTasks()).thenReturn(Arrays.asList(mockTask));

        List<Task> result = taskController.getAllTasks();

        assertEquals(1, result.size());
        assertEquals("Test Task", result.get(0).getTitle());
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void testGetTaskById() {
        when(taskService.getTaskById("1")).thenReturn(ResponseEntity.ok(mockTask));

        ResponseEntity<Task> response = taskController.getTaskById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Task", response.getBody().getTitle());
        verify(taskService).getTaskById("1");
    }

    @Test
    void testCreateTask() {
        when(taskService.createTask(mockTask)).thenReturn(mockTask);

        ResponseEntity<Task> response = taskController.createTask(mockTask);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Task", response.getBody().getTitle());
        verify(taskService).createTask(mockTask);
    }

    @Test
    void testUpdateTask() {
        Task updated = Task.builder()
                .id("1")
                .title("Updated Title")
                .description("Updated desc")
                .status("Done")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(taskService.updateTask("1", updated)).thenReturn(updated);

        ResponseEntity<Task> response = taskController.updateTask("1", updated);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Title", response.getBody().getTitle());
        verify(taskService).updateTask("1", updated);
    }

    @Test
    void testDeleteTask() {
        doNothing().when(taskService).deleteTask("1");

        ResponseEntity<Task> response = taskController.deleteTask("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(taskService).deleteTask("1");
    }
}
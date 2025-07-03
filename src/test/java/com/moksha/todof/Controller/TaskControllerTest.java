package com.moksha.todof.Controller;

import com.moksha.todof.Model.Task;
import com.moksha.todof.Service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    private Task sampleTask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleTask = new Task();
        sampleTask.setId("1");
        sampleTask.setTitle("Test Task");
        sampleTask.setDescription("Test Description");
    }

    @Test
    void testGetAllTasks() {
        List<Task> taskList = Collections.singletonList(sampleTask);
        when(taskService.getAllTasks()).thenReturn(taskList);

        List<Task> result = taskController.getAllTasks();

        assertEquals(1, result.size());
        assertEquals("Test Task", result.get(0).getTitle());
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void testGetTaskById() {
        when(taskService.getTaskById("1")).thenReturn(ResponseEntity.ok(sampleTask));

        ResponseEntity<Task> response = taskController.getTaskById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Task", response.getBody().getTitle());
        verify(taskService, times(1)).getTaskById("1");
    }

    @Test
    void testCreateTask() {
        when(taskService.createTask(sampleTask)).thenReturn(sampleTask);

        ResponseEntity<Task> response = taskController.createTask(sampleTask);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Task", response.getBody().getTitle());
        verify(taskService, times(1)).createTask(sampleTask);
    }

    @Test
    void testUpdateTask() {
        when(taskService.updateTask("1", sampleTask)).thenReturn(sampleTask);

        ResponseEntity<Task> response = taskController.updateTask("1", sampleTask);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Task", response.getBody().getTitle());
        verify(taskService, times(1)).updateTask("1", sampleTask);
    }

    @Test
    void testDeleteTask() {
        doNothing().when(taskService).deleteTask("1");

        ResponseEntity<Void> response = taskController.deleteTask("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(taskService, times(1)).deleteTask("1");
    }
}

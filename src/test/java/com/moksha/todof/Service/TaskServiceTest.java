package com.moksha.todof.Service;

import com.moksha.todof.Model.Task;
import com.moksha.todof.Repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task mockTask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockTask = Task.builder()
                .id("1")
                .title("Sample Task")
                .description("Desc")
                .status("Pending")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = List.of(mockTask);
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(1, result.size());
        assertEquals("Sample Task", result.get(0).getTitle());
        verify(taskRepository).findAll();
    }

    @Test
    void testGetTaskById_CacheHit() {
        when(restTemplate.getForEntity("http://localhost:8080/api/cache/1", Task.class))
                .thenReturn(new ResponseEntity<>(mockTask, HttpStatus.OK));

        ResponseEntity<Task> response = taskService.getTaskById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Sample Task", response.getBody().getTitle());
        verify(restTemplate).getForEntity("http://localhost:8080/api/cache/1", Task.class);
        verify(taskRepository, never()).findById(any());
    }

    @Test
    void testGetTaskById_CacheMiss_DBHit() {
        when(restTemplate.getForEntity("http://localhost:8080/api/cache/1", Task.class))
                .thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        when(taskRepository.findById("1")).thenReturn(Optional.of(mockTask));

        ResponseEntity<Task> response = taskService.getTaskById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(taskRepository).findById("1");
        verify(restTemplate).postForEntity(eq("http://localhost:8080/api/cache/1"), eq(mockTask), eq(Void.class));
    }

    @Test
    void testGetTaskById_NotFoundAnywhere() {
        when(restTemplate.getForEntity("http://localhost:8080/api/cache/1", Task.class))
                .thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        when(taskRepository.findById("1")).thenReturn(Optional.empty());

        ResponseEntity<Task> response = taskService.getTaskById("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(taskRepository).findById("1");
    }

    @Test
    void testCreateTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(mockTask);

        Task result = taskService.createTask(mockTask);

        assertEquals("Sample Task", result.getTitle());
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void testUpdateTask() {
        Task updatedDetails = Task.builder()
                .title("Updated Title")
                .description("Updated Desc")
                .status("Completed")
                .build();

        when(taskRepository.findById("1")).thenReturn(Optional.of(mockTask));
        when(taskRepository.save(any(Task.class))).thenReturn(mockTask);
        when(restTemplate.getForEntity("http://localhost:8080/api/cache/1", Task.class))
                .thenReturn(new ResponseEntity<>(mockTask, HttpStatus.OK));

        Task result = taskService.updateTask("1", updatedDetails);

        assertEquals("Updated Title", result.getTitle());
        verify(taskRepository).save(any(Task.class));
        verify(restTemplate).postForEntity(eq("http://localhost:8080/api/cache/1"), eq(updatedDetails), eq(Task.class));
    }

    @Test
    void testDeleteTask() {
        when(restTemplate.getForEntity("http://localhost:8080/api/cache/1", Task.class))
                .thenReturn(new ResponseEntity<>(mockTask, HttpStatus.OK));

        taskService.deleteTask("1");

        verify(taskRepository).deleteById("1");
        verify(restTemplate).delete("http://localhost:8080/api/cache/1");
    }
}

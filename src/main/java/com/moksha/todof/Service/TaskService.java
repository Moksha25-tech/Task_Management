package com.moksha.todof.Service;

import com.moksha.todof.Model.Task;
import com.moksha.todof.Repository.TaskRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final RestTemplate restTemplate;
    private final TaskRepository taskRepository;
    private final String cacheServiceUrl = "http://localhost:8080/api/cache";

    public TaskService(RestTemplate restTemplate, TaskRepository taskRepository) {
        this.restTemplate = restTemplate;
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        log.info("Fetching all tasks");
        return taskRepository.findAll();
    }

    public ResponseEntity<Task> getTaskById(String id) {
        log.info("Fetching task with id: {}", id);

        ResponseEntity<Task> response = restTemplate.getForEntity(cacheServiceUrl + "/" + id, Task.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Task found in cache with id: {}", id);
            return ResponseEntity.ok(response.getBody());
        }

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            log.info("Task not found in cache, fetched from database with id: {}", id);

            restTemplate.postForEntity(cacheServiceUrl + "/" + id, task, Void.class);
            log.info("Task added to cache with id: {}", id);

            return ResponseEntity.ok(task);
        }

        log.warn("Task not found with id: {}", id);
        return ResponseEntity.notFound().build();
    }

    public Task createTask(@NotNull Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        log.info("Creating a new task with title: {}", task.getTitle());
        return taskRepository.save(task);
    }

    public Task updateTask(String id, @NotNull Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        Task updatedTask = taskRepository.save(task);

        ResponseEntity<Task> response = restTemplate.getForEntity(cacheServiceUrl + "/" + updatedTask.getId(), Task.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            restTemplate.postForEntity(cacheServiceUrl + "/" + updatedTask.getId(), taskDetails, Task.class);
            log.info("Task added to cache with id: {}", updatedTask.getId());
        }

        return updatedTask;
    }

    public void deleteTask(String id) {
        log.info("Deleting task with id: {}", id);
        taskRepository.deleteById(id);

        ResponseEntity<Task> response = restTemplate.getForEntity(cacheServiceUrl + "/" + id, Task.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            restTemplate.delete(cacheServiceUrl + "/" + id);
            log.info("Task deleted from cache with id: {}", id);
        }
    }
}

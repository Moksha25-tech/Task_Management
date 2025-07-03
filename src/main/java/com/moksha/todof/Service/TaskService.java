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
        log.info("Fetching all tasks from database");
        return taskRepository.findAll();
    }

    public ResponseEntity<Task> getTaskById(String id) {
        log.info("Fetching task with id: {}", id);

       Task cachedTask = fetchFromCache(id);
       if(cachedTask != null) {
           return ResponseEntity.ok(cachedTask);
       }

       Optional<Task> optionalTask = taskRepository.findById(id);
       if(optionalTask.isPresent()) {
           Task task = optionalTask.get();
           addToCache(id, task);
           return ResponseEntity.ok(task);
       }

       log.info("Task with id: {} not found", id);
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
        task.setUpdatedAt(LocalDateTime.now());
        Task updatedTask = taskRepository.save(task);

        if(isPresentInCache(id)){
            updateCache(id, updatedTask);
        }

        return updatedTask;
    }

    public void deleteTask(String id) {
        log.info("Deleting task with id: {}", id);
        taskRepository.deleteById(id);

        removeFromCache(id);
    }

    private Task fetchFromCache(String id) {
        try {
            ResponseEntity<Task> responseEntity = restTemplate.getForEntity(cacheServiceUrl + "/" + id, Task.class);
            if(responseEntity.getStatusCode() ==  HttpStatus.OK) {
                log.info("Task found in cache with id: {}", id);
                return responseEntity.getBody();
            }
        }
        catch(Exception e) {
            log.error("Failed to fetch task from cache: {}", e.getMessage());
        }
        return null;
    }

    private void addToCache(String id, Task task) {
        try {
            restTemplate.postForEntity(cacheServiceUrl, new KeyValueDTO(id, task), Void.class);
            log.info("Task added to cache with id: {}", id);
        }
        catch(Exception e) {
            log.warn("Failed to add task to cache: {}", e.getMessage());
        }
    }

    private boolean isPresentInCache(String id) {
        try {
            ResponseEntity<Task> response = restTemplate.getForEntity(cacheServiceUrl + "/" + id, Task.class);
            return response.getStatusCode() == HttpStatus.OK;
        }
        catch(Exception e) {
            log.warn("Cache not available or task not found in cache while checking existence: {}", e.getMessage());
            return false;
        }
    }

    private void updateCache(String id, Task task) {
        try {
            restTemplate.put(cacheServiceUrl + "/" + id, new KeyValueDTO(id, task));
            log.info("Task updated in cache with id: {}", id);
        }
        catch(Exception e) {
            log.warn("Failed to update task in cache: {}", e.getMessage());
        }
    }

    private void removeFromCache(String id) {
        try {
            restTemplate.delete(cacheServiceUrl + "/" + id);
            log.info("Task deleted from cache with id: {}", id);
        }
        catch(Exception e) {
            log.warn("Failed to delete task from cache: {}", e.getMessage());
        }
    }
}

package com.moksha.todof.Repository;

import com.moksha.todof.Model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testSaveAndFindById() {
        Task task = new Task(null, "Test Task", "Test Description", "Pending", null, null);
        Task savedTask = taskRepository.save(task);

        Optional<Task> foundTask = taskRepository.findById(savedTask.getId());
        assertTrue(foundTask.isPresent());
        assertEquals("Test Task", foundTask.get().getTitle());
    }

    @Test
    void testDelete() {
        Task task = new Task(null, "Task to Delete", "Description", "Pending", null, null);
        Task savedTask = taskRepository.save(task);

        taskRepository.deleteById(savedTask.getId());

        Optional<Task> found = taskRepository.findById(savedTask.getId());
        assertTrue(found.isEmpty());
    }
}

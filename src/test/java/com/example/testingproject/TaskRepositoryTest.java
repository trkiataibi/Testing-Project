package com.example.testingproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepositoryInterface taskRepository;

    @Test
    public void testSaveAndFindById() {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatus("Not Started");
        task.setPriority("Medium");

        Task savedTask = taskRepository.save(task);
        Optional<Task> foundTask = taskRepository.findById(savedTask.getId());

        assertTrue(foundTask.isPresent());
        assertEquals("Test Task", foundTask.get().getTitle());
    }

    @Test
    public void testFindAll() {
        Task task1 = new Task();
        task1.setTitle("Task 1");

        Task task2 = new Task();
        task2.setTitle("Task 2");

        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasks = taskRepository.findAll();
        assertEquals(2, tasks.size());
    }

    @Test
    public void testDeleteById() {
        Task task = new Task();
        task.setTitle("Task to Delete");

        Task savedTask = taskRepository.save(task);
        Long taskId = savedTask.getId();

        taskRepository.deleteById(taskId);
        Optional<Task> deletedTask = taskRepository.findById(taskId);

        assertFalse(deletedTask.isPresent());
    }
}
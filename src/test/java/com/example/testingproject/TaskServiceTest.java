package com.example.testingproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepositoryInterface taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Task task1 = new Task();
        task1.setTitle("Task 1");
        Task task2 = new Task();
        task2.setTitle("Task 2");

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.findAll();

        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Sample Task");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> foundTask = taskService.findById(1L);

        assertTrue(foundTask.isPresent());
        assertEquals("Sample Task", foundTask.get().getTitle());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        Task task = new Task();
        task.setTitle("New Task");

        when(taskRepository.save(task)).thenReturn(task);

        Task savedTask = taskService.save(task);

        assertNotNull(savedTask);
        assertEquals("New Task", savedTask.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testDeleteById() {
        taskService.deleteById(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }
}
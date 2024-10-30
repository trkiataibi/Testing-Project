package com.example.testingproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testGettersAndSetters() {
        Task task = new Task();

        task.setId(1L);
        task.setTitle("Sample Task");
        task.setDescription("Sample Description");
        task.setStatus("In Progress");
        task.setPriority("High");

        assertEquals(1L, task.getId());
        assertEquals("Sample Task", task.getTitle());
        assertEquals("Sample Description", task.getDescription());
        assertEquals("In Progress", task.getStatus());
        assertEquals("High", task.getPriority());
    }
}
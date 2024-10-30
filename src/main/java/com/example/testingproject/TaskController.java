package com.example.testingproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String redirectToTasks() {
        return "redirect:/tasks";
    }

    @GetMapping
    public String viewTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "task-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "create-task";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("task", taskService.findById(id).orElseThrow());
        return "edit-task";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@PathVariable("id") Long id, @ModelAttribute Task task) {
        task.setId(id);
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }

    @GetMapping("/set-priority/{id}")
    public String showPriorityForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("task", taskService.findById(id).orElseThrow());
        return "set-priority";
    }

    @PostMapping("/set-priority/{id}")
    public String setPriority(@PathVariable("id") Long id, @RequestParam("priority") String priority) {
        Task task = taskService.findById(id).orElseThrow();
        task.setPriority(priority);
        taskService.save(task);
        return "redirect:/tasks";
    }
}
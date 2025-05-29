package com.br.todolist.controller;

import com.br.todolist.dto.request.TaskRequest;
import com.br.todolist.dto.response.TaskResponse;
import com.br.todolist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewTask(@RequestBody @Valid TaskRequest task) {
        taskService.addNewTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTask() {
        List<TaskResponse> listTask = taskService.getAllTask();

        return ResponseEntity.status(HttpStatus.OK)
                .body(listTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskByID(@PathVariable Long id) {
        TaskResponse task = taskService.getTaskById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(task);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<TaskResponse>> getAllTaskCompleted() {
        List<TaskResponse> taskCompleted = taskService.getAllTaskCompleted();
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskCompleted);
    }

    @GetMapping("/no-completed")
    public ResponseEntity<List<TaskResponse>> getAllTaskNoCompleted() {
        List<TaskResponse> taskNoCompleted = taskService.getAllTaskNoCompleted();
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskNoCompleted);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody @Valid TaskRequest task) {
        taskService.updateTask(id, task);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> changeCompletedStatus(@PathVariable Long id, @RequestParam Boolean completed) {
        taskService.changeCompletedStatus(id, completed);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

}

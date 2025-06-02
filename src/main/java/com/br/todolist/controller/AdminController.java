package com.br.todolist.controller;

import com.br.todolist.dto.response.TaskResponse;
import com.br.todolist.dto.response.UserResponse;
import com.br.todolist.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/user")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> listUser = adminService.getAllUsers();
        return ResponseEntity
                .status(HttpStatus.OK).body(listUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse user = adminService.getUserById(id);
        return ResponseEntity
                .status(HttpStatus.OK).body(user);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskResponse>> getAllUserTask(@PathVariable Long id) {
        List<TaskResponse> listTask = adminService.getAllUserTask(id);
        return ResponseEntity
                .status(HttpStatus.OK).body(listTask);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity
                .ok().build();
    }

}

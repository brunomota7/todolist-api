package com.br.todolist.controller;

import com.br.todolist.dto.request.UserRequest;
import com.br.todolist.dto.response.UserResponse;
import com.br.todolist.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo() {
        UserResponse user = userService.getUserInfo();
        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }

    @PutMapping("/update-info")
    public ResponseEntity<?> updateUserInfo(@RequestBody @Valid UserRequest dto) {
        userService.updateInfoUser(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

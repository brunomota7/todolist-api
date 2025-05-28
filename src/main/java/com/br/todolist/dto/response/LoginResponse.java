package com.br.todolist.dto.response;

import com.br.todolist.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String name;
    private String email;
    private UserRole role;
}

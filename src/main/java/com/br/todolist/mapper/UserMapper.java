package com.br.todolist.mapper;

import com.br.todolist.dto.response.UserResponse;
import com.br.todolist.model.UserModel;

public class UserMapper {

    public static UserResponse toDTO(UserModel dto) {
       return UserResponse.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

}

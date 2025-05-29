package com.br.todolist.service;

import com.br.todolist.dto.request.UserRequest;
import com.br.todolist.dto.response.UserResponse;
import com.br.todolist.exceptions.UserNotFoundException;
import com.br.todolist.mapper.UserMapper;
import com.br.todolist.model.UserModel;
import com.br.todolist.repositorys.UserRepository;
import org.hibernate.boot.model.process.internal.UserTypeResolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse getUserInfo() {
        UserModel user = authService.getAuthenticatedUser();
        return UserMapper.toDTO(user);
    }

    public void updateInfoUser(UserRequest dto) {
        UserModel user = authService.getAuthenticatedUser();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
    }

}

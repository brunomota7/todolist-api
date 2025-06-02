package com.br.todolist.service;

import com.br.todolist.dto.response.TaskResponse;
import com.br.todolist.dto.response.UserResponse;
import com.br.todolist.exceptions.TaskNotFoundException;
import com.br.todolist.exceptions.UserNotFoundException;
import com.br.todolist.mapper.TaskMapper;
import com.br.todolist.mapper.UserMapper;
import com.br.todolist.model.TaskModel;
import com.br.todolist.model.UserModel;
import com.br.todolist.repositorys.TaskRepository;
import com.br.todolist.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public UserResponse getUserById(Long id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário de ID " + id + " não encontrado!"));
        return UserMapper.toDTO(user);
    }

    public List<TaskResponse> getAllUserTask(Long id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário de ID " + id + " não encontrado!"));

        List<TaskModel> listTask = taskRepository.findByUser(user);

        if (!listTask.isEmpty()) throw new TaskNotFoundException("Nenhuma tarefa para esse usuário!");

        return listTask.stream()
               .map(TaskMapper::toDTO)
               .toList();
    }

    public void deleteUser(Long id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário de ID " + id + " não encontrado!"));

        userRepository.delete(user);
    }

}

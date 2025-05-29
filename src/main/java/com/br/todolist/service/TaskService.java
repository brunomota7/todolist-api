package com.br.todolist.service;

import com.br.todolist.dto.request.TaskRequest;
import com.br.todolist.dto.response.TaskResponse;
import com.br.todolist.exceptions.TaskNotFoundException;
import com.br.todolist.mapper.TaskMapper;
import com.br.todolist.model.TaskModel;
import com.br.todolist.model.UserModel;
import com.br.todolist.repositorys.TaskRepository;
import com.br.todolist.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public void addNewTask(TaskRequest dto) {
        UserModel user = authService.getAuthenticatedUser();

        TaskModel task = TaskModel.builder()
                .description(dto.getDescription())
                .dueDate(dto.getDueDate())
                .completed(dto.getCompleted())
                .user(user)
                .build();

        taskRepository.save(task);
    }

    public List<TaskResponse> getAllTask() {
        UserModel user = authService.getAuthenticatedUser();
        return taskRepository.findByUser(user).stream()
                .map(TaskMapper::toDTO).toList();
    }

    public TaskResponse getTaskById(Long id) {
        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task de ID " + id + " não encontrada!"));
        return TaskMapper.toDTO(task);
    }

    public List<TaskResponse> getAllTaskCompleted() {
        return taskRepository.findByCompletedTrue().stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    public List<TaskResponse> getAllTaskNoCompleted() {
        return taskRepository.findByCompletedFalse().stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    public void updateTask(Long id, TaskRequest dto) {
        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task de ID " + id + " não encontrada!"));

        task.setDescription(dto.getDescription());
        task.setDueDate(dto.getDueDate());

        taskRepository.save(task);
    }

    public void changeCompletedStatus(Long id, Boolean completed) {
        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task de ID " + id + " não encontrada!"));

        task.setCompleted(completed);
        taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task de ID " + id + " não encontrada!"));

        taskRepository.delete(task);
    }

}

package com.br.todolist.repositorys;

import com.br.todolist.model.TaskModel;
import com.br.todolist.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    List<TaskModel> findByUser(UserModel user);
    List<TaskModel> findByUserAndCompletedTrue(UserModel user);
    List<TaskModel> findByUserAndCompletedFalse(UserModel user);
}

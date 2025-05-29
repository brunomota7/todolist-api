package com.br.todolist.repositorys;

import com.br.todolist.model.TaskModel;
import com.br.todolist.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    List<TaskModel> findByUser(UserModel user);
    List<TaskModel> findByCompletedTrue();
    List<TaskModel> findByCompletedFalse();
}

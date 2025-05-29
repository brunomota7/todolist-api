package com.br.todolist.mapper;

import com.br.todolist.dto.response.TaskResponse;
import com.br.todolist.model.TaskModel;

public class TaskMapper {

    public static TaskResponse toDTO(TaskModel task) {
        var builder = TaskResponse.builder()
                .id(task.getId())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .completed(task.getCompleted());

        if (task.getUser() != null) {
            builder.user(TaskResponse.UserInfo.builder()
                    .id(task.getUser().getId())
                    .name(task.getUser().getName())
                    .build());
        }

        return builder.build();
    }

}

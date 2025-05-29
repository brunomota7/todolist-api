package com.br.todolist.repositorys;

import com.br.todolist.dto.response.UserResponse;
import com.br.todolist.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}

package com.br.todolist.service;

import com.br.todolist.config.JwtUtil;
import com.br.todolist.dto.request.LoginRequest;
import com.br.todolist.dto.request.RegisterRequest;
import com.br.todolist.dto.response.LoginResponse;
import com.br.todolist.exceptions.UserNotFoundException;
import com.br.todolist.model.UserModel;
import com.br.todolist.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public void register(RegisterRequest dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado!");
        }

        UserModel user = UserModel.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();

        userRepository.save(user);
    }

    public ResponseEntity<LoginResponse> login(LoginRequest dto) {
        UserModel user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida!");
        }

        String token = jwtUtil.generateToke(user);

        return ResponseEntity.status(200)
                .body(new LoginResponse(token, user.getName(), user.getEmail(), user.getRole()));
    }

    public UserModel getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
    }

}

package com.study.card.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.card.dtos.LoginRequestDTO;
import com.study.card.dtos.LoginResponseDTO;
import com.study.card.model.User;
import com.study.card.repository.UserRepository;
import com.study.card.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data) {
        
        // procura o usuário no banco de dados pelo email
        User user = userRepository.findByEmail(data.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // verifica a senha
        if (passwordEncoder.matches(data.password(), user.getPassword())) {
            
            // se tiver tudo certo, gera o token
            String token = tokenService.generateToken(user);
            
            // devolve o token para o usuario
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } else {
            // se tiver errado retorna erro
            throw new RuntimeException(HttpStatus.UNAUTHORIZED.toString() + " - Credenciais inválidas!");
        }
    }
} 
    


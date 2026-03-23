package com.study.card.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.study.card.dtos.CadastroDTO;
import com.study.card.model.User;
import com.study.card.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CadastroDTO cadastroDTO) {
        User savedUser = userService.createUser(cadastroDTO);
        return ResponseEntity.ok(savedUser);
    }

}

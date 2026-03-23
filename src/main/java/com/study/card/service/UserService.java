package com.study.card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.card.dtos.CadastroDTO;
import com.study.card.model.User;
import com.study.card.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(CadastroDTO cadastroDTO) {
        User user = new User();
        user.setName(cadastroDTO.getName());
        user.setEmail(cadastroDTO.getEmail());
        user.setPassword(passwordEncoder.encode(cadastroDTO.getPassword()));
        return userRepository.save(user);
    }   
    
}

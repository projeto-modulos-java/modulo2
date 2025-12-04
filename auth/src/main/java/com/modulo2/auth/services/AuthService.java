package com.modulo2.auth.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.modulo2.auth.entities.User;
import com.modulo2.auth.repositories.UserRepository;
import com.modulo2.auth.repositories.entities.UserEntity;
import com.modulo2.auth.services.adapters.UserAdapter;


@Service
public class AuthService{ 
    final private UserRepository repository;
    final private PasswordEncoder passwordEncoder;
    final private UserAdapter adapter;

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder, UserAdapter adapter) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.adapter = adapter;
    }

    public User login(String email, String password) throws Exception{
        User user = adapter.adaptUser(this.repository.findByEmail(email).orElse(new UserEntity()));
        if(passwordEncoder.matches(password, user.getPassword())){
            return user;
        } else {
            throw new Exception("Usuario com nome ou senha incorretos");
        }
    }

    public void create(User user) throws Exception{
        if(this.repository.findByEmail(user.getEmail()).isPresent()){;
            throw new Exception("Email ja utilizado");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(adapter.adaptUser(user));
    }
}
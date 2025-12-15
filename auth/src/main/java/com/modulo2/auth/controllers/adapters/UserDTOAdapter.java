package com.modulo2.auth.controllers.adapters;

import java.util.List;

import org.springframework.stereotype.Service;

import com.modulo2.auth.controllers.dto.UserRequest;
import com.modulo2.auth.controllers.dto.UserResponse;
import com.modulo2.auth.entities.User;



@Service
public class UserDTOAdapter {
    public User adaptUser(UserRequest request){
        return new User(null, request.nome(), request.password(), request.email(), request.dtaNasc(), false);
    }

    public List<UserResponse> adaptResponse(List<User> users){
        return users.stream().map(user -> new UserResponse(user.getId(), user.getNome(), user.getEmail(), user.getDtaNasc(), user.isAtivo())).toList();
    }

    public UserResponse adaptResponse(User user){
        return new UserResponse(user.getId(), user.getNome(), user.getEmail(), user.getDtaNasc(), user.isAtivo());
    }
}
package com.modulo2.auth.services.adapters;

import java.util.List;

import org.springframework.stereotype.Service;

import com.modulo2.auth.entities.User;
import com.modulo2.auth.repositories.entities.UserEntity;


@Service
public class UserAdapter {
    public User adaptUser(UserEntity entity){
        return new User(entity.getId(), entity.getNome(), entity.getPassword(), entity.getEmail(), entity.getDtaNasc(), entity.isAtivo());
    }

    public UserEntity adaptUser(User user){
        return new UserEntity(user.getId(), user.getNome(), user.getPassword(), user.getEmail(), user.getDtaNasc(), user.isAtivo());
    }

    public List<User> adaptUsers(List<UserEntity> entities){
        return entities.stream().map(
            entity -> new User(entity.getId(), 
                entity.getNome(), 
                entity.getPassword(), 
                entity.getEmail(), 
                entity.getDtaNasc(), 
                entity.isAtivo())
            ).toList();
    }
}

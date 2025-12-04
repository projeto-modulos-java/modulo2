package com.modulo2.auth.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.modulo2.auth.repositories.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    public Optional<UserEntity> findByEmail(String email);
}

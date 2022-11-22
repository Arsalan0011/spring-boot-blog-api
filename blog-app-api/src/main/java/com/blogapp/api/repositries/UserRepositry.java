package com.blogapp.api.repositries;

import com.blogapp.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositry extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}

package com.example.CodeFellowship.repositories;

import com.example.CodeFellowship.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

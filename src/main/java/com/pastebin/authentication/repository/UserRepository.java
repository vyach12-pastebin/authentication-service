package com.pastebin.authentication.repository;

import com.pastebin.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserById(String id);
    Optional<User> findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}

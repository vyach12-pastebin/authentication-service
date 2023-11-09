package com.finracy.authentication.repository;

import com.finracy.authentication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserById(String id);
    Optional<User> findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}

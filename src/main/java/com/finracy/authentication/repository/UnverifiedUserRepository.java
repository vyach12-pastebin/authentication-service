package com.finracy.authentication.repository;

import com.finracy.authentication.model.UnverifiedUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UnverifiedUserRepository extends MongoRepository<UnverifiedUser, String> {
    boolean existsUnverifiedUserByEmail(String email);
    Optional<UnverifiedUser> findUnverifiedUserByEmail(String email);
}

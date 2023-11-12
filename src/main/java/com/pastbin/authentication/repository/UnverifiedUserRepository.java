package com.pastbin.authentication.repository;

import com.pastbin.authentication.model.UnverifiedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnverifiedUserRepository extends JpaRepository<UnverifiedUser, String> {
    boolean existsUnverifiedUserByEmail(String email);
    Optional<UnverifiedUser> findUnverifiedUserByEmail(String email);
}

package com.pastebin.authentication.repository;

import com.pastebin.authentication.model.UnverifiedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnverifiedUserRepository extends JpaRepository<UnverifiedUser, String> {
    boolean existsUnverifiedUserByEmail(String email);
    Optional<UnverifiedUser> findUnverifiedUserByEmail(String email);
}

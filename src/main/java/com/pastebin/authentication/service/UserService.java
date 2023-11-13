package com.pastebin.authentication.service;

import com.pastebin.authentication.model.User;

public interface UserService {
    User findByEmail(String email);
    User findById(String id);
    boolean existsByEmail(String email);
    User save(User user);
}

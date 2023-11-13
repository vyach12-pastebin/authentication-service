package com.pastebin.authentication.service;

import com.pastebin.authentication.model.UnverifiedUser;
import com.pastebin.authentication.model.User;

public interface UnverifiedUserService {
    UnverifiedUser findByEmail(String email);
    boolean existsByEmail(String email);
    UnverifiedUser save(UnverifiedUser user);

    void delete(UnverifiedUser user);
}

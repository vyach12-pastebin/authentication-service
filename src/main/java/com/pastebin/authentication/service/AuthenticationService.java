package com.pastebin.authentication.service;

import com.pastebin.authentication.dto.RegisterRequest;
import com.pastebin.authentication.model.User;

public interface AuthenticationService {
    void register(RegisterRequest request);
    User authenticate(RegisterRequest request);
    void confirmURL(String email, String code);
}

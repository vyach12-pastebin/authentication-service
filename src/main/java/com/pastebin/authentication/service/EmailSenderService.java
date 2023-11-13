package com.pastebin.authentication.service;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String body);
}

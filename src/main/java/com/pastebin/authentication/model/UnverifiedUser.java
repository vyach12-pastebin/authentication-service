package com.pastebin.authentication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "unverified_users")
public class UnverifiedUser {
    @Id
    private String id;
    private String email;
    private String password;
    private Instant createdAt;
    private String verificationCode;
}
